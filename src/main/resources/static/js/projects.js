const timeline = document.getElementById('timeline');
const timelineItems = document.getElementsByClassName('timeline-item');

for (let i = 0; i < timeline.children.length; i++) {
    let currentTimelineItem = timeline.children.item(i);
    if (i%2===0) {
        currentTimelineItem.children.item(0).classList.add("direction-l");
    } else {
        currentTimelineItem.children.item(0).classList.add("direction-r");
    }
}

for (let i = 0; i < timelineItems.length; i++) {
    let current = timelineItems.item(i);
    let currentDate = current.getElementsByClassName('t-item-date').item(0);
    let currentDescription = current.getElementsByClassName('t-item-text').item(0);
    let currentDateText = currentDate.innerText;
    let currentText = currentDescription.innerText;
    let tWords = currentText.split(' ');
    let dWords = currentDateText.split(' ');

    currentDate.innerHTML = "";
    currentDescription.innerHTML = "";

    for (let j = 0; j < dWords.length; j++) {
        let delay = 40 * j;
        let span = document.createElement("span");
        span.innerText = dWords[j];
        span.classList.add("t-item-word")
        span.style.cssText += `transition-delay: ${delay}ms;`;

        currentDate.appendChild(span);
    }

    for (let j = dWords.length; j < dWords.length + tWords.length; j++) {
        let delay = 60 * j;
        let span = document.createElement("span");
        span.innerText = tWords[j - dWords.length];
        span.classList.add("t-item-word")
        span.style.cssText += `transition-delay: ${delay}ms;`;

        currentDescription.appendChild(span);
    }
}

function navigate(reponame) {
    let url = new URL(window.location.href);
    url.pathname = "/repo";

    url.search = "name=" + reponame;

    window.location.assign(url.href);
}