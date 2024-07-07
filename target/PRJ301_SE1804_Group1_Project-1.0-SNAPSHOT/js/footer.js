var chatboxBody = document.querySelector('.chatbox-body');
chatboxBody.scrollTop = chatboxBody.scrollHeight;

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