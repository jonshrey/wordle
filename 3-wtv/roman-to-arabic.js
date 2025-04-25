const ARABIC_VALUES = {
    'I': 1,
    'V': 5,
    'X': 10,
    'L': 50,
    'C': 100,
    'D': 500,
    'M': 1000
};

function toArabic(roman) {
    var regularized = roman.toUpperCase()
                            .replaceAll("IV", "IIII")
                            .replaceAll("IX", "VIIII")
                            .replaceAll("XL", "XXXX")
                            .replaceAll("XC", "LXXXX")
                            .replaceAll("CD", "CCCC")
                            .replaceAll("CM", "DCCCC")
                            .split("");
    return regularized.map(element => ARABIC_VALUES[element])
                .reduce((elem, acc) => elem + acc, 0);
}

const romanForm = document.querySelector("#roman-converter");

romanForm.addEventListener("submit", event => {
    event.preventDefault();
    const roman = romanForm.elements["num"].value;
    document.querySelector("#roman-message").textContent = toArabic(roman);
});