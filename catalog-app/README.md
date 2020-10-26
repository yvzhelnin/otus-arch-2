# otus-arch-2/catalog-app

# Docker
<p>docker build -t yvzhelnin/catalog-appl:v3.0 --no-cache .</p>
<p>docker push yvzhelnin/catalog-appl:v3.0</p>

# Kubernetes
<p>helm install pg bitnami/postgresql -f values.yml</p>
<p>kubectl apply -f datasource_secrets.yml -f deployment.yml -f service.yml -f ingress.yml</p>

# HELM
<p>cd catalog-appl-chart</p>
<p>helm dependency update</p>
<p>cd ..</p>
<p>helm install catalog-app ./catalog-appl-chart/ --values catalog-appl-chart/values.yaml</p>

# CHECK (Postman)
<p>newman run catalog-service-test.postman_collection.json</p>
