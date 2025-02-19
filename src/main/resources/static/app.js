/*fetch("http://localhost:8080/v1/users/all")
  .then(response => {
    if (!response.ok) {
      throw new Error("Network response was not ok");
    }
    return response.json(); // Convert response to JSON
  })
  .then(displayUsers)// Handle the data)
  .catch(error => {
    console.error("Error fetching users:", error);
  });

  function displayUsers(data) {
    const list = document.createElement("ul")
    data.forEach(u => {
        const li = document.createElement('li')
        li.innerText=`${u.name} and username ${u.username}`
        list.appendChild(li)
    });
    document.body.appendChild(list);
  }*/

const title = document.querySelector("#input-title");
title.addEventListener("input",function(e){
console.log(title.value)
})
