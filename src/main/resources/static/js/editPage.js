function displayExercises() {
    let div= document.getElementById('exercise-div');
    let button = document.getElementById('edit-button');

    if(div.hasAttribute("hidden")){
        div.removeAttribute("hidden");
        button.textContent = "-";
    }else{
        div.setAttribute("hidden", true);
        button.textContent = "+";
    }
}