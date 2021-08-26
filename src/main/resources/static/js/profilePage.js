let div= document.getElementById('info-div');
let button1 = document.getElementById('show-button1');
let button2 = document.getElementById('show-button2');

function displayInfo() {
    if(div.hasAttribute("hidden")){
        div.removeAttribute("hidden");
        button1.textContent = "-";
        button2.textContent = "-";
    }else{
        div.setAttribute("hidden", true);
        button1.textContent = "+";
        button2.textContent = "+";
    }
}

function cancelDisplay(){
    div.setAttribute("hidden", true);
    button1.textContent = "+";
    button2.textContent = "+";
}