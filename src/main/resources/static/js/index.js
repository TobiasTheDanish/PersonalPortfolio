const timeline = document.getElementById('timeline');

for (let i = 0; i < timeline.children.length; i++) {
    let currentTimelineItem = timeline.children.item(i);
    if (i%2===0) {
        currentTimelineItem.children.item(0).classList.add("direction-l");
    } else {
        currentTimelineItem.children.item(0).classList.add("direction-r");
    }
}


function navigate(reponame) {
    let url = new URL(window.location.href);
    url.pathname = "/Repo";

    url.search = "name=" + reponame;

    window.location.assign(url.href);
}