// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/*
Functions to display picture gallery as a carousel
 */

var slideIndex=1;
showDivs(slideIndex)

function plusDivs(n) {
  showDivs(slideIndex += n);
}

function showDivs(n) {
  var i;
  var x = document.getElementsByClassName("mySlides");
  if (n > x.length) {slideIndex = 1}
  if (n < 1) {slideIndex = x.length}
  for (i = 0; i < x.length; i++) {
    x[i].style.display = "none";  
  }
  x[slideIndex-1].style.display = "block";  
}
function receiveComments(){
    fetch('/get-comment').then(response=>response.json()).then((comments)=>{
        const commentListElement = document.getElementById('message');
    commentListElement.innerHTML = '';
        comments.forEach((comment)=>{
            commentListElement.appendChild(
            createListElement(comment.Name+' of '+comment.Email+' says: ' + comment.Message));
        })
    })
}

function createListElement(text) {
  const liElement = document.createElement('li');
  liElement.innerText = text;
  return liElement;
}

function checkLogin(){
    fetch('/login-status').then(response=>response.text()).then((loginStatus)=>{
        if (loginStatus.valueOf===new String("logged in").valueOf){
            document.getElementById("commentForm").style.display="block";
        }
        else{
            document.getElementById("loginCheck").innerHTML=loginStatus;
        }
    })
}

