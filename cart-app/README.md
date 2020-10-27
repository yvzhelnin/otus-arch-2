# otus-arch-2/cart-app

# Docker
<p>docker build -t yvzhelnin/cart-appl:v3.0 --no-cache .</p>
<p>docker push yvzhelnin/cart-appl:v3.0</p>

# Kubernetes
<p>helm install pg bitnami/postgresql -f values.yml</p>
<p>kubectl apply -f datasource_secrets.yml -f deployment.yml -f service.yml -f ingress.yml</p>

# HELM
<p>cd cart-appl-chart</p>
<p>helm dependency update</p>
<p>cd ..</p>
<p>helm install cart-app ./cart-appl-chart/ --values cart-appl-chart/values.yaml</p>

# CHECK (Postman)
<p>newman run cart-service-test.postman_collection.json</p>
