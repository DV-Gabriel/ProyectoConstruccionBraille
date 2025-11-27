package com.example.demo.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

/**
 * Service for Braille conversion.
 * Handles business logic for text to Braille and Braille to text translation.
 */
@Service
public class BrailleService {
    
    private static final Map<Character, String> TEXTO_A_BRAILLE = new HashMap<>();
    private static final Map<String, Character> BRAILLE_A_TEXTO = new HashMap<>();
    private static final String INDICADOR_MAYUSCULA = "⠨";
    private static final String INDICADOR_NUMERO = "⠼"; // Prefijo para números
    
    static {
        // Alfabeto español
        TEXTO_A_BRAILLE.put('a', "⠁");
        TEXTO_A_BRAILLE.put('b', "⠃");
        TEXTO_A_BRAILLE.put('c', "⠉");
        TEXTO_A_BRAILLE.put('d', "⠙");
        TEXTO_A_BRAILLE.put('e', "⠑");
        TEXTO_A_BRAILLE.put('f', "⠋");
        TEXTO_A_BRAILLE.put('g', "⠛");
        TEXTO_A_BRAILLE.put('h', "⠓");
        TEXTO_A_BRAILLE.put('i', "⠊");
        TEXTO_A_BRAILLE.put('j', "⠚");
        TEXTO_A_BRAILLE.put('k', "⠅");
        TEXTO_A_BRAILLE.put('l', "⠇");
        TEXTO_A_BRAILLE.put('m', "⠍");
        TEXTO_A_BRAILLE.put('n', "⠝");
        TEXTO_A_BRAILLE.put('ñ', "⠻");
        TEXTO_A_BRAILLE.put('o', "⠕");
        TEXTO_A_BRAILLE.put('p', "⠏");
        TEXTO_A_BRAILLE.put('q', "⠟");
        TEXTO_A_BRAILLE.put('r', "⠗");
        TEXTO_A_BRAILLE.put('s', "⠎");
        TEXTO_A_BRAILLE.put('t', "⠞");
        TEXTO_A_BRAILLE.put('u', "⠥");
        TEXTO_A_BRAILLE.put('v', "⠧");
        TEXTO_A_BRAILLE.put('w', "⠺");
        TEXTO_A_BRAILLE.put('x', "⠭");
        TEXTO_A_BRAILLE.put('y', "⠽");
        TEXTO_A_BRAILLE.put('z', "⠵");
        
        // Vocales con acento
        TEXTO_A_BRAILLE.put('á', "⠷");
        TEXTO_A_BRAILLE.put('é', "⠮");
        TEXTO_A_BRAILLE.put('í', "⠌");
        TEXTO_A_BRAILLE.put('ó', "⠬"); // CORREGIDO: era ⠾ (de ú)
        TEXTO_A_BRAILLE.put('ú', "⠾");
        TEXTO_A_BRAILLE.put('ü', "⠳");
        
        // Números (sin indicador, solo el patrón)
        TEXTO_A_BRAILLE.put('0', "⠚");
        TEXTO_A_BRAILLE.put('1', "⠁");
        TEXTO_A_BRAILLE.put('2', "⠃");
        TEXTO_A_BRAILLE.put('3', "⠉");
        TEXTO_A_BRAILLE.put('4', "⠙");
        TEXTO_A_BRAILLE.put('5', "⠑");
        TEXTO_A_BRAILLE.put('6', "⠋");
        TEXTO_A_BRAILLE.put('7', "⠛");
        TEXTO_A_BRAILLE.put('8', "⠓");
        TEXTO_A_BRAILLE.put('9', "⠊");
        
        // Puntuación
        TEXTO_A_BRAILLE.put(' ', " ");
        TEXTO_A_BRAILLE.put(',', "⠂");
        TEXTO_A_BRAILLE.put('.', "⠄"); // CORREGIDO: punto en Braille
        TEXTO_A_BRAILLE.put('?', "⠢");
        TEXTO_A_BRAILLE.put('¿', "⠢"); // AGREGADO: mismo que ?
        TEXTO_A_BRAILLE.put('!', "⠖");
        TEXTO_A_BRAILLE.put('¡', "⠖"); // AGREGADO: mismo que !
        TEXTO_A_BRAILLE.put(';', "⠆");
        TEXTO_A_BRAILLE.put(':', "⠒");
        TEXTO_A_BRAILLE.put('-', "⠤"); // Resta/Guion
        TEXTO_A_BRAILLE.put('(', "⠐⠣");
        TEXTO_A_BRAILLE.put(')', "⠐⠜");
        
        // Operadores matemáticos y símbolos especiales
        TEXTO_A_BRAILLE.put('+', "⠐⠖"); // Suma
        TEXTO_A_BRAILLE.put('*', "⠡"); // Multiplicación
        TEXTO_A_BRAILLE.put('×', "⠡"); // Multiplicación alternativo
        TEXTO_A_BRAILLE.put('/', "⠸⠌"); // División (con prefijo para evitar conflicto con í)
        TEXTO_A_BRAILLE.put('÷', "⠸⠌"); // División alternativo
        TEXTO_A_BRAILLE.put('=', "⠶"); // Igual
        TEXTO_A_BRAILLE.put('<', "⠐⠅"); // Menor que
        TEXTO_A_BRAILLE.put('>', "⠨⠂"); // Mayor que
        TEXTO_A_BRAILLE.put('%', "⠚⠴"); // Porcentaje
        TEXTO_A_BRAILLE.put('@', "⠈⠁"); // Arroba
        TEXTO_A_BRAILLE.put('#', "⠼"); // Numeral/hashtag
        TEXTO_A_BRAILLE.put('$', "⠈⠎"); // Dólar
        TEXTO_A_BRAILLE.put('€', "⠈⠑"); // Euro
        TEXTO_A_BRAILLE.put('&', "⠯"); // Ampersand
        TEXTO_A_BRAILLE.put('_', "⠤⠤"); // Guion bajo
        TEXTO_A_BRAILLE.put('"', "⠦"); // Comillas
        TEXTO_A_BRAILLE.put('\'', "⠄"); // Apóstrofe
        TEXTO_A_BRAILLE.put('[', "⠷"); // Corchete izq
        TEXTO_A_BRAILLE.put(']', "⠾"); // Corchete der
        TEXTO_A_BRAILLE.put('{', "⠐⠷"); // Llave izq
        TEXTO_A_BRAILLE.put('}', "⠐⠾"); // Llave der
        TEXTO_A_BRAILLE.put('\\', "⠸⠡"); // Backslash
        TEXTO_A_BRAILLE.put('`', "⠸⠳"); // Backtick
        TEXTO_A_BRAILLE.put('~', "⠈⠱"); // Tilde
        TEXTO_A_BRAILLE.put('^', "⠈⠢"); // Caret
        TEXTO_A_BRAILLE.put('°', "⠴"); // Grado
        
        // Crear mapa inverso
        TEXTO_A_BRAILLE.forEach((key, value) -> {
            if (!value.equals(" ")) {
                BRAILLE_A_TEXTO.put(value, key);
            }
        });
        BRAILLE_A_TEXTO.put(" ", ' ');
        
        // Sobrescribir caracteres ambiguos - priorizar vocales sobre operadores
        BRAILLE_A_TEXTO.put("⠌", 'í'); // í tiene prioridad sobre división simple
        
        // Caracteres con secuencias de 2+ caracteres Braille (manejo especial)
        BRAILLE_A_TEXTO.put("⠐⠣", '(');
        BRAILLE_A_TEXTO.put("⠐⠜", ')');
        BRAILLE_A_TEXTO.put("⠐⠖", '+'); // Suma
        BRAILLE_A_TEXTO.put("⠸⠌", '/'); // División (con prefijo)
        BRAILLE_A_TEXTO.put("⠐⠅", '<'); // Menor que
        BRAILLE_A_TEXTO.put("⠨⠂", '>'); // Mayor que
        BRAILLE_A_TEXTO.put("⠚⠴", '%'); // Porcentaje
        BRAILLE_A_TEXTO.put("⠈⠁", '@'); // Arroba
        BRAILLE_A_TEXTO.put("⠈⠎", '$'); // Dólar
        BRAILLE_A_TEXTO.put("⠈⠑", '€'); // Euro
        BRAILLE_A_TEXTO.put("⠤⠤", '_'); // Guion bajo
        BRAILLE_A_TEXTO.put("⠐⠷", '{'); // Llave izq
        BRAILLE_A_TEXTO.put("⠐⠾", '}'); // Llave der
        BRAILLE_A_TEXTO.put("⠸⠡", '\\'); // Backslash
        BRAILLE_A_TEXTO.put("⠸⠳", '`'); // Backtick
        BRAILLE_A_TEXTO.put("⠈⠱", '~'); // Tilde
        BRAILLE_A_TEXTO.put("⠈⠢", '^'); // Caret
    }
    
    /**
     * Converts Spanish text to Braille.
     * Correctly handles the number indicator (⠼) once per numeric sequence.
     *
     * @param texto the text to convert
     * @return the Braille representation
     */
    public String textoABraille(String texto) {
        if (texto == null || texto.isEmpty()) {
            return "";
        }
        
        StringBuilder resultado = new StringBuilder();
        boolean enSecuenciaNumerica = false; // Flag para saber si estamos en números
        
        for (int i = 0; i < texto.length(); i++) {
            char caracter = texto.charAt(i);
            char caracterMinuscula = Character.toLowerCase(caracter);
            
            boolean esDigito = Character.isDigit(caracter);
            
            // Detectar inicio de secuencia numérica
            if (esDigito && !enSecuenciaNumerica) {
                // Nueva secuencia de números, agregar indicador ⠼
                resultado.append(INDICADOR_NUMERO);
                enSecuenciaNumerica = true;
            }
            
            // Detectar separadores que terminan secuencia numérica pero inician una nueva
            // Separadores en contexto numérico: guion (-), coma (,), punto (.)
            if (enSecuenciaNumerica && (caracter == '-' || caracter == ',' || caracter == '.')) {
                // Agregar el separador
                String brailleSeparador = TEXTO_A_BRAILLE.get(caracter);
                if (brailleSeparador != null) {
                    resultado.append(brailleSeparador);
                }
                
                // Verificar si el siguiente carácter es un número
                if (i + 1 < texto.length() && Character.isDigit(texto.charAt(i + 1))) {
                    // Agregar indicador de número después del separador
                    resultado.append(INDICADOR_NUMERO);
                    enSecuenciaNumerica = true;
                } else {
                    enSecuenciaNumerica = false;
                }
                continue;
            }
            
            // Detectar fin de secuencia numérica (espacio, letra, etc.)
            if (!esDigito && caracter != '-' && caracter != ',' && caracter != '.') {
                enSecuenciaNumerica = false;
            }
            
            // Si es mayúscula (y no es número ni espacio), agregar indicador
            if (Character.isUpperCase(caracter) && !esDigito && caracter != ' ') {
                resultado.append(INDICADOR_MAYUSCULA);
            }
            
            // Convertir el carácter
            String brailleChar = TEXTO_A_BRAILLE.get(caracterMinuscula);
            if (brailleChar != null) {
                resultado.append(brailleChar);
            } else {
                // Si no se encuentra, mantener el carácter original
                resultado.append(caracter);
            }
        }
        
        return resultado.toString();
    }
    
