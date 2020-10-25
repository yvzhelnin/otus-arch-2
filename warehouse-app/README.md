# otus-arch-2/warehouse-app

# Docker
<p>docker build -t yvzhelnin/warehouse-appl:v3.0 --no-cache .</p>
<p>docker push yvzhelnin/warehouse-appl:v3.0</p>

# Kubernetes
<p>helm install pg bitnami/postgresql -f values.yml</p>
<p>kubectl apply -f datasource_secrets.yml -f deployment.yml -f service.yml -f ingress.yml</p>

# HELM
<p>cd warehouse-appl-chart</p>
<p>helm dependency update</p>
<p>cd ..</p>
<p>helm install warehouse-app ./warehouse-appl-chart/ --values warehouse-appl-chart/values.yaml</p>

# CHECK (Postman)
<p>newman run warehouse-service-test.postman_collection.json</p>
