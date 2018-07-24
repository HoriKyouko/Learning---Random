var displayedImage = document.querySelector('.displayed-img');
var thumbBar = document.querySelector('.thumb-bar');

btn = document.querySelector('button');
var overlay = document.querySelector('.overlay');

/* Looping through images */
for(var i = 0 ; i < 5; i++){
  var newImage = document.createElement('img');
  var pic = "images/pic" + (i+1) + ".jpg";
  newImage.setAttribute('src', pic);
  thumbBar.appendChild(newImage);
  /* But one of the main things the loop does is to add an onclick handler to each image, using this code: */
  newImage.onclick = function(e){
    displayedImage.setAttribute("src", e.target.getAttribute("src"));
  }
  /* because that handler is added to each image, it is run every time an image is clicked. */
}

/* Wiring up the Darken/Lighten button */
btn.onclick = function(e){
  if(e.target.getAttribute("dark") === "light"){
    e.target.setAttribute("dark", "dark");
    e.target.textContent = "Darken";
    overlay.style.backgroundColor = "rgba(0, 0, 0, 0)";
  }
  else if(e.target.getAttribute("dark") === "dark" || e.target.getAttribute("dark") === null){
    e.target.setAttribute("dark", "light");
    e.target.textContent = "Lighten";
    overlay.style.backgroundColor = "rgba(0, 0, 0, 0.5)";
  }
}
