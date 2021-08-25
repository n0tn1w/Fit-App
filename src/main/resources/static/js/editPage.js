function displayExercises() {
    let div= document.getElementById('exercise-div');

    if(div.hasAttribute("hidden")){
        div.removeAttribute("hidden");
    }else{
        div.setAttribute("hidden", true);
    }
}