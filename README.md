# Ceiba_back 
Cambiar la configuración principal de la aplicación para no tener problemas

--spring.datasource.url=jdbc:mysql://localhost:3306/[DATABASE]?useSSL=false&PublicKeyRetrieval=true&serverTimezone=America/Bogota  
--spring.datasource.username=[USUARIO]  
--spring.datasource.password=[CONTRASEÑA]  

# http://localhost:8080/api/person

# Puerto de salida está por defecto base de datos: 3306

El proyecto está generado con JPA , por ende, solo es necesario crear la base de datos en MySQL y el proyecto en su ejecución, hará un create - drop para asegurar que no haya nada y crear las tablas necesarias como se definió en el modelo

# Ejecución  
Es un proyecto spring boot, por lo tanto asegurarse de que estén instaladas todas las dependencias con maven y ejecutarlo como spring boot app

# localhost:3306/api/person 
esta es la url necesaria para hacer peticiones, están autorizadas para las peticiones (GET/POST)
