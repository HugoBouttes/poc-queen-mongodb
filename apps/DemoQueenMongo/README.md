API 1.1 Mongo + Spring Data

En local :
http://localhost:8080/swagger-ui/index.html#/

http://localhost:8080/api/survey-units


En ligne, sur kubernetes
https://demoqueenmongo.dev.insee.io/



Pour démarrer l'API :
```
kubectl apply -f Deployment.yml
kubectl apply -f Service.yml
kubectl apply -f Ingress.yml
kubectl port-forward --namespace dev-hbouttes svc/testhelmmongo-mongodb 27017:27017
```

Puis aller dans le fichier mongo et taper :

```
mongod
mongo --host 127.0.0.1 --authenticationDatabase admin -p $MONGODB_ROOT_PASSWORD
```

MongoDB&reg; can be accessed on the following DNS name(s) and ports from within your cluster:

    testhelmmongo-mongodb.dev-hbouttes.svc.cluster.local

To get the root password run:

    export MONGODB_ROOT_PASSWORD=$(kubectl get secret --namespace dev-hbouttes testhelmmongo-mongodb -o jsonpath="{.data.mongodb-root-password}" | base64 --decode)

To connect to your database, create a MongoDB&reg; client container:

    kubectl run --namespace dev-hbouttes testhelmmongo-mongodb-client --rm --tty -i --restart='Never' --env="MONGODB_ROOT_PASSWORD=$MONGODB_ROOT_PASSWORD" --image docker.io/bitnami/mongodb:5.0.8-debian-10-r9 --command -- bash

Then, run the following command:
    mongo admin --host "testhelmmongo-mongodb" --authenticationDatabase admin -u root -p $MONGODB_ROOT_PASSWORD