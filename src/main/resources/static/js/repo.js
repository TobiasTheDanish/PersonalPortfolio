let readmeContainer = document.getElementsByClassName("read-me-container").item(0);

let preTags = readmeContainer.getElementsByTagName("pre");

for (let i = 0; i < preTags.length; i++) {
    let tag = preTags.item(i);

    let copyIcon = document.createElement("i");
    copyIcon.classList.add("copy-icon");
    copyIcon.classList.add("fa-regular");
    copyIcon.classList.add("fa-copy");

    let checkIcon = document.createElement("i");
    checkIcon.classList.add("copy-icon");
    checkIcon.classList.add("fa-regular");
    checkIcon.classList.add("fa-circle-check");

    let clipboardCopy = document.createElement("button");
    clipboardCopy.classList.add("copy-btn");
    clipboardCopy.id = "copy-btn-" + i;
    clipboardCopy.appendChild(copyIcon);
    clipboardCopy.appendChild(checkIcon);
    clipboardCopy.onclick = function() { jqueryCopy(tag.children.item(0), i); };

    tag.parentNode.appendChild(clipboardCopy);
    tag.parentElement.classList.add("code-container");
}

function jqueryCopy(element, index) {
    let textToCopy = element.innerText;

    navigator.clipboard.writeText(textToCopy).then(() => {
        $(`#copy-btn-${index}`).toggleClass("copied");
        $(`#copy-btn-${index} > .copy-icon`).fadeToggle("fast");
    });

    setTimeout(() => {
        $(`#copy-btn-${index}`).toggleClass("copied");
        $(`#copy-btn-${index} > .copy-icon`).fadeToggle("fast");
    }, 2500);
}