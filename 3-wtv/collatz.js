function nextCollatz(n) {
    return n % 2 == 0 ? Math.floor(n / 2) : n * 3 + 1;
}

function collatz(n) {
    if (n == 4) {
        return [4, 2, 1];
    } else {
        return [n, ...collatz(nextCollatz(n))];
        //return [n].concat(collatz(nextCollatz(n)))
    }
}

const collatzForm = document.querySelector("#collatz");

collatzForm.addEventListener("submit", event => {
    event.preventDefault();
    const n = Number(collatzForm.elements["num"].value);
    document.querySelector("#collatz-message").textContent = collatz(n);
});