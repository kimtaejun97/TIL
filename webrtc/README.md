
# ๐ง WebSocket
***
> - ์๋ฒ์ ํด๋ผ์ด์ธํธ ๊ฐ์ ์๋ฐฉํฅ ์ค์๊ฐ ํต์ ์ ์ํ ์คํ.


## โ๏ธ ๊ธฐ์กด์ HTTP ํต์ 
***

### 1. HTTP Pooling
- ํด๋ผ์ด์ธํธ๊ฐ ์์ฒญ์ ๋ณด๋ด๊ณ , ์๋ฒ์์ ์๋ต์ ๋ณด๋ด ๊ธฐ๋ณธ์ ์ธ ๋ฐฉ์. ํด๋ผ์ด์ธํธ๊ฐ ์ฆํ๋ฉด ์๋ฒ์ ๋ถ๋ด์ด ์ฆ๊ฐํจ.

### 2. HTTP Long Pooling
- ํด๋ผ์ด์ธํธ์์ ์ผ๋จ ์์ฒญ์ ๋ณด๋ด๊ณ  ๊ธฐ๋ค๋ฆฌ๊ณ , ์๋ฒ์์ ์ด๋ฒคํธ๊ฐ ๋ฐ์ํ๋ฉด ์๋ต์ ๋๋ ค์ค. ํด๋ผ์ด์ธํธ๋ ์๋ต์ ๋ฐ๊ณ  ๋ค์ ์์ฒญ์ ๋ณด๋ด ๋ค์ ์ด๋ฒคํธ ๋ฐ์์
  ๊ธฐ๋ค๋ฆผ.
  
### 3. HTTP  Streaming
- ํด๋ผ์ด์ธํธ ์์ฒญ์ ๋ณด๋ด๊ณ , ์๋ต์ ๋ฐ์๋ ์ฐ๊ฒฐ์ ๋์ง ์๊ณ  ์๋ฒ์์ ์ง์์ ์ผ๋ก ์ด๋ฒคํธ ๋ฐ์์ ์๋ต์ ๋ณด๋.

> ๐ ์ธ ๊ธฐ์  ๋ชจ๋ ๋จ๋ฐฉํฅ ํต์ ์ด๋ผ๋ ํ๊ณ๋ฅผ ๊ฐ์ง๋ค.


## ๐ WebSocket
***
- ์ต์ด์ ํธ๋์์ดํฌ๋ก ์ฐ๊ฒฐ์ด ์ด๋ฃจ์ด์ง๋ค.
- http์ ๋ง์ฐฌ๊ฐ์ง๋ก 80, 443 ํฌํธ๋ฅผ ์ฌ์ฉํ์ฌ ์๋ฐฉํฅ ํต์ ์ ํ๋ค.
- ํ๋์ URL์ ์ด์ฉํด ์ปค๋ฅ์ ๋๊ณ , ์ดํ์๋ ์ด๋ฅผ ํตํด ํต์ ํ๋ค.
- ๋ฏธ์ง์๋๋ ๋ธ๋ผ์ฐ์ ๊ฐ ์กด์ฌํ๊ธฐ ๋๋ฌธ์ ๋ณดํต ์ง์ํ๋ ๋ธ๋ผ์ฐ์ ์์๋ WebSocket์ ์ฌ์ฉํ๊ณ , ์ด์ธ์๋ Long Pooling๋ฐฉ์์ด ์ ์ฉ๋๋๋ก ํ๋ค.


## ๐ Spring Boot์ WebSocket
****
- spring-boot-starter-websocket
- Fallback ๋ฉ์๋์ SockJS ์ฌ์ฉ. (๋ธ๋ผ์ฐ์  ๋ฏธ์ง์ ๋ฑ)
- STOMP(Simple Text Oriented Messaging Protocol) : ๋ฐ์ดํฐ ๊ตํ ๋ฐ ํ์ ์ง์ ํ๋ ๋ฉ์์ง ํ๋กํ ์ฝ
    > ํน์  ํด๋ผ์ด์ธํธ์๊ฒ๋ง ๋ฉ์์ง๋ฅผ ์ ์กํ๋ ๋ฑ์ ์ญํ .

```java
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // WebSocket์ ์ฌ์ฉํ  ์ ์๋ ๊ฒฝ์ฐ FallBack ์ต์ ํ์ฑํ.
        registry.addEndpoint("/ws").withSockJS();
    }
    
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        //message handling methods๋ก ๋ผ์ฐํ (Controller)
        registry.setApplicationDestinationPrefixes("/app");

        //๊ฐ๋จํ ์ธ๋ฉ๋ชจ๋ฆฌ ๊ธฐ๋ฐ. message broker๋ก ๋ผ์ฐํ. (send ๋ชฉ์ ์ง)
        registry.enableSimpleBroker("/topic");
    }
}
```

```java
@MessageMapping("/hello")
@SendTo("/topic/greetings")
public Greeting greeting(HelloMessage message){
    return new Greeting("Hello " + HtmlUtils.htmlEscape(message.getName()) +"!");
}
```
- "/app/hello"๋ก ๋ณด๋ด์ง ๋ฐ์ดํฐ ์์ .
- "/topic/greetings"๋ก  Greeting ๊ฐ์ฒด ์ ๋ฌ.

```javascript
function connect() {
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
}
```
- config์์ SockJs์ ์๋ํฌ์ธํธ๋ก ์์ผ ์์ฑ.
- subscribe() : '/topic/greetings' ๋ก ๋ธ๋ก๋์ผ์คํธ ๋๋ ๋ฉ์์ง ์์ 

```javascript
function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}
```
- "/app/hello" ๋ก ๋ฉ์์ง ์ ์ก. JSON ํ์.

```javascript
function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}
```
- ์์ ํ ๋ฐ์ดํฐ๋ฅผ ๊ฐ์ง๊ณ  HTML Element ์์ฑ


# ๐ง ๋ค์๊ฐ ๋ฐ์ดํฐ ์ ์ก
*****
## 1.Mesh Networking
- ์ค์ ์๋ฒ ์์ด Peer๊ฐ ์๋ก ๋ฐ์ดํฐ๋ฅผ ์ฃผ๊ณ  ๋ฐ์, ์ฐธ์ฌ์๊ฐ ๋ง์์ง ์๋ก ํฐ ๋ถ๋ด.

## 2. SFU : Selective Forwarding Unit
- ์ค์ ์๋ฒ๋ฅผ ํตํด ํธ๋ํฝ์ ์ค๊ณ.
- ํด๋ผ์ด์ธํธ๋ก๋ถํฐ ๋ฏธ๋์ด ์คํธ๋ฆผ์ ๋ฐ์ ์กฐ๊ฑด์ ๋ง๊ฒ ์ ํ์ ์ผ๋ก ๊ทธ๋๋ก ์ ๋ฌ.
- ๋น๊ต์  ์๋ฒ ๋ถํ, ์ง์ฐ์๊ฐ์ด ๋ฎ์.

## 3. MCU : Multi Point Control Unit
- ๊ฐ ํด๋ผ์ด์ธํธ์ ๋ฏธ๋์ด ์คํธ๋ฆผ์ ์ค์์์ ์ฒ๋ฆฌ.(๊ฐ๊ณต)
- ์ฒ๋ฆฌ๋ ๊ฒฐ๊ณผ๋ฌผ์ ๋ค์ ํด๋ผ์ด์ธํธ์๊ฒ ์ ๋ฌ. 
- ํด๋ผ์ด์ธํธ์ ๋คํธ์ํฌ ๋ถ๋ด์ ๋งค์ฐ ์ ์ด์ง์ง๋ง ์ค์์๋ฒ์ CPU ๋ถ๋ด์ด ์ฆ๊ฐ.

# ๐ง WebRTC(Web Real-Time Communication)
*****
> - ํ๋ฌ๊ทธ์ธ์ ๋์ ์์ด ์ํธ ํต์ ํ  ์ ์๋๋ก ์ค๊ณ๋ API
> - peer to peer (P2P)ํต์ ์ ์ต์ ํ.
> - 

