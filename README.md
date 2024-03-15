# Is My Burguer

Projeto para aplicação de conhecimentos da Fase 3 da Pós-Graduação em SOFTWARE ARCHITECTURE da FIAP

# Requisitos necessários para compilar o projeto

Ter o docker instalado na sua máquina:

* [Windows](https://docs.docker.com/windows/started)

* [OS X](https://docs.docker.com/mac/started/)

* [Linux](https://docs.docker.com/linux/started/)

Ter o Java 20 instalado na sua máquina:

[JDK 20](https://jdk.java.net/java-se-ri/20)

# Como fazer o build do projeto e da imagem docker


## Windows

Na pasta raiz do projeto rodar o comando abaixo:

```
./mvnw install -U "&" docker compose build "&" docker compose up
```

## Linux

Na pasta shell do projeto rodar o comando abaixo:

```
/bin/bash docker-build.sh
```

# Swagger
http://localhost:31080/swagger-ui/index.html


# ReDoc
http://localhost:31080


