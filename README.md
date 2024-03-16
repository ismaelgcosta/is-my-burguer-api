# Is My Burguer

Projeto para aplicação de conhecimentos da Fase 3 da Pós-Graduação em SOFTWARE ARCHITECTURE da FIAP

## Estrutura na AWS

![alt text](/docs/is-my-burguer-api.drawio.png)

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

# Terraform

<!-- BEGIN_TF_DOCS -->
## Requirements

| Name | Version |
|------|---------|
| <a name="requirement_aws"></a> [aws](#requirement\_aws) | ~> 5.38.0 |
| <a name="requirement_kubectl"></a> [kubectl](#requirement\_kubectl) | >= 1.7.0 |
| <a name="requirement_kubernetes"></a> [kubernetes](#requirement\_kubernetes) | ~> 2.26.0 |

## Providers

| Name | Version |
|------|---------|
| <a name="provider_aws"></a> [aws](#provider\_aws) | 5.38.0 |
| <a name="provider_kubectl"></a> [kubectl](#provider\_kubectl) | 1.14.0 |
| <a name="provider_kubernetes"></a> [kubernetes](#provider\_kubernetes) | 2.26.0 |
| <a name="provider_terraform"></a> [terraform](#provider\_terraform) | n/a |

## Modules

No modules.

## Resources

| Name | Type |
|------|------|
| [kubectl_manifest.is-my-burguer-api-deployment](https://registry.terraform.io/providers/gavinbunney/kubectl/latest/docs/resources/manifest) | resource |
| [kubectl_manifest.is-my-burguer-api-hpa](https://registry.terraform.io/providers/gavinbunney/kubectl/latest/docs/resources/manifest) | resource |
| [kubectl_manifest.is-my-burguer-api-load-balancer](https://registry.terraform.io/providers/gavinbunney/kubectl/latest/docs/resources/manifest) | resource |
| [kubectl_manifest.is-my-burguer-api-namespace](https://registry.terraform.io/providers/gavinbunney/kubectl/latest/docs/resources/manifest) | resource |
| [kubernetes_secret.is-my-burguer-cognito](https://registry.terraform.io/providers/hashicorp/kubernetes/latest/docs/resources/secret) | resource |
| [kubernetes_secret.is-my-burguer-db](https://registry.terraform.io/providers/hashicorp/kubernetes/latest/docs/resources/secret) | resource |
| [aws_availability_zones.available](https://registry.terraform.io/providers/hashicorp/aws/latest/docs/data-sources/availability_zones) | data source |
| [aws_caller_identity.current](https://registry.terraform.io/providers/hashicorp/aws/latest/docs/data-sources/caller_identity) | data source |
| [aws_cognito_user_pool_client.is-my-burguer-api-client](https://registry.terraform.io/providers/hashicorp/aws/latest/docs/data-sources/cognito_user_pool_client) | data source |
| [aws_eks_cluster.cluster](https://registry.terraform.io/providers/hashicorp/aws/latest/docs/data-sources/eks_cluster) | data source |
| [aws_eks_cluster_auth.cluster](https://registry.terraform.io/providers/hashicorp/aws/latest/docs/data-sources/eks_cluster_auth) | data source |
| [terraform_remote_state.is-my-burguer-cognito](https://registry.terraform.io/providers/hashicorp/terraform/latest/docs/data-sources/remote_state) | data source |
| [terraform_remote_state.is-my-burguer-postgres](https://registry.terraform.io/providers/hashicorp/terraform/latest/docs/data-sources/remote_state) | data source |

## Inputs

| Name | Description | Type | Default | Required |
|------|-------------|------|---------|:--------:|
| <a name="input_TF_VAR_COGNITO_PASSWORD"></a> [TF\_VAR\_COGNITO\_PASSWORD](#input\_TF\_VAR\_COGNITO\_PASSWORD) | The master password for the COGNITO CLIENT. | `string` | n/a | yes |
| <a name="input_TF_VAR_IMAGE_VERSION"></a> [TF\_VAR\_IMAGE\_VERSION](#input\_TF\_VAR\_IMAGE\_VERSION) | The number of the new image version. | `string` | n/a | yes |
| <a name="input_TF_VAR_POSTGRES_PASSWORD"></a> [TF\_VAR\_POSTGRES\_PASSWORD](#input\_TF\_VAR\_POSTGRES\_PASSWORD) | The master password for the database. | `string` | n/a | yes |
| <a name="input_TF_VAR_POSTGRES_USER"></a> [TF\_VAR\_POSTGRES\_USER](#input\_TF\_VAR\_POSTGRES\_USER) | The master username for the database. | `string` | n/a | yes |

## Outputs

No outputs.
<!-- END_TF_DOCS -->

