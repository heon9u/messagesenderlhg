# 

## Model
www.msaez.io/#/181188264/storming/message_sender-lhg

## Before Running Services
### Make sure there is a Kafka server running
```
cd kafka
docker-compose up
```
- Check the Kafka messages:
```
cd infra
docker-compose exec -it kafka /bin/bash
cd /bin
./kafka-console-consumer --bootstrap-server localhost:9092 --topic
```

## Run the backend micro-services
See the README.md files inside the each microservices directory:

- message
- validator
- device
- dashboard


## Run API Gateway (Spring Gateway)
```
cd gateway
mvn spring-boot:run
```

## Test by API
- message
```
 http :8088/messages id="id" messageId="messageId" userContact="userContact" mno="mno" sendTime="sendTime" chatbotId="chatbotId" description="description" 
```
- validator
```
 http :8088/validators id="id" messageId="messageId" userContact="userContact" mno="mno" sendTime="sendTime" chatbotId="chatbotId" policyInfo="policyInfo" description="description" 
```
- device
```
 http :8088/devices id="id" messageId="messageId" userContact="userContact" mno="mno" sendTime="sendTime" chatbotId="chatbotId" description="description" result="result" 
```
- dashboard
```
```


## Run the frontend
```
cd frontend
npm i
npm run serve
```

## Test by UI
Open a browser to localhost:8088

## Required Utilities

- httpie (alternative for curl / POSTMAN) and network utils
```
sudo apt-get update
sudo apt-get install net-tools
sudo apt install iputils-ping
pip install httpie
```

- kubernetes utilities (kubectl)
```
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl
```


