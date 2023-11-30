# Установка базового образа
FROM openjdk:17

# Копирование вашего jar-файла в контейнер
ARG JAR_FILE=/target/*.jar
COPY ${JAR_FILE} app.jar

# Открытие нужного порта
EXPOSE 8089

# Запуск приложения
ENTRYPOINT ["java","-jar","/app.jar"]