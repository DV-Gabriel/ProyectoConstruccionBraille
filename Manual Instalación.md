# Manual de Instalación 

## Requisitos previos
- Cuenta en Supabase (PostgreSQL).  
- Cuenta en Render.  
- Cuenta en Vercel.  

## Paso 1 — Configurar base de datos
1. Crear proyecto en Supabase + Postgres.  
2. Copiar la URL de conexión.  

## Paso 2 — Configurar backend
1. En `backend/src/main/resources`, crear un archivo de propiedades (ej. `application-prod.properties`) con:
   ```
   spring.datasource.url=TU_URL_SUPABASE
   spring.datasource.username = username
   spring.datasource.password password
   ```
2. Verificar que las dependencias y configuración de Spring Boot estén correctas.

## Paso 3 — Desplegar backend en Render
1. Crear un nuevo Web Service apuntando a la carpeta `backend`.  
2. Configurar build y start command.  
3. Definir variables de entorno (URL de DB, credenciales…).  
4. Desplegar.  

## Paso 4 — Desplegar frontend en Vercel
1. Conectar repo a Vercel.  
2. Definir variable (ej. `REACT_APP_API_URL`) con la URL de tu backend en Render.  
3. Deploy.  

## Paso 5 — Ajustes finales
- Configurar CORS en backend para permitir llamadas desde tu dominio de frontend.  
- Validar funcionamiento de la app: CRUD, conexión con DB, etc.