    /**
     * Converts Braille to Spanish text.
     * Correctly handles the number indicator (⠼).
     *
     * @param braille the Braille text to convert
     * @return the Spanish text representation
     */
    public String brailleATexto(String braille) {
        if (braille == null || braille.isEmpty()) {
            return "";
        }
        
        StringBuilder resultado = new StringBuilder();
        boolean siguienteMayuscula = false;
        boolean enModoNumero = false;
        
        for (int i = 0; i < braille.length(); i++) {
            String caracterActual = String.valueOf(braille.charAt(i));
            
            // Detectar indicador de mayúscula
            if (caracterActual.equals(INDICADOR_MAYUSCULA)) {
                siguienteMayuscula = true;
                continue;
            }
            
            // Detectar indicador de número
            if (caracterActual.equals(INDICADOR_NUMERO)) {
                enModoNumero = true;
                continue;
            }
            
            // Detectar separadores que mantienen o terminan el modo número
            if (caracterActual.equals("⠤") || caracterActual.equals("⠂") || caracterActual.equals("⠄")) {
                // Es un separador (guion, coma, punto)
                Character textoChar = BRAILLE_A_TEXTO.get(caracterActual);
                if (textoChar != null) {
                    resultado.append(textoChar);
                }
                // No salir de modo número, el siguiente indicador lo decidirá
                continue;
            }
            
            // Espacios terminan el modo número
            if (caracterActual.equals(" ")) {
                resultado.append(' ');
                enModoNumero = false;
                continue;
            }
            
            // Convertir el carácter
            Character textoChar = BRAILLE_A_TEXTO.get(caracterActual);
            
            if (textoChar != null) {
                if (enModoNumero) {
                    // En modo número: convertir letras a-j como números 1-0
                    char numeroChar = letraANumero(textoChar);
                    resultado.append(numeroChar);
                } else if (siguienteMayuscula && textoChar != ' ') {
                    resultado.append(Character.toUpperCase(textoChar));
                    siguienteMayuscula = false;
                } else {
                    resultado.append(textoChar);
                }
            } else {
                // Si no se encuentra, mantener el carácter original
                resultado.append(braille.charAt(i));
            }
        }
        
        return resultado.toString();
    }
    
    /**
     * Convierte letra Braille a número cuando está en modo numérico
     * a=1, b=2, c=3, d=4, e=5, f=6, g=7, h=8, i=9, j=0
     */
    private char letraANumero(char letra) {
        switch (letra) {
            case 'a': return '1';
            case 'b': return '2';
            case 'c': return '3';
            case 'd': return '4';
            case 'e': return '5';
            case 'f': return '6';
            case 'g': return '7';
            case 'h': return '8';
            case 'i': return '9';
            case 'j': return '0';
            default: return letra;
        }
    }
    
    /**
    /**
     * Validates if a text can be converted to Braille.
     *
     * @param texto the text to validate
     * @return true if all characters can be converted, false otherwise
     */
    public boolean puedeConvertirABraille(String texto) {
        if (texto == null || texto.isEmpty()) {
            return false;
        }
        
        for (char c : texto.toLowerCase().toCharArray()) {
            if (!TEXTO_A_BRAILLE.containsKey(c)) {
                return false;
            }
        }
        return true;
    }
}
