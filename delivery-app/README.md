# otus-arch-2/delivery-app

# Docker
<p>docker build -t yvzhelnin/delivery-appl:v1.0 --no-cache .</p>
<p>docker push yvzhelnin/delivery-appl:v1.0</p> 

# HELM
<p>helm repo add https://charts.bitnami.com/bitnami</p>
<p>cd delivery-appl-chart</p>
<p>helm dependency update</p>
<p>cd ..</p>
<p>helm install delivery-app ./delivery-appl-chart/ --values delivery-appl-chart/values.yaml</p>
