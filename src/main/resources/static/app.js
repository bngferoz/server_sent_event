var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/stomp-endpoint');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (output) {
			const txt = output.body;
			const obj = JSON.parse(txt);
			document.getElementById("publicKey").value = obj.publicKey;
			document.getElementById("sigValue").value = obj.signature;
			
            showGreeting('Public Key: '+obj.publicKey+', Signature: '+obj.signature);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/chat", {}, JSON.stringify({'message': $("#name").val()}));
}
function verifyFnc() {
    alert("Calling Server Side method to verify signature with public key!");
    var publicKey = document.getElementById("publicKey").value;
    var sig = document.getElementById("sigValue").value;
    stompClient.send("/app/verify", {}, JSON.stringify({'message': publicKey+'@'+sig+'@'+$("#name").val()}));
        
}

function showGreeting(message) {
	alert(message);
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
    $("#verifyBtn").prop("disabled", false);
    
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
    $( "#verifyBtn" ).click(function() { verifyFnc(); });
});