1. MediaStream class (GetUserMedia) : ์นด๋ฉ๋ผ/๋ง์ดํฌ ๋ฑ์ ๋ฐ์ดํฐ ์คํธ๋ฆผ ์ ๊ทผ.
2. RTCPeerConnection class : Peer๊ฐ์ ์ฐ๊ฒฐ ์์ฑ, ์ค๋์ค, ๋น๋์ค ํต์ .
3. RTCDataChannel class: Peer ๊ฐ์ ์ผ๋ฐ์ ์ธ ๋ฐ์ดํฐ ํต์ .(Chrome 25๋ฒ์ , Opera 18๋ฒ์ , Firefox 22๋ฒ์  ์ด์์์ ์ง์)

## ๐ P2P ๊ธฐ๋ฐ ๋ฐฉํ๋ฒฝ ํต๊ณผ

- ### STUN : Session Traversal Utilities for NAT
- P2P IP ์ฐ๊ฒฐ์ ์ํ ์ ๋ณด ์ ๊ณต, ์ข๋จ์ ์ ๊ทผ ๊ฐ๋ฅํ IP, Port๋ฅผ ๋ฐ๊ฒฌํ๋ ์์. 
- ํ์ฌ ํด๋ผ์ด์ธํธ์ Public IP๋ฅผ ๋ฐํํ๋ ์ญํ ์ ํ๋ ์๋ฒ.

- ### TURN : Traversal Using Relays around NAT
- Peer ๊ฐ ์ง์  ํต์ ์ด ์คํจํ  ๊ฒฝ์ฐ ์ข๋จ์ฌ์ด์ ์ฐ๊ฒฐ์ ์ํํ๋ TURN ์๋ฒ๋ฅผ ์ฌ์ฉ.
- Peer ๊ฐ์ ๋ฏธ๋์ด ์คํธ๋ฆฌ๋ฐ์ ์ฐ๊ฒฐํ๊ธฐ ์ํด ์ฌ์ฉ.
- TURN ์ ๊ณต์ฉ ์ฃผ์๋ฅผ ๊ฐ์ง๋ค.
 > 1. ํด๋ผ์ด์ธํธ๋ TURN ์๋ฒ์ ํ ๋น ์์ฒญ์ ๋ณด๋ธ๋ค.
 > 2. ์๋ฒ๋ ํ ๋น๋ ์ฃผ์๋ฅผ ์๋ต์ผ๋ก ๋ณด๋ธ๋ค.
 > 3. ํด๋ผ์ด์ธํธ๋ ํต์ ํ๊ณ ์ ํ๋ Peer ์๊ฒ ์ด ์ฃผ์๋ฅผ ์ ๋ฌ.
 > 4. Peer๋ ํด๋น ์ฃผ์๋ก TURN ์๋ฒ์ ์ ๊ทผ. P2P ์ฐ๊ฒฐ์ ๋งบ๋๋ค.
 
- ### ICE : Interactive Connectivity Establishment
- Peer ๊ฐ ํต์ ์ ์ํด STUN, TURN ๊ณผ ๊ฐ์ ๊ธฐ์ ๋ก ์ต์ ์ ๋ผ์ฐํ ๊ฒฝ๋ก๋ฅผ ์ฐพ์๋ด๋ ๊ธฐ์ .

- ### Signaling
> - ํต์  ์กฐ์  ํ๋ก์ธ์ค
> - WebRTC ์์ 'call'์ ์ด๊ธฐํํ๊ธฐ ์ํด์ ํ์ํ ์ ๋ณด๊ตํ์ ์ํด ํ์.
- ํต์ ์ ์ด๊ณ  ๋ซ๋๋ฐ ์ฌ์ฉ๋๋ ์ธ์ ์ปจํธ๋กค ๋ฉ์ธ์ง
- ์๋ฌ ๋ฉ์ธ์ง
- ์ฝ๋ฑ์ด๋ ์ฝ๋ฑ ์ค์ , ๋์ญํญ, ๋ฏธ๋์ด ํ์ ๊ฐ์ ๋ฏธ๋์ด ๋ฉํ๋ฐ์ดํฐ.
- ๋ณด์ ์ฐ๊ฒฐ์ ์๋ฆฝํ๊ธฐ ์ํด ์ฌ์ฉ๋๋ ํค ๋ฐ์ดํฐ.
- ํธ์คํธ์ IP ์ฃผ์์ ํฌํธ์ ๊ฐ์ ๋คํธ์ํฌ ๋ฐ์ดํฐ.




