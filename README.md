# Master Cloud Apps - Monolith to microservices

## Description

This repository contains the code base and the Kubernetes manifest to run a books web application in two different modes: monolith and monolith with a microservice.

The microservice mentioned above manages Users within the application. Originally, logic for Users was also in the monolith application. 
After applying strategies to split the monolith codebase into smaller services (Stangler Fig and Branch by Abstraction), it is still possible to run a version of the monolith that has the service Users included.

## Usage

### First steps - installation

You need:
- A Minikube instance running on your computer.
- Configure local hosts file to add the IP address and DNS from minikube, pointing to `booksapp`.
- A running MySQL DB instance (configured in K8s manifests)
- Choose between `k8s/monolith-only/` or `k8s/monolith-and-users-ms/` manifests, depending on which version you want to run.

#### Launch Minikube
Run:
```bash
minikube start --driver='virtualbox' --cpus=4 --memory=4000
```
Enable ingress:
```bash
minikube addons enable ingress
```

#### Configure hosts file
Run:
```bash
minikube ip
# 192.168.99.143
```
Copy that ip address, followed by the DNS "booksapp" and paste it in `/etc/hosts`. Do not forget to edit that file using `sudo`.
```bash
sudo vim /etc/hosts
```

#### Run MySQL within the k8s cluster
This service will be available for both versions of the application

Run:
```bash
kubectl apply -f ./k8s/db
```

### Run Monolith Only Application
Start k8s cluster
```bash
kubectl apply -f ./k8s/monolith-only
```

### Run Monolith Only Application
Start k8s cluster
```bash
kubectl apply -f ./k8s/monolith-and-users-ms
```


## Testing
Use a Postman collection with name `Practice2-databases.postman_collection.json` at the root of this project.
