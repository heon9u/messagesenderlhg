# RCS message

## 클라우드 아키텍처 설계
#### 클라우드 아키텍처 구성

실무에서 담당하고 있는 기업형 스마트메시지 RCS를 기반으로 클라우드 아키텍처를 설계.
- 메시지를 보내는 주체인 message(중계)
- 메시지를 검증하는 validator(홀세일)
- 메시지 전송을 확인하는 device(삼성)
- 메시지 이력을 관리하는 dashboard(마이페이지)

1. 메시지를 전송할 때마다 검증을 진행.
2. 검증이 완료될 때마다 메시지를 전송.
3. 메시지를 전송 혹은 실패할 때마다 이력에 저장.
4. 전송한 메시지를 취소하는 기능.

## Data Modeling / 서비스 분리 / 설계 역량
#### 도메인 분석 - 이벤트스토밍

Aggregation: message / validator / device / dashboard
![RCS_Event Storming](https://github.com/user-attachments/assets/b8fcb83d-2e9a-4340-b773-0b230f6cd10a)


## MSA개발 또는 개발 관리 역량

#### 분산트랜잭션 - Saga

- Kafka 기반으로 pub/sub 관계

```
cd infra
docker-compose exec -it kafka /bin/bash
cd /bin
./kafka-console-consumer --bootstrap-server http://localhost:9092 --topic messagesenderlhg --from-beginning
```

![saga pattern](https://github.com/user-attachments/assets/14510971-4dd7-4c60-b608-47d370c040d0)


#### 보상처리 - Compensation

메시지를 발송 or 예약한 이후, 취소할 수 있는 cancel 기능
[before]
![compensation_before](https://github.com/user-attachments/assets/dfe8b996-1cc7-43b6-9af8-afed6b917f72)

[after]
![compensation_after](https://github.com/user-attachments/assets/4a12c2b5-9814-4cf1-8db7-4330f8c3d108)


#### 단일 진입점 - Gateway

- 게이트웨이 기반으로 단일 진입점 적용.
- 해당 단계에서는 localhost에서 테스트.
- 요청 port는 다르지만, 결과는 동일.

[message - 8082]
![gateway-8082](https://github.com/user-attachments/assets/f569a72a-433e-4ce0-9e86-8e6517a7d85e)

[gateway - 8088]
![gateway-8088](https://github.com/user-attachments/assets/5c956a30-e216-4573-bd29-3ac7cfb25fb3)


#### 분산 데이터 프로젝션 - CQRS

- Create / Delete와 조회 역할을 나눠 부하 분산

[myMessages - 8085]
![CQRS](https://github.com/user-attachments/assets/793a3949-1697-40ad-986a-e66157f1de74)



## 컨테이너 인프라 설계 및 구성

#### 컨테이너 자동확장 - HPA


#### 컨테이너로부터 환경분리 - ConfigMap


#### 클라우드스토리지 활용 - PVC


#### 셀프 힐링 / 무정지배포 - Readiness Probe


#### 서비스 메쉬 응용 - Mesh


#### 통합 모니터링 - Monitoring


## github webhook을 이용한 CI/CD
#### VM기반 AKS, ACR을 활용한 Jenkins pipeline


