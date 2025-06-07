const globalURL = "http://localhost:8080/v1/"

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


//if html addForm
//registerForm()
async function addAccount(){
    try {
        let response = await fetch(globalURL+"accounts/addAccount?username=yara1&balance=1000", {
         method: "POST",
         headers: {
                   "Content-Type": "application/json"
                   }
                  });

                  let result = await response.text();
                   alert(result);
         } catch (error) {
              alert("Error registering user: " + error);
              }
}

addAccount()
makeAction()

function makeAction(){
    let depositBtn = document.getElementById("deposit-btn");
    let withdrawBtn = document.getElementById("withdraw-btn");
    depositBtn.addEventListener('click', ()=> action("makeDeposit"))
    withdrawBtn.addEventListener('click', ()=>action("withdraw"))
}

async function action(endpoint){
let amount = document.getElementById("amount-text");
try {

        if (!amount.value || isNaN(amount.value) || amount.value <= 0) {
            console.log(amount.value)
        }

            let response = await fetch(globalURL+`accounts/${endpoint}?id=1&amount=${amount.value}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                }
            });

            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }

            let result = await response.json(); //return
            let display = document.getElementById("result-span")
            display.innerText=result.balance

        } catch (error) {
            alert(`Error depositing ${amount.value}: ${error.message}`);
        }
}

function confirmPassword(password, confirmPassword){
    if(password !== confirmPassword)
        {
         return false;
        }
    return true;
}
function registerForm(){

    document.getElementById("registerForm").addEventListener("submit", async function(event) {
                    event.preventDefault();
                    let username = document.getElementById("username").value;
                    let name = document.getElementById("name").value;
                    let password = document.getElementById("password").value;
                    let confirmPassword = document.getElementById("confirmPassword").value;
                    let errorMessage = document.getElementById("errorMessage");

                    if (confirmPassword(password,confirmPassword)) {
                        errorMessage.classList.remove("hidden");
                        return;
                    } else {
                        errorMessage.classList.add("hidden");
                    }

                    let user = {
                        username: username,
                        name: name,
                        password: password
                    };

                    try {
                        let response = await fetch(globalURL+"users/register", {
                            method: "POST",
                            headers: {
                                "Content-Type": "application/json"
                            },
                            body: JSON.stringify(user)
                        });

                        let result = await response.text();
                        alert(result);
                    } catch (error) {
                        alert("Error registering user: " + error);
                    }
                });

}

