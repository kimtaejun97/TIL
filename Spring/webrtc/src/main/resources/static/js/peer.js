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

// 내 비디오
navigator.mediaDevices.getUserMedia({
    video: true,
    audio: false,
}).then((mediaStream)=>{
    streamId =mediaStream.id;
    localStream = mediaStream;
    localVideo.srcObject = mediaStream;

});

// 소캣 연결시.
socket.onopen = function() {
    console.log("Connected to the signaling server");
};

//서버에서 메시지 수신.
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

        case "leave" :
    }
}

//peer 생성시.
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

// 다른 피어가 연결을 요청
peer.on("call", (mediaConnection)=>{
    console.log("on call : " +  mediaConnection)
    mediaConnection.answer(localStream);
    const newVideo = document.createElement("video");
    newVideo.setAttribute("autoplay", "playsinline");

    mediaConnection.on("stream", (newStream)=>{
       newVideo.srcObject = newStream;
       videoGrid.append(newVideo);
    })

    //TODO 연결 close
    mediaConnection.on("close",()=>{

    });
});


function sendMessage(msg){
    socket.send(JSON.stringify(msg));
}

//TODO 소켓에서 user-connected 이벤트를 받음 (peerId, username, streamId) room에 등록.




