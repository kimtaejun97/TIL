# 📌 OSI 7계층
***
> - 통신이 일어나는 과정을 단계별로 파악 가능.
> - 문제가 생기면 해당 계층만 수정할 수 있다.


- ### 7. Application Layer(응용 계층)
    - HTTP, DNS, FTP 등 
    - 최종 목적지, 종단 계층으로 일반적인 응용 프로세스 수행.
    - 사용자 인터페이스, 데이터베이스 등을 관리.

- ### 6. Presentation Layer(표현 계층)
    - 데이터 전송에 대한 표현, 암호화 등을 담당한다.
    - 파일 인코딩, 명령어 포장, 압축, 암호화
    
- ### 5. Session Layer
    - 데이터가 통신하기 위한 논리적 연결을 담당한다.  
    - TCP / IP 세션의 생성과 제거의 책임.
    
- ### 4. Transport Layer(전송 계층)
    - TCP, UDP 프로토콜을 이용하여 통신을 활성화 한다.
    - 포트를 열고 프로그램들이 전송할 수 있도록 함.
    
- ### 3. Network Layer(네트워크 계층)
    - 데이터를 목적지까지 전송
    - 라우터를 통해 이동할 경로를 탐색, IP 주소를 지정.
    - 라우팅, 흐름 제어, 오류 제어, 세그멘테이션 수행.
    
- ### 2. Data Link
    - 물리 계층으로 송, 수신되는 데이터를 안전하게 전달되도록 도와줌.
    - 프레임에 Mac 주소를 할당하고, 흐름 제어, 오류 제어, 재전송 등을 진행.
    
- ### 1. Physical(물리 계층)
    - 데이터를 전기적 신호로 주고받는 공간.
    

# 📌 TCP
***
- 신뢰적 네트워크 통신 프로토콜.
- 혼잡 회피(congestion avoidance) 알고리즘 사용.

## 🧐 3 Way HandShake (연결)
- 통신 이전에 논리적인 접속을 성립하기 위해 3 Way HandShake를 사용.

> 1. 클라이언트가 서버에 SYN 패킷 전송.(sequence=x) -> 서버야 들려?
> 2. 서버에서 SYN(sequence=x) 패킷을 수신 하고 응답으로 SYN(sequence=y, ack=x+1)을 전송. -> 응 들려, 너도 들려?
> 3. 클라이언트에서 SYN(seqence=y, ack=x+1)을 수신하고 ACK(sequence=y+1)을 전송.
> - 서버에서 ACK(sequence=y+1) 를 receive.

## 🧐 4 Way HandShake (연결 해제)
![img.png](img.png)
이미지 출처 :https://www.geeksforgeeks.org/tcp-connection-termination/

> 1. 클라이언트가 서버에게 연결 종료를 요청 (FIN)
> 2. 서버에서 ACK를 보내고 남은 데이터를 전송하기 위해 CLOSE WAIT 상태가 됨.
> 3. 서버에서 남은 데이터를 전송하고 FIN 패킷을 보냄.
> 4. 클라이언트에서는 FIN을 수신하고 서버에게 ACK를 보냄. 남은 데이터를 받기 위해 TIME WAIT 상태가 됨.
> 
> - 서버는 ACK를 수신 한 후 소켓을 Close.
> - 클라이언트는 TIME WAIT가 끝나면 소켓을 Close.


## 🧐 신뢰성을 위한 문제 해결
```
1. 손실 (ACK를 수신하지 못했으면 재전송.)
2. 순서 바뀜. (order bit를 할당.)
3. Congestion(Congestion Control)
4. Overload - 흐름 제어(Flow Control)
```

### ☝️ Flow Control(흐름 제어)
- 송신자와 수신자의 처리 속도 차이를 해결하기 위한 방법.
- 수신자의 처리 버퍼를 초과하면 불필요한 재전송이 발생하기 때문에 이를 조절.

- #### ✏️ Stop and Wait
  - 매번 ACK 응답을 받아야 다음 패킷을 전송.
    
- #### ✏️ Sliding Window(Go Back N ARQ)
    - 수신측에서 정한 Window의 크기만큼 응답을 기다리지 않고 전송.
    - 수신측의 Window보다 작거나 같은 크기의 송신 Window의 크기를 정한다.
    - 3 way HandShake 때 수신측의 window 사이즈와 맞춤.
    
    ![img_1.png](img_1.png)
    
    > - 윈도우에 포함되는 모든 패킷을 전송.
    > - 각 ACK 응답을 수신하는 대로 윈도우를 한 칸씩 이동.


### ☝️ Congestion Control(혼잡 제어)
- 송신자와 네트워크의 처리 속도 차이를 해결하기 위한 방법.
- 혼잡 : 네트워크 내에 패킷의 수가 과도하게 증가하는 상태.

- #### ✏️ AIMD(Additive Increase / Multiplicative Decrease)
    - 처음에는 패킷을 하나씩 전송하고 문제 없이 도착한다면 window size를 1씩 증가시킴.
    - 전송이 실패하거나 일정 시간이 지나면 window size를 절반으로 줄인다.
    - 처음에 높은 대역폭을 사용하지 못해 느림.
    - 혼잡을 미리 감지하지 못하고 혼잡해 진 다음에야 대처하는 방법.

- #### ✏️ Slow Start
    - 패킷을 하나씩 보내면서 시작하고, 패킷이 문제없이 도착하면 각각의 ACK 패킷마다 window size를 1씩 늘려준다. (즉 1,2,4,8.. 지수로 증
    - 혼잡이 발생하면 window size를 1로 낮춘다. 
    - 한번 혼잡이 발생하면 네트워크 수용량을 어느정도 예측 가능.
    - 혼잡 현상이 발생하였던 window size의 절반까지는 이전처럼 지수 함수 꼴로 창 크기를 증가시키고 그 이후부터는 완만하게 1씩 증가시킨다.
    
- #### ✏️ Fast Retransmit (빠른 재전송)
    - 순서대로 도착하지 않아도 ACK를 보냄.
    - 올바르게 도착한 마지막 패킷의 다음 패킷의 순번을 ACK로 보내기 때문에, 중간에 손실된 패킷이 존재한다면 중복 ACK를 수신.
        - 1,2,3,5 를 수신한다면 4를 두번 전송.
    - 중복 ACK를 3번 받으면 재전송, 혼잡임을 예측하고 Window size를 조절.
    
- #### ✏️ Fast Recovery
    - 혼잡한 발생시 window size를 1로 줄이지 않고 반으로 줄이고 선형증가시키는 방법. 
    - 혼잡 상황을 한번 겪고 나서부터는 순수한 AIMD 방식으로 동작하게 된다.