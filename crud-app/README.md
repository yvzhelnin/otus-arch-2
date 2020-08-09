# otus-arch-2/crud-app

# Docker
<p>docker build -t yvzhelnin/crud-appl:v3.0 --no-cache .</p>
<p>docker push yvzhelnin/crud-appl:v3.0</p>

# Kubernetes
<p>helm install pg bitnami/postgresql -f values.yml</p>
<p>kubectl apply -f datasource_secrets.yml -f deployment.yml -f service.yml -f ingress.yml</p>

# HELM
<p>cd crud-appl-chart</p>
<p>helm dependency update</p>
<p>cd ..</p>
<p>helm install crud-app ./crud-appl-chart/ --values crud-appl-chart/values.yaml -n crud-app</p>

# CHECK (Postman)
<p>newman run crud-service-test.postman_collection.json</p>
