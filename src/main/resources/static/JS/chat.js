var stompClient = null;

function connect() {
    var socket = new SockJS('/chat'); // create webSocket connection using SockJS and connects to spring boot webSocket endpoint
    stompClient = Stomp.over(socket); // wraps the connection to send/receive formal messages
    
    // Browser is now connected to the server(springboot application)
    stompClient.connect({}, function(frame) {
        setConnected(true); // client to server msg transfer
        // server to client communication
        stompClient.subscribe('/topic/messages', function(message)  { // this function(message) is executed only runs when server pushes a message
            showMessage(JSON.parse(message.body));
        });
    });
}

function setConnected(connected) {
    document.getElementById('sendMessage').disabled = !connected;
}

function showMessage(message) {
    var chat = document.getElementById('chat');
    var messageElement = document.createElement('div');
    messageElement.textContent = message.sender + ' : ' + message.content;
    messageElement.className = "border-bottom mb-1";
    chat.appendChild(messageElement);
    chat.scrollTop = chat.scrollHeight;
}

function sendMessage() {
    var sender = document.getElementById('senderInput').value;
    var content = document.getElementById('messageInput').value;

    var chatMessage = {
        sender: sender,
        content: content
    };

    stompClient.send("/app/sendMessage", {}, JSON.stringify(chatMessage));
    document.getElementById('messageInput').value = '';
}

document.getElementById('sendMessage').onclick = sendMessage;
window.onload = connect; // when user opens the chat page connect() func runs
