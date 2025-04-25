console.log("If you can see this message, then I have been loaded along with my html bestie");

function hyphenate(word) {
    return word.split(" ").join("-");
}

function addIdsToHeadings() {
    document.querySelectorAll("h1, h2, h3, h4, h5, h6").forEach(addId);
}

const addId = (heading) => {
    heading.setAttribute("id", hyphenate(heading.textContent));
};

function createToc() {
    var toc = document.createElement("section");
    var ol = document.createElement("ol");
    document.querySelectorAll("h2").forEach(heading => {
        var li = document.createElement("li");
        var a = document.createElement("a");
        a.setAttribute("href", `#${heading.id}`);
        a.textContent = heading.textContent;
        li.appendChild(a);
        ol.appendChild(li);
    });
    toc.appendChild(ol);
    var firstSection = document.querySelector("section");
    firstSection.insertAdjacentElement('beforebegin', toc);
}


function toggleCollapseRestOfSection(heading) {
    [...heading.parentElement.children].forEach(child => {
        if (child !== heading) {
            child.style.display === "none" ? 
                child.style.display = "block" : 
                child.style.display = "none";
        }
    });
}

function makeHeadingsCollapsible() {
    document.querySelectorAll("h1, h2, h3, h4, h5, h6")
    .forEach((h) => h.addEventListener("click", event => {
        toggleCollapseRestOfSection(event.target);
    }));
}


addIdsToHeadings();
createToc();
makeHeadingsCollapsible();