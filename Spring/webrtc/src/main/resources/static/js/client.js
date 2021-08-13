//connecting to our signaling server
const conn = new WebSocket('ws://localhost:8080/signal');
const messageGrid = document.querySelector('#messageGrid');
const videoGrid = document.querySelector('#videoGrid');
const localVideo = document.querySelector('#localVideo');
let localStream;

const mediaStreamConstraints = {
    video: true,
};


conn.onopen = function() {
    console.log("Connected to the signaling server");
    initialize();
};





conn.onmessage = function(msg) {
    console.log("Got message", msg.data);
    var content = JSON.parse(msg.data);
    var data = content.data;
    switch (content.event) {
        // when somebody wants to call us
        case "offer":
            handleOffer(data);
            break;
        case "answer":
            handleAnswer(data);
            break;
        // when a remote peer sends an ice candidate to us
        case "candidate":
            handleCandidate(data);
            break;
        default:
            break;
    }
};

function send(message) {
    conn.send(JSON.stringify(message));
}

var peerConnection;
var dataChannel;
var input = document.getElementById("messageInput");

function initialize() {

    // STUN, TURN 구성 전달.
    var configuration = {
        "iceServers" : [ {
            "url" : "stun:stun2.1.google.com:19302"
        } ]
    };

    peerConnection = new RTCPeerConnection(configuration);

    //내 비디오
    navigator.mediaDevices.getUserMedia({
        video: true,
        audio: true,
    }).then((mediaStream)=>{
        localStream = mediaStream;
        localVideo.srcObject = mediaStream
        peerConnection.addStream(mediaStream);

    });

    // Setup ice handling

    peerConnection.onicecandidate = function (event) {
        if (event.candidate) {
            send({
                event: "candidate",
                data: event.candidate
            });

        }

    };



    peerConnection.onaddstream = function (event) {
        let newVideo = document.createElement('video');
        newVideo.setAttribute("autoplay", "playsinline")
        newVideo.srcObject = event.stream;

        videoGrid.append(newVideo);

    }


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
        $("#messageGrid").append("<p>"+ "상대방: " +event.data+"</p>");
    };

    dataChannel.onclose = function() {
        console.log("data channel is closed");
    };

    peerConnection.ondatachannel = function (event) {
        dataChannel = event.channel;
    };

}

function createOffer() {
    peerConnection.createOffer(function(offer) {
        send({
            event : "offer",
            data : offer
        });
        peerConnection.setLocalDescription(offer);
    }, function(error) {
        alert("Error creating an offer");
    });
}

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

function handleCandidate(candidate) {
    peerConnection.addIceCandidate(new RTCIceCandidate(candidate));
};

function handleAnswer(answer) {
    peerConnection.setRemoteDescription(new RTCSessionDescription(answer));
    console.log("connection established successfully!!");
};

function sendMessage() {
    dataChannel.send(input.value);
    $("#messageGrid").append("<p>" + "나: " + input.value + "</p>");

    input.value = "";
}
