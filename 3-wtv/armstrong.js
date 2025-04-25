const COLORS = ["violet", "blue", "green", "yellow", "orange", "red"];

function getRandomColor() {
    return COLORS[Math.floor(Math.random() * COLORS.length)];
}

function numToDigits(num) {
    return [...num.toString()].map(elem => Number(elem));
}

function arraySum(arr) {
    var sum = 0;
    arr.forEach(item => sum += item);
    return sum;
}

function isArmstrong(num) {
    const digits = numToDigits(num);
    const len = digits.length;
    const powerDigits = digits.map(digit => Math.pow(digit, len));
    return arraySum(powerDigits) == num;
}

const form = document.querySelector("#armstrong-checker");
form.addEventListener("submit", event => {
    event.preventDefault();
    const num = Number(form.elements["num"].value);
    
    const elem = document.querySelector("#message");
    if (!num) {
        elem.textContent = "Please only input numbers!";
    } else {
        elem.textContent = `The number is ${isArmstrong(num) ? "" : "not"} armstrong`;
    }
});

document.querySelector("#funk").addEventListener("click", () => {
    document.body.style.backgroundColor = getRandomColor();
})