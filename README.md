# otus-arch-2

# Docker
<p>docker build -t yvzhelnin/crud-appl:v1.5 --no-cache .</p>
<p>docker push yvzhelnin/crud-appl:v1.5</p>

# Kubernetes
<p>helm install pg bitnami/postgresql -f values.yml</p>
<p>kubectl apply -f datasource_secrets.yml -f deployment.yml -f service.yml -f ingress.yml</p>
