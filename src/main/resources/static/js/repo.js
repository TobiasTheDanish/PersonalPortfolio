let readmeContainer = document.getElementsByClassName("read-me-container").item(0);

let preTags = readmeContainer.getElementsByTagName("pre");

for (let i = 0; i < preTags.length; i++) {
    let tag = preTags.item(i);
    let copyIcon = document.createElement("i");
    copyIcon.classList.add("copy-icon");
    copyIcon.classList.add("fa-regular");
    copyIcon.classList.add("fa-copy");
    let clipboardCopy = document.createElement("button");
    clipboardCopy.classList.add("copy-btn");
    clipboardCopy.onclick = function() { copyToClipboard(tag.children.item(0), copyIcon); }

    clipboardCopy.appendChild(copyIcon);

    tag.parentNode.appendChild(clipboardCopy);
    tag.parentElement.classList.add("code-container");
}

function copyToClipboard(element, icon) {
    let textToCopy = element.innerText;

    navigator.clipboard.writeText(textToCopy).then(() => {
        icon.parentElement.classList.add("copied");
        icon.classList.remove("fa-copy");
        icon.classList.add("fa-circle-check");
    });

    setTimeout(() => {
        icon.parentElement.classList.remove("copied");
        icon.classList.remove("fa-circle-check");
        icon.classList.add("fa-copy");
    }, 2500);
}