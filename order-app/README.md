# otus-arch-2/order-app

# Docker
<p>docker build -t yvzhelnin/order-appl:v1.0 --no-cache .</p>
<p>docker push yvzhelnin/order-appl:v1.0</p> 

# HELM
<p>helm repo add https://charts.bitnami.com/bitnami</p>
<p>cd order-appl-chart</p>
<p>helm dependency update</p>
<p>cd ..</p>
<p>helm install order-app ./order-appl-chart/ --values order-appl-chart/values.yaml</p>
