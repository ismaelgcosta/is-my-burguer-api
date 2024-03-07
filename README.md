# Is My Burguer

Projeto para aplicação de conhecimentos da Fase 2 da Pós-Graduação em SOFTWARE ARCHITECTURE da FIAP

# Requisitos necessários para compilar o projeto

Ter o docker instalado na sua máquina:

* [Windows](https://docs.docker.com/windows/started)

* [OS X](https://docs.docker.com/mac/started/)

* [Linux](https://docs.docker.com/linux/started/)

Ter o Java 20 instalado na sua máquina:

[JDK 20](https://jdk.java.net/java-se-ri/20)


Ter o Kubectl instalado na sua máquina:

[Kubectl](https://kubernetes.io/docs/tasks/tools/)


# Como fazer o build do projeto e da imagem docker


## Windows

Na pasta raiz do projeto rodar o comando abaixo:

```
./mvnw install -U "&" docker compose build "&" docker compose up
```

## Linux

Na pasta raiz do projeto rodar o comando abaixo:

```
/bin/bash docker-build.sh
```

# Como fazer o construir o ambiente de deploy no kubernetes
(os testes foram feitos utilizando o Kubernetes embarcado do Docker Desktop)

## Windows & Linux

Na pasta kubernetes do projeto rodar o comando abaixo:

```
kubectl apply -f .
```

# Swagger
http://localhost:31080/swagger-ui/index.html


# ReDoc
http://localhost:31080


