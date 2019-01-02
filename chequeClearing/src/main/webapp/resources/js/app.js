var stompClient = null;

function connect() {
    var socket = new SockJS('../socketEndpoint');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/notification', function (message) {
            console.log(message.body)
        });
    });
}


$(function () {
    connect();
});