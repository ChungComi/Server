version: "3.8"

services:
  springboot-app:
    build: .
    ports:
      - "8080:8080"
    environment:
      - SPRING_PROFILES_ACTIVE=dev
      # Spring Boot에서 MySQL 연결을 위한 환경 변수 설정 (필요 시 application.yml과 연동)
      - SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/springbootdb?useSSL=false&serverTimezone=UTC
      - SPRING_DATASOURCE_USERNAME=root
      - SPRING_DATASOURCE_PASSWORD=0000
    depends_on:
      - mysql

  mysql:
    image: mysql:8.0
    restart: always
    environment:
      MYSQL_DATABASE: chungcomi
      MYSQL_USER: root
      MYSQL_PASSWORD: 0000
      MYSQL_ROOT_PASSWORD: 0000
    ports:
      - "3306:3306"
    volumes:
      - mysql_data:/var/lib/mysql

volumes:
  mysql_data:
