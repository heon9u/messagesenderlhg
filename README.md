# RCS message

## 클라우드 아키텍처 설계
#### 클라우드 아키텍처 구성

메시지를 보내는 주체인 message(중계)
메시지를 검증하는 validator(홀세일)
메시지 전송을 확인하는 device(삼성)
메시지 이력을 관리하는 dashboard(마이페이지)

## Data Modeling / 서비스 분리 / 설계 역량
#### 도메인 분석 - 이벤트스토밍

Aggregation: message / validator / device / dashboard
![RCS_Event Storming](https://github.com/user-attachments/assets/b8fcb83d-2e9a-4340-b773-0b230f6cd10a)




## MSA개발 또는 개발 관리 역량

#### 분산트랜잭션 - Saga


### 보상처리 - Compensation


### 단일 진입점 - Gateway


### 분산 데이터 프로젝션 - CQRS


## 컨테이너 인프라 설계 및 구성

### 컨테이너 자동확장 - HPA


### 컨테이너로부터 환경분리 - ConfigMap


### 클라우드스토리지 활용 - PVC


### 셀프 힐링 / 무정지배포 - Readiness Probe


### 서비스 메쉬 응용 - Mesh


### 통합 모니터링 - Monitoring


