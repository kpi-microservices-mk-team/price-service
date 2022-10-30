docker build -t price-service:1.0.1 -f Dockerfile .
docker tag price-service:1.0.1  makstrexa/price-service:1.0.1
docker push makstrexa/price-service:1.0.1