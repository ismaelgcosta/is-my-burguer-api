 resource "kubectl_manifest" "is-my-burguer-api-namespace" {
  depends_on = [
    data.aws_eks_cluster.cluster
  ]
  yaml_body = <<YAML
apiVersion: apps/v1
kind: Namespace
apiVersion: v1
metadata:
  name: is-my-burguer-api
  namespace: is-my-burguer-api
  labels:
    name: is-my-burguer-api
    app: is-my-burguer-api
YAML
}

resource "kubectl_manifest" "is-my-burguer-api-deployment" {
  depends_on = [
    data.aws_eks_cluster.cluster,
    kubernetes_secret.is-my-burguer-db,
    kubernetes_secret.is-my-burguer-cognito,
    kubectl_manifest.is-my-burguer-api-namespace
  ]
  yaml_body = <<YAML
apiVersion: apps/v1
kind: Deployment
metadata:
  name: is-my-burguer-api
  namespace: is-my-burguer-api
  labels:
    name: is-my-burguer-api
    app: is-my-burguer-api
spec:
  replicas: 1
  selector:
    matchLabels:
      app: is-my-burguer-api
  strategy:
    type: Recreate
  template:
    metadata:
      labels:
        app: is-my-burguer-api
    spec:
      containers:
        - name: is-my-burguer-api
          resources:
            limits:
              cpu: "1"
              memory: "300Mi"
            requests:
              cpu: "300m"
              memory: "300Mi"
          env:
            - name: POSTGRES_PASSWORD
              valueFrom:
                secretKeyRef:
                  name: is-my-burguer-db
                  key: password
            - name: POSTGRES_USER
              valueFrom:
                secretKeyRef:
                  name: is-my-burguer-db
                  key: username
            - name: POSTGRES_HOST
              valueFrom:
                secretKeyRef:
                  name: is-my-burguer-db
                  key: host
            - name: POSTGRES_PORT
              valueFrom:
                secretKeyRef:
                  name: is-my-burguer-db
                  key: port
            - name: CLIENT_CREDENTIALS_ID
              valueFrom:
                secretKeyRef:
                  name: is-my-burguer-cognito
                  key: username
            - name: CLIENT_CREDENTIALS_SECRET
              valueFrom:
                secretKeyRef:
                  name: is-my-burguer-cognito
                  key: password
            - name: CLIENT_DOMAIN
              valueFrom:
                secretKeyRef:
                  name: is-my-burguer-cognito
                  key: cognito_domain
            - name: AWS_COGNITO_USER_POOL_ID
              valueFrom:
                secretKeyRef:
                  name: is-my-burguer-cognito
                  key: user-pool-id
            - name: AWS_REGION
              value: ${local.region}
            - name: AWS_API_GATEWAY_ID
              valueFrom:
                secretKeyRef:
                  name: is-my-burguer-cognito
                  key: api-gateway
          image: docker.io/ismaelgcosta/is-my-burguer-app:${var.TF_VAR_IMAGE_VERSION}
          ports:
            - containerPort: 8080
      restartPolicy: Always
status: {}
YAML
}


resource "kubectl_manifest" "is-my-burguer-api-load-balancer" {
  depends_on = [
    data.aws_eks_cluster.cluster,
    kubectl_manifest.is-my-burguer-api-deployment,
    kubectl_manifest.is-my-burguer-api-namespace
  ]
  yaml_body = <<YAML
apiVersion: v1
kind: Service
metadata:
  name: is-my-burguer-api-load-balancer
  namespace: is-my-burguer-api
spec:
  type: LoadBalancer
  selector:
    app: is-my-burguer-api
  ports:
    - name: http
      protocol: TCP
      port: 80
      targetPort: 8080
      nodePort: 31080
YAML
}

resource "kubectl_manifest" "is-my-burguer-api-hpa" {
  depends_on = [
    data.aws_eks_cluster.cluster,
    kubectl_manifest.is-my-burguer-api-deployment,
    kubectl_manifest.is-my-burguer-api-load-balancer,
    kubectl_manifest.is-my-burguer-api-namespace
  ]
  yaml_body = <<YAML
apiVersion: autoscaling/v2
kind: HorizontalPodAutoscaler
metadata:
  name: is-my-burguer-api-hpa
  namespace: is-my-burguer-api
spec:
  scaleTargetRef:
    apiVersion: apps/v1
    kind: Deployment
    name: is-my-burguer-api
    namespace: is-my-burguer-api
  minReplicas: 2
  maxReplicas: 4
  behavior:
    scaleDown:
      stabilizationWindowSeconds: 0 # para forçar o kubernets a zerar a janela de tempo e escalar imediatamente
    scaleUp:
      stabilizationWindowSeconds: 0 # para forçar o kubernets a zerar a janela de tempo e escalar imediatamente
  metrics:
  - type: Resource
    resource:
      name: cpu
      target:
        type: Utilization
        averageUtilization: 1 # para forçar o kubernets escalar com 1% de cpu
status:
  observedGeneration: 0
  lastScaleTime:
  currentReplicas: 0
  desiredReplicas: 2
  currentMetrics:
  - type: Resource
    resource:
      name: cpu
YAML
}


resource "kubernetes_secret" "is-my-burguer-db" {
  depends_on = [
    kubectl_manifest.is-my-burguer-api-namespace
  ]

  metadata {
    name      = "is-my-burguer-db"
    namespace = kubectl_manifest.is-my-burguer-api-namespace.name
  }

  immutable = false

  data = {
    host = "${data.terraform_remote_state.is-my-burguer-postgres.outputs.database_endpoint_host}",
    port = "${data.terraform_remote_state.is-my-burguer-postgres.outputs.database_endpoint_port}",
    username = "${data.aws_db_instance.is-my-burguer-postgres.master_username}",
    password = "${var.TF_VAR_POSTGRES_PASSWORD}"
  }

  type = "kubernetes.io/basic-auth"

}

resource "kubernetes_secret" "is-my-burguer-cognito" {
  depends_on = [
    kubectl_manifest.is-my-burguer-api-namespace
  ]

  metadata {
    name      = "is-my-burguer-cognito"
    namespace = kubectl_manifest.is-my-burguer-api-namespace.name
  }

  immutable = false

  data = {
    user-pool-id= "${data.aws_cognito_user_pool_client.is-my-burguer-api-client.user_pool_id}"
    api-gateway= "${data.terraform_remote_state.is-my-burguer-cognito.outputs.api_gateway_domain}"
    cognito_domain= "${data.terraform_remote_state.is-my-burguer-cognito.outputs.cognito_domain}"
    username = "${data.terraform_remote_state.is-my-burguer-cognito.outputs.is-my-burguer-api-client-id}",
    password = "${data.aws_cognito_user_pool_client.is-my-burguer-api-client.client_secret}"
  }

  type = "kubernetes.io/basic-auth"

}



