# bootcamp-reto

# DATABASE
## Crear BD de MongoDB
`docker run -d -p 27016:27017 -v ~/data:/data/db --name mongo mongo`
## Ejecutar script que se encuenta en la ruta para agregar los servicios: src/main/resources/data.sql
## Crear BD de SQL AZURE
Para conectarse a la bd azure las credenciales son:
url=r2dbc:pool:mssql://svrbootcamp.database.windows.net:1433/bd-favorite
username=bootcamp
password=bootc@mp123

#Crear containder registry
usar el nombre `acrbootcamp1`

#LOgin a Azure container registry
`docker login acrbootcamp1.azurecr.io -u [USER] -p [PASSWORD]`

#Conectarse a kubernetes, previo descargar e instalar el az cli.
`az login`
copiar del boton conectar en kubernetes. conectando a la cuenta de azure
`az account set --subscription [codigo_subscripcion]`
conectado al servicio aks
`az aks get-credentials --resource-group [grupo de recurso] --name [nombre aks]`

#Crear secreto para conectar kubernete con ACR
`kubectl create secret docker-registry acr-secret \
--namespace default \
--docker-server=acrbootcamp1.azurecr.io \
--docker-username=[usuario de containder registry] \
--docker-password=[password de containder registry]`

# Config Server
## En Kubernetes ejecutar el siguiente script
`docker build -t config-server:0.0.1 .`
`docker tag config-server:0.0.1 acrbootcamp1.azurecr.io/config-server:0.0.1`
`docker push acrbootcamp1.azurecr.io/config-server:0.0.1`
`kubectl apply -f script_kubernetes/01-config-server-deployment-svc-loadBalancer.yaml`

# SECURITY KEYCLOACK
## En Kubernetes ejecutar el siguiente script
`kubectl apply -f script_kubernetes/02-keycloak-deployment-svc-loadBalancer.yaml`

# EUREKA
## En Kubernetes ejecutar el siguiente script
`docker build -t service-discovery:0.0.1 .`
`docker tag service-discovery:0.0.1 acrbootcamp1.azurecr.io/service-discovery:0.0.1`
`docker push acrbootcamp1.azurecr.io/service-discovery:0.0.1`
`kubectl apply -f script_kubernetes/03-service-discovery-deployment-svc-loadBalancer.yaml`

# GATEWAY
## En Kuberneter ejecutar el siguiente script
`docker build -t gateway-service:0.0.1 .`
`docker tag gateway-service:0.0.1 acrbootcamp1.azurecr.io/gateway-service:0.0.1`
`docker push acrbootcamp1.azurecr.io/gateway-service:0.0.1`
`kubectl apply -f script_kubernetes/04-gateway-service-deployment-svc-loadBalancer.yaml`

#DESPLIEGUE DE APIS.
##Despliegue de favorite-service
`docker build -t favorite-service:0.0.1 .`
`docker tag favorite-service:0.0.1 acrbootcamp1.azurecr.io/favorite-service:0.0.1`
`docker push acrbootcamp1.azurecr.io/favorite-service:0.0.1`
`kubectl apply -f script_kubernetes/05-favorite-service-deployment-svc-loadBalancer.yaml`

##Despliegue de payment-service
`docker build -t payment-service:0.0.1 .`
`docker tag payment-service:0.0.1 acrbootcamp1.azurecr.io/payment-service:0.0.1`
`docker push acrbootcamp1.azurecr.io/payment-service:0.0.1`
`kubectl apply -f script_kubernetes/06-payment-service-deployment-svc-loadBalancer.yaml`

##Despliegue de channel-payment
`docker build -t channel-payment:0.0.1 .`
`docker tag channel-payment:0.0.1 acrbootcamp1.azurecr.io/channel-payment:0.0.1`
`docker push acrbootcamp1.azurecr.io/channel-payment:0.0.1`
`kubectl apply -f script_kubernetes/07-channel-payment-deployment-svc-loadBalancer.yaml`