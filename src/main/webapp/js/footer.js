var chatboxBody = document.querySelector('.chatbox-body');
scrollBarToBottom();

chatboxBody.addEventListener('scroll', (e) => {
    if (chatboxBody.scrollTop === 0) {
        loadmoreChat();
    }
    if (chatboxBody.scrollTop + chatboxBody.clientHeight >= 0.95 * chatboxBody.scrollHeight) {
        document.querySelector('.chat-notification').style.display = 'none';
    }
});

function scrollBarToBottom() {
    chatboxBody.scrollTo({
        top: chatboxBody.scrollHeight,
        behavior: 'smooth'
    });
    document.querySelector('.chat-notification').style.display = 'none';
}

let index = 1;

function loadmoreChat() {
    const loadingIcon = document.createElement('div');
    loadingIcon.className = 'loading-icon';
    chatboxBody.insertBefore(loadingIcon, chatboxBody.firstChild);

    fetch('/home/chat/loadmore?index=' + index++).then(response => {
        return response.text();
    }).then(response => {
        loadingIcon.remove();
        var previousScrollHeight = chatboxBody.scrollHeight;
        chatboxBody.innerHTML = response + chatboxBody.innerHTML;
        var newScrollHeight = chatboxBody.scrollHeight;
        chatboxBody.scrollTop = newScrollHeight - previousScrollHeight;
    }).catch(error => {
        loadingIcon.remove();
    });
}

function showChatbox() {
    document.querySelector('.chatbox-open-icon').classList.add('open');
    document.querySelector('.chatbox-container').classList.add('open');
}

function hideChatbox() {
    document.querySelector('.chatbox-open-icon').classList.remove('open');
    document.querySelector('.chatbox-container').classList.remove('open');
}

var emoji = document.querySelector('emoji-picker');

function showEmoji() {
    emoji.classList.contains('hidden') ? emoji.classList.remove('hidden') : emoji.classList.add('hidden');
}

document.querySelector('emoji-picker').addEventListener('emoji-click', event => {
    document.querySelector('#messageChat').value += event.detail.unicode;
    emoji.classList.add('hidden');
});