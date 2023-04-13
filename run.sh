cd src/infra || exit
kubectl apply -f mail-client-secrets.yml
kubectl apply -f mail-client-configmap.yml

kubectl apply -f mail-client-service.yml

kubectl apply -f mail-client-deployment.yml