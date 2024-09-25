# RCS message

## 클라우드 아키텍처 설계
### 클라우드 아키텍처 구성

실무에서 담당하고 있는 기업형 스마트메시지 RCS를 기반으로 클라우드 아키텍처를 설계.
- 메시지를 보내는 주체인 message(중계)
- 메시지를 검증하는 validator(홀세일)
- 메시지 전송을 확인하는 device(삼성)
- 메시지 이력을 관리하는 dashboard(마이페이지)
  
![achitecture](https://github.com/user-attachments/assets/826c1415-718d-46dd-8da1-d244d6a5df1f)
    
## Data Modeling / 서비스 분리 / 설계 역량
### 도메인 분석 - 이벤트스토밍

1. 메시지를 전송할 때마다 검증을 진행.
2. 검증이 완료될 때마다 메시지를 전송.
3. 메시지를 전송 혹은 실패할 때마다 이력에 저장.
4. 전송한 메시지를 취소하는 기능.

  
Aggregation: message / validator / device / dashboard
![RCS_Event Storming](https://github.com/user-attachments/assets/b8fcb83d-2e9a-4340-b773-0b230f6cd10a)

  
## MSA개발 또는 개발 관리 역량

### 분산트랜잭션 - Saga

- Kafka 기반으로 pub/sub 관계

```
cd infra
docker-compose exec -it kafka /bin/bash
cd /bin
./kafka-console-consumer --bootstrap-server http://localhost:9092 --topic messagesenderlhg --from-beginning
```

```bash
http POST localhost:8082/messages messageId="msg-1" userContact="01012341234" mno="kt" sendTime="2024-09-23" chatbotId="0801234567" description="test"
```

![saga pattern](https://github.com/user-attachments/assets/14510971-4dd7-4c60-b608-47d370c040d0)
  
### 보상처리 - Compensation

- 메시지를 발송 or 예약한 이후, 취소할 수 있는 cancel 기능
- result를 "CANCEL"로 업데이트

```bash
http DELETE localhost:8082/messages/1
```

[before]  
![compensation_before](https://github.com/user-attachments/assets/dfe8b996-1cc7-43b6-9af8-afed6b917f72)
  
[after]  
![compensation_after](https://github.com/user-attachments/assets/4a12c2b5-9814-4cf1-8db7-4330f8c3d108)

  
### 단일 진입점 - Gateway

- 게이트웨이 기반으로 단일 진입점 적용.
- 해당 단계에서는 localhost에서 테스트.
- 요청 port는 다르지만, 결과는 동일.

[message - 8082]  
![gateway-8082](https://github.com/user-attachments/assets/f569a72a-433e-4ce0-9e86-8e6517a7d85e)
  
[gateway - 8088]  
![gateway-8088](https://github.com/user-attachments/assets/5c956a30-e216-4573-bd29-3ac7cfb25fb3)

  
### 분산 데이터 프로젝션 - CQRS

- Create / Delete와 조회 역할을 나눠 부하 분산

[myMessages - 8085]  
![CQRS](https://github.com/user-attachments/assets/793a3949-1697-40ad-986a-e66157f1de74)

  
## 컨테이너 인프라 설계 및 구성
### 컨테이너 자동확장 - HPA

- 컨테이너에 autoscale 적용 (cpu percent: 10)
- siege를 통해 부하를 발생했을 때, cpu에 따른 pod 갯수를 확인
- 이때, pod spec에 resources 적용 (CPU: 200m)

```
kubectl autoscale deploy message --cpu-percent=10 --min=1 --max=3
```

[cpu에 따른 replicas 증가]  
![hpa_replicas](https://github.com/user-attachments/assets/028ea804-ac42-4d7f-98d3-335727defa27)
  
[replicas가 증가한 pod]  
![hpa_pod](https://github.com/user-attachments/assets/c22660e8-0dd5-4a60-ac0a-05c10762e1a3)
  
[siege를 통한 요청 전체 성공]  
![siege_message_hpa](https://github.com/user-attachments/assets/f3f7c404-72b0-480a-928e-277a21938fbe)

  
### 컨테이너로부터 환경분리 - ConfigMap

- 컨테이너에 configMap 기반으로 환경변수를 부여
- Pod spec에 마운트
- Pod 터미널에 진입하여 env 확인

[configMap yaml]  
![configMap yaml file](https://github.com/user-attachments/assets/c7ffb326-cd14-4fa6-8219-2401a5903eab)
  
[Pod spec]  
![deploy에env설정](https://github.com/user-attachments/assets/50a743c3-f9ac-48b7-ac8c-811183aa7da1)
  
[Pod 터미널에서 env 확인]  
![env_result](https://github.com/user-attachments/assets/aac1f9bc-0385-43e1-be98-1ae3289762ca)

  
### 클라우드스토리지 활용 - PVC

- PV, PVC 기반으로 클라우드 스토리지 활용.
- PVC를 선언하고, pod spec에 마운팅.
- 실제 확보된 스토리지 경로를 확인.

[PVC 선언]  
![pvc yaml file](https://github.com/user-attachments/assets/72ff5e83-6279-418a-b1f2-7a972470ebdf)
  
[Pod spec에 마운팅]  
![pod_pvc](https://github.com/user-attachments/assets/486f8c75-233a-4476-bdaf-ffb82160ec79)
![pod_volumeMount](https://github.com/user-attachments/assets/1d527835-a8bc-4122-9430-170d02d815fc)


[확보된 스토리지]  
![pvc result](https://github.com/user-attachments/assets/279bd7fd-5f4c-4430-b0b8-951e2a67b176)

  
### 셀프 힐링 / 무정지배포 - Readiness Probe

- 배포하는 사이에도 요청을 처리할 수 있도록 변경
- siege를 기반으로 부하 발생
- Pod spec에서 readinessProbe 적용

[Probe 설정하기 전, 재배포]  
![readinessProbe_before](https://github.com/user-attachments/assets/3f891117-1ef7-48a9-b29b-2789f5d58dc0)
  
[Probe 설정 후, 재배포]  
![readinessProbe_after](https://github.com/user-attachments/assets/b45fb2b3-0354-467b-b23d-9d54241e604f)
  
[Probe 설정]  
![readinessProbe설정](https://github.com/user-attachments/assets/718bc230-45e5-498b-b8b4-938ab3c43f59)

  
### 서비스 메쉬 응용 - Mesh

- istio / Sidecar / VirtualService 기반으로 서비스 메쉬 구현
- siege를 통해 임의로 500 error를 발생
- Sidecar에서 retry를 하도록 설정
- Tracing Server인 Jaeger에서 retry 확인

> Istio add-on Dashboard 설치
```
mv samples/addons/loki.yaml samples/addons/loki.yaml.old
curl -o samples/addons/loki.yaml https://raw.githubusercontent.com/msa-school/Lab-required-Materials/main/Ops/loki.yaml
kubectl apply -f samples/addons
```

```
sidecar.istio.io/inject=true
```

[istio 설치]  
![istio install](https://github.com/user-attachments/assets/e7a0a161-ff8d-4829-8668-757e1b716436)
  
[message / siege에 Sidecar 적용]  
![istio_sidecar](https://github.com/user-attachments/assets/d775a357-5f8d-4038-831c-dd4550948e5c)
  
[500 error]  
![istio_siege_500error](https://github.com/user-attachments/assets/d2623564-aabe-463a-aafc-413a4a1de0cb)
  
[Jaeger에서 확인]  
![istio_retry](https://github.com/user-attachments/assets/8430d935-4584-4434-bb6f-8ceca29dc402)

  
### 통합 모니터링 - Monitoring

- 모니터링은 Grafana를 활용
- Siege를 통한 부하량을 Grafana의 그래프를 통해 확인

[Grafana 그래프]  
![monitoring_grafana](https://github.com/user-attachments/assets/352b02c5-de5e-4672-95be-9381b4dad635)

  
## github webhook을 이용한 CI/CD
### VM기반 AKS, ACR을 활용한 Jenkins pipeline

- Azure의 VM에서 환경 설정 및 Jenkins 설치
- github에서 commit발생 시, webhook을 통해 자동으로 파이프라인 빌드
- message domain만 CI/CD를 진행하기 때문에 JenkinsFile, Dockerfile 수정

[구축한 VM]  
![vm](https://github.com/user-attachments/assets/c38f2a32-2b70-43ce-b262-59c1bd903931)

```
stage('Maven Build') {
    steps {
        withMaven(maven: 'Maven') {
            sh 'mvn package -f message/pom.xml -DskipTests'
        }
    }
}

stage('Docker Build') {
    steps {
        script {
            image = docker.build("${REGISTRY}/${IMAGE_NAME}:v${env.BUILD_NUMBER}", "-f message/Dockerfile .")
        }
    }
}
```
[Started by Github push by heon9u]  
![cicd jenkins](https://github.com/user-attachments/assets/b87e2ce5-ef01-46c0-a351-33449a241c16)
  
[image tag]  
![cicd docker build](https://github.com/user-attachments/assets/9626595b-e98a-4cdd-adae-a2ed43c68838)
  
[acr에서 확인한 image tag]  
![cicd azure](https://github.com/user-attachments/assets/b9dd0477-0656-4804-894c-97fb3c9f8fb4)
  
[배포된 pod]  
![cicd_pod_deploy](https://github.com/user-attachments/assets/9a0ae0d8-f974-4b19-91fc-1f60c0020c6a)
  