Google Code Labs : https://github.com/googlecodelabs/webrtc-web 
## ๐ ์น์บ , ๋ง์ดํฌ ์ ๊ทผ
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
- local stream์ ๋น๋์ค ํ๊ทธ์ src๋ก ๋ฃ๋๋ค.



## ๐ ๋ฐ์ดํฐ ์ ์ก. RTCPeerConnection, DataChannelSend
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
 2. remoteConnection์ local Candidate ์ถ๊ฐ.
 3. localConnection์๋ remote Candidate ์ถ๊ฐ
 
```javascript
 var data = dataChannelSend.value;
    sendChannel.send(data);
```
- WebSocket ์ฒ๋ผ send๋ฅผ ์ด์ฉํ์ฌ ๋ฐ์ดํฐ ์ ์ก.
![img_2.png](img_2.png)



# ๐ง WebRTC With Spring
***
## 1. Signal Server
 
- ### WebSocketConfig
```java
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new SignalHandler(), "/signal")
                .setAllowedOriginPatterns("*");
    }
}
```

- ### Handler
```java
@Slf4j
@Component
public class SignalHandler extends TextWebSocketHandler {

    List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    // send ๋ฐ์.
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        for (WebSocketSession webSocketSession : sessions) {
            if (webSocketSession.isOpen() && !session.getId().equals(webSocketSession.getId())) {
                webSocketSession.sendMessage(message);
            }
        }
    }
    
    // ํด๋ผ์ด์ธํธ ์ ์์ ๋ฐ์
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
    }

    // ํด๋ผ์ด์ธํธ ์ ์์ข๋ฃ์ ๋ฐ์
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
    }
}
```
- Session ์ ๋ณด   
![img.png](img.png)

- Message Payload    
![img_1.png](img_1.png)    
  ๐ SDP : Session Description Protocol, ํด๋ผ์ด์ธํธ ๊ฐ์ ๋ฉํ๋ฐ์ดํฐ์ ๋์์ ์ฌ์ฉ.

## 2. Client

### ๐์ฐ๊ฒฐ ๊ณผ์ 
***
```java
var conn = new WebSocket('ws://localhost:8080/signal');
conn.onopen = function() {
    console.log("Connected to the signaling server");
    initialize();

};
```
- connection ์์ฑ
```javascript
function initialize() {

    // STUN, TURN ๊ตฌ์ฑ ์ ๋ฌ.
    var configuration = null;
    peerConnection = new RTCPeerConnection(configuration);
    // Setup ice handling

    peerConnection.onicecandidate = function(event) {
        if (event.candidate) {
            send({
                event : "candidate",
                data : event.candidate
            });

        }

    };
    
    // creating data channel
    dataChannel = peerConnection.createDataChannel("dataChannel", {
        reliable : true
    });

    dataChannel.onerror = function(error) {
        console.log("Error occured on datachannel:", error);
    };

    // when we receive a message from the other peer, printing it on the console
    dataChannel.onmessage = function(event) {
        console.log("message:", event.data);
    };

    dataChannel.onclose = function() {
        console.log("data channel is closed");
    };

    peerConnection.ondatachannel = function (event) {
        dataChannel = event.channel;
    };

}

function send(message) {
    conn.send(JSON.stringify(message));
}
```
- RTCPeerConnection๋ฅผ ์์ฑํ๊ณ  ice ๋ฐ์ ์ด๋ฒคํธ ๋ฆฌ์ค๋ ๋ฑ๋ก.
- dataChannel ์์ฑ, ์ด๋ฒคํธ ๋ฆฌ์ค๋ ๋ฑ๋ก.
  
```javascript
function handleOffer(offer) {
    peerConnection.setRemoteDescription(new RTCSessionDescription(offer));

    // create and send an answer to an offer
    peerConnection.createAnswer(function(answer) {
        peerConnection.setLocalDescription(answer);
        send({
            event : "answer",
            data : answer
        });
    }, function(error) {
        alert("Error creating an answer");
    });

};
```
```javascript
function handleCandidate(candidate) {
    peerConnection.addIceCandidate(new RTCIceCandidate(candidate));
};
```
- ์๋ก์ด ํด๋ผ์ด์ธํธ๊ฐ offer ๋ฅผ ๋ณด๋ด๋ฉด ์๋ฒ์์ ํด๋ผ์ด์ธํธ๋ค์๊ฒ offer ๋ฅผ ์ ๋ฌํ๊ณ ,
  ํด๋ผ์ด์ธํธ๋ ์ ๋ฌ๋ฐ์ offer ๋ฅผ remoteDescription์ ๋ฑ๋ก, ์ด ๋ onicecandidate ๋ฐ์. candidate message ์ฃผ๊ณ ๋ฐ์.
- offer ๋ฅผ ๋ฐ์ ํด๋ผ์ด์ธํธ๋ค์ answer์ ๋ณด๋.

```javascript
function handleAnswer(answer) {
    peerConnection.setRemoteDescription(new RTCSessionDescription(answer));
    console.log("connection established successfully!!");
};
```
- ์๋ก์ด ํด๋ผ์ด์ธํธ๋ answer๋ฅผ ์์ ํ๊ณ  ์ด๋ฅผ RemoteDescription์ ์ค์ .

### ๐ ๋ฐ์ดํฐ ์ ์ก
> ์์ ๊ณผ์ ์ผ๋ก peer๊ฐ ์ฐ๊ฒฐ์ด ์๋ฃ๋จ.

```javascript
function sendMessage() {
    dataChannel.send(input.value);
    input.value = "";
}
```
- dataChannel.send()๋ฅผ ์ด์ฉํด ๋ฐ์ดํฐ ์ ์ก.

```javascript
dataChannel.onmessage = function(event) {
    console.log("message:", event.data);
};
```
-init์ ์ค์ ํด๋ onmessage ์ด๋ฒคํธ ๋ฆฌ์ค๋.

# ๐ PearJS ๋ฅผ ์ด์ฉํ ํ์์ฑํ

- ### WebSocketHandler
```java
@RequiredArgsConstructor
@Slf4j
@Component
public class SignalHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private Map<String, Room> rooms = new HashMap<>();

    List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
        Message message = objectMapper.readValue(textMessage.getPayload(), Message.class);
        String peerId = message.getPeerId();
        String streamId = message.getStreamId();

        switch (message.getType()){
            case "join" :
                Room room = rooms.get(message.getRoomId());
                if(room == null){
                    log.info("Create Room! " + message.getRoomId());
                    room = new Room();
                    room.setRoomId(message.getRoomId());
                    room.getClient().put(message.getUserSessionId(), session);
                    rooms.put(message.getRoomId(), room);
                }
                else {
                    log.info("Join Room! " + message.getRoomId());
                    room.getClient().put(message.getUserSessionId(), session);

                    for (WebSocketSession client : room.getClient().values()) {
                        if (!client.getId().equals(session.getId())) {
                            message.setType("user-connected");
                            TextMessage connectedMessage = new TextMessage(objectMapper.writeValueAsString(message));

                            client.sendMessage(connectedMessage);
                            log.info("send User Connected Message");
                        }
                    }
                }
                break;
        }
    }
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("connect : " + session.getId());
        sessions.add(session);
        Message message = new Message();
        message.setType("connect");
        message.setUserSessionId(session.getId());

        TextMessage textMessage = new TextMessage(objectMapper.writeValueAsString(message));
        session.sendMessage(textMessage);

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
    }
}
```
1. ์๋ก์ด ํผ์ด๊ฐ ์ ์์ ์ธ์์ ์ ์ฅ. ๊ฐ๋จํ๊ฒ ๊ตฌํํ๊ธฐ ์ํด ๊ทธ๋ฅ ์๋ฒ์ ์ํ๋ก ์ ์ฅํจ.
2. ํผ์ด๊ฐ ๋ฐฉ์ ์์ฅ์ ์๋ก์ด ๋ฐฉ์ด๋ฉด Create, ๊ธฐ์กด์ ๋ฐฉ์ด๋ฉด Joinํ๊ณ  ๊ธฐ์กด์ ์ ์ ๋ค์๊ฒ ์๋ฆฌ๊ณ  ์ ๋ณด๋ฅผ ์ ๋ฌ.


```javascript
const messageGrid = document.querySelector('#messageGrid');
const videoGrid = document.querySelector('#videoGrid');
const localVideo = document.querySelector('#localVideo');
let localStream;

const socket = new WebSocket('ws://localhost:8080/signal');
const peer = new Peer();
let localPeerId;
let roomId = "roomId";
let userId = "";
let username = "username"
let streamId = ""

// ๋ด ๋น๋์ค
navigator.mediaDevices.getUserMedia({
    video: true,
    audio: false,
}).then((mediaStream)=>{
    streamId =mediaStream.id;
    localStream = mediaStream;
    localVideo.srcObject = mediaStream;

});

// ์์บฃ ์ฐ๊ฒฐ์.
socket.onopen = function() {
    console.log("Connected to the signaling server");
};

//์๋ฒ์์ ๋ฉ์์ง ์์ .
socket.onmessage = function (message) {
    console.log(message.data)
    let msg = JSON.parse(message.data);
    let type = msg.type;
    console.log("onmessage : "+ type);

    switch (type){
        case "connect" :
            userId = msg.userSessionId;
            console.log("Connected User : "+userId);
            break;

        case "user-connected" :
            const mediaConnection = peer.call(msg.peerId, localStream);
            console.log("Call")
            const newVideo = document.createElement("video");
            newVideo.setAttribute("autoplay", "playsinline");

            mediaConnection.on("stream", (newStream)=>{
                newVideo.srcObject = newStream;
                videoGrid.append(newVideo)
            })
            break;
    }
}

//peer ์์ฑ์.
peer.on("open", (peerId)=>{
    localPeerId = peerId;
    sendMessage({
        type: "join",
        roomId : roomId,
        userSessionId : userId,
        peerId : localPeerId,
        streamId : streamId
    });

});

// ๋ค๋ฅธ ํผ์ด๊ฐ ์ฐ๊ฒฐ์ ์์ฒญ
peer.on("call", (mediaConnection)=>{
    console.log("on call : " +  mediaConnection)
    mediaConnection.answer(localStream);
    const newVideo = document.createElement("video");
    newVideo.setAttribute("autoplay", "playsinline");

    mediaConnection.on("stream", (newStream)=>{
        newVideo.srcObject = newStream;
        videoGrid.append(newVideo);
    })

    //TODO ์ฐ๊ฒฐ close
    mediaConnection.on("close",()=>{

    });
});


function sendMessage(msg){
    socket.send(JSON.stringify(msg));
}

//TODO ์์ผ์์ user-connected ์ด๋ฒคํธ๋ฅผ ๋ฐ์ (peerId, username, streamId) room์ ๋ฑ๋ก.





```
1. onopen : ์๋ก์ด ํผ์ด๊ฐ ์ฐ๊ฒฐ๋  ๋ ์์ ์ ์ ๋ณด๋ฅผ ์๋ฒ๋ก ์ ์ก.
2. ์๋ฒ์์๋ ์๋ก์ด ํผ์ด์ ์ ๋ณด๋ฅผ ๊ธฐ์กด์ ํผ์ด๋ค์๊ฒ ์ ์ก.
3. user-connected : ์๋ก์ด ํผ์ด๊ฐ ์ฐ๊ฒฐ๋  ๋ ๋น๋์ค ์์ฑ.
3. oncall : ๊ธฐ์กด์ ํผ์ด๋ค์ ์๋ก์ด ํผ์ด์ ์ ๋ณด์ ๋ฏธ๋์ด ์คํธ๋ฆผ์ ์์ , ๋น๋์ค๋ฅผ ๋ง๋ค๊ณ , ์์ ์ ์คํธ๋ฆผ์ ์๋ก์ด ํผ์ด์๊ฒ ์๋ต๊ณผ ํจ๊ป ๋๋ ค์ค.(answer)

