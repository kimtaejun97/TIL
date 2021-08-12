
# 🧐 WebSocket
***
> - 서버와 클라이언트 간의 양방향 실시간 통신을 위한 스펙.


## ☝️ 기존의 HTTP 통신
***

### 1. HTTP Pooling
- 클라이언트가 요청을 보내고, 서버에서 응답을 보내 기본적인 방식. 클라이언트가 증하면 서버의 부담이 증가함.

### 2. HTTP Long Pooling
- 클라이언트에서 일단 요청을 보내고 기다리고, 서버에서 이벤트가 발생하면 응답을 돌려줌. 클라이언트는 응답을 받고 다시 요청을 보내 다음 이벤트 발생을
  기다림.
  
### 3. HTTP  Streaming
- 클라이언트 요청을 보내고, 응답을 받아도 연결을 끊지 않고 서버에서 지속적으로 이벤트 발생시 응답을 보냄.

> 📌 세 기술 모두 단방향 통신이라는 한계를 가진다.


## 📌 WebSocket
***
- 최초의 핸드쉐이크로 연결이 이루어진다.
- http와 마찬가지로 80, 443 포트를 사용하여 양방향 통신을 한다.
- 하나의 URL을 이용해 커넥션 되고, 이후에는 이를 통해 통신한다.
- 미지원되는 브라우저가 존재하기 때문에 보통 지원하는 브라우저에서는 WebSocket을 사용하고, 이외에는 Long Pooling방식이 적용되도록 한다.



## 📌 Spring Boot의 WebSocket
****
- Fallback 메서드에 SockJS 사용. (브라우저 미지원 등)
- STOMP(Simple Text Oriented Messaging Protocol) : 데이터 교환 및 형식 지정하는 메시징 프로토콜
    > 특정 클라이언트에게만 메시지를 전송하는 등의 역할.

# 🧐 다자간 데이터 전송
*****
## 1.Mesh Networking
- 중앙 서버 없이 Peer간 서로 데이터를 주고 받음, 참여자가 많아질 수록 큰 부담.

## 2. SFU : Selective Forwarding Unit
- 중앙 서버를 통해 트래픽을 중계.
- 클라이언트로부터 미디어 스트림을 받아 조건에 맞게 선택적으로 그대로 전달.
- 비교적 서버 부하, 지연시간이 낮음.

## 3. MCU : Multi Point Control Unit
- 각 클라이언트의 미디어 스트림을 중앙에서 처리.(가공)
- 처리된 결과물을 다시 클라이언트에게 전달. 
- 클라이언트와 네트워크 부담은 매우 적어지지만 중앙서버의 CPU 부담이 증가.

# 🧐 WebRTC(Web Real-Time Communication)
*****
> - 플러그인의 도움 없이 상호 통신할 수 있도록 설계된 API
> - peer to peer (P2P)통신에 최적화.
> - 

1. MediaStream class (GetUserMedia) : 카메라/마이크 등의 데이터 스트림 접근.
2. RTCPeerConnection class : Peer간의 연결 생성, 오디오, 비디오 통신.
3. RTCDataChannel class: Peer 간의 일반적인 데이터 통신.(Chrome 25버전, Opera 18버전, Firefox 22버전 이상에서 지원)

## 📌 P2P 기반 방화벽 통과

- ### STUN : Session Traversal Utilities for NAT
- P2P IP 연결을 위한 정보 제공, 종단에 접근 가능한 IP, Port를 발견하는 작업. 
- 현재 클라이언트의 Public IP를 반환하는 역할을 하는 서버.

- ### TURN : Traversal Using Relays around NAT
- Peer 간 직접 통신이 실패할 경우 종단사이의 연결을 수행하는 TURN 서버를 사용.
- Peer 간의 미디어 스트리밍을 연결하기 위해 사용.
- TURN 은 공용 주소를 가진다.
 > 1. 클라이언트는 TURN 서버에 할당 요청을 보낸다.
 > 2. 서버는 할당된 주소를 응답으로 보낸다.
 > 3. 클라이언트는 통신하고자 하는 Peer 에게 이 주소를 전달.
 > 4. Peer는 해당 주소로 TURN 서버에 접근. P2P 연결을 맺는다.
 
- ### ICE : Interactive Connectivity Establishment
- Peer 간 통신을 위해 STUN, TURN 과 같은 기술로 최적의 라우팅 경로를 찾아내는 기술.




Google Code Labs : https://github.com/googlecodelabs/webrtc-web 
## 📌 웹캠, 마이크 접근
```javascript

const mediaStreamConstraints = {
    video: true,
    audio : false,
};

const localVideo = document.querySelector('#localVideo');

function gotLocalMediaStream(mediaStream) {
  localVideo.srcObject = mediaStream;
}

function handleLocalMediaStreamError(error) {
  console.log('navigator.getUserMedia error: ', error);
}

navigator.mediaDevices.getUserMedia(mediaStreamConstraints)
    .then(gotLocalMediaStream).catch(handleLocalMediaStreamError);
```
- local stream을 비디오 태그의 src로 넣는다.



## 📌 데이터 전송. RTCPeerConnection, DataChannelSend
```javascript
// 1
window.localConnection = localConnection =
        new RTCPeerConnection(servers, pcConstraint);
sendChannel = localConnection.createDataChannel('sendDataChannel',
    dataConstraint);

localConnection.onicecandidate = iceCallback1;

// 2
function iceCallback1(event) {
    if (event.candidate) {
        remoteConnection.addIceCandidate(
            event.candidate
        ).then(
            onAddIceCandidateSuccess,
            onAddIceCandidateError
        );
    }
// 3
remoteConnection.onicecandidate = iceCallback2;
}
```
 1. local peer connection, create data channel
 2. remoteConnection에 local Candidate 추가.
 3. localConnection에도 remote Candidate 추가
 
```javascript
 var data = dataChannelSend.value;
    sendChannel.send(data);
```
- WebSocket 처럼 send를 이용하여 데이터 전송.
![img_2.png](img_2.png)

