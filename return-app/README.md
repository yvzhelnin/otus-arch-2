# otus-arch-2/return-app

# Docker
<p>docker build -t yvzhelnin/return-appl:v1.0 --no-cache .</p>
<p>docker push yvzhelnin/return-appl:v1.0</p> 

# HELM
<p>helm repo add https://charts.bitnami.com/bitnami</p>
<p>cd return-appl-chart</p>
<p>helm dependency update</p>
<p>cd ..</p>
<p>helm install return-app ./return-appl-chart/ --values return-appl-chart/values.yaml</p>
