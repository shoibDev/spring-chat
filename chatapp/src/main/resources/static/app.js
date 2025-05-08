let stompClient = null;
let sender = null;
let roomId = null;

async function fetchRooms() {
    const res = await fetch("/api/rooms");
    const data = await res.json();

    console.log("Fetched rooms:", data); // 🧠 Check what you actually got

    if (!Array.isArray(data)) {
        console.error("Expected array but got:", data);
        return;
    }

    const select = document.getElementById("roomSelect");
    data.forEach(room => {
        const option = document.createElement("option");
        option.value = room.id;
        option.textContent = room.name;
        select.appendChild(option);
    });
}

async function enterChat() {
    sender = document.getElementById("username").value.trim();
    roomId = document.getElementById("roomSelect").value;

    if (!sender || !roomId) {
        alert("Please enter your name and select a room.");
        return;
    }

    // Hide setup and show modal
    document.getElementById("setup-container").style.display = "none";
    document.getElementById("chat-modal").classList.add("active");

    document.getElementById("roomTitle").textContent = "Room #" + roomId;

    await loadHistory();
    connect();
}


function leaveChat() {
    if (stompClient && stompClient.connected) {
        stompClient.publish({
            destination: `/app/room/${roomId}`,
            body: JSON.stringify({
                sender: sender,
                type: "LEAVE"
            })
        });

        stompClient.deactivate(); // clean disconnect
    }

    // Hide modal and show setup again
    document.getElementById("chat-modal").classList.remove("active");
    document.getElementById("setup-container").style.display = "block";

    // Reset chat state
    document.getElementById("chat").innerHTML = "";
    document.getElementById("message").value = "";
}

async function loadHistory() {
    const res = await fetch(`/api/chat/room/${roomId}/history`);
    const messages = await res.json();
    messages.reverse().forEach(msg => showMessage(JSON.stringify(msg)));
}

function connect() {
    const socket = new SockJS('/ws');
    stompClient = new StompJs.Client({
        webSocketFactory: () => socket,
        reconnectDelay: 5000
    });

    stompClient.onConnect = () => {
        stompClient.subscribe(`/topic/room/${roomId}`, (message) => {
            showMessage(message.body);
        });

        // Send JOIN
        stompClient.publish({
            destination: `/app/room/${roomId}`,
            body: JSON.stringify({
                sender: sender,
                type: "JOIN"
            })
        });
    };

    stompClient.activate();
}

function sendMessage() {
    const msg = document.getElementById("message").value;
    if (stompClient && stompClient.connected) {
        stompClient.publish({
            destination: `/app/room/${roomId}`,
            body: JSON.stringify({
                sender: sender,
                message: msg,
                type: "CHAT"
            })
        });
        document.getElementById("message").value = "";
    }
}

function showMessage(msgJson) {
    const msg = JSON.parse(msgJson);
    let content = "";

    switch (msg.type) {
        case "JOIN":
            content = `${msg.sender} joined the chat.`;
            break;
        case "LEAVE":
            content = `${msg.sender} left the chat.`;
            break;
        case "CHAT":
            content = `${msg.sender}: ${msg.message}`;
            break;
        default:
            content = "[Unknown message type]";
    }

    const chat = document.getElementById("chat");
    const div = document.createElement("div");
    div.className = "chat-message";
    div.textContent = content;
    chat.appendChild(div);
}

window.addEventListener("beforeunload", () => {
    if (stompClient && stompClient.connected) {
        stompClient.publish({
            destination: `/app/room/${roomId}`,
            body: JSON.stringify({
                sender: sender,
                type: "LEAVE"
            })
        });
    }
});

document.addEventListener("DOMContentLoaded", fetchRooms);
