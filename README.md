# SoftwareEngineeringProj
Repository for Software Engineering's Project for Ricardo Quintana - 625634


Es necesario crear un archivo Procfiles SIN EXTENSION para ejecutar el dyno de java WEB con la siguiente información

web: java $JAVA_OPTS -jar target/dependency/webapp-runner.jar --port $PORT target/*.war

Es necesario acceder mediante consola y ejecutar:

heroku ps:scale web=1
heroku restart
heroku run java console -a <nombre app>