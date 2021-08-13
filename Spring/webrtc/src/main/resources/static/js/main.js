const localVideo = document.querySelector("#localVideo");
const videoGrid = document.querySelector("#videoGrid");
let myStream =null;
let streamId = "";
let username ="";
let url = "";
const socketUrl = "/ws"
let stompClient = "";
var main = {
    init: function () {
        $('#btn-start').on('click', function () {
            this.loadVideoStream();
        })
    },

    loadVideoStream: function () {
        socket = new SockJs(socketUrl);
        stompClient = Stomp.over(socket);

        let peer = new Peer();

        navigator.mediaDevices.getUserMedia({
            video: true,
            audio: false,
        }).then((stream) => {
            streamId = stream.id;
            myStream = stream;
            localVideo.srcObject = mediaStream;

            peer.on("open", function () {
                let data = '{"url": url, "sender": username, "streamId": streamId, "type": "CREATE"}';
                stompClient.send(data)
            });

            peer.on("call", function (mediaConnection) {
                mediaConnection.answer(stream);
                const newVideo = document.createElement("video");
                newVideo.setAttribute("autoplay", "playsinline");
                videoGrid.append(newVideo);
            })
        });

    },
}



main.init();