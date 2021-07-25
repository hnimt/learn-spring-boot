function getCookie(cname) {
    let name = cname + "=";
    let ca = document.cookie.split(";");
    for (let i = 0; i < ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) == " ") {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function changeElementByCookies() {
    let bgColor = getCookie("bgColor");
    let fontColor = getCookie("fontColor");
    let fontSize = getCookie("fontSize");
    document.body.style.backgroundColor = bgColor;
    document.body.style.color = fontColor;
    document.body.style.fontSize = fontSize + "px";
}
