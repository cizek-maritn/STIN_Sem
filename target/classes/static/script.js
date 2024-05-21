function PostData(lat,lon,days) {
    data = {
        lat: lat,
        lon: lon,
        days: days
    }
    var msg = "Na Vámi určeném místě na souřadnicích "+lat+" "+lon+" ";
    fetch('/submit', {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
    .then(response => response.text()) // Use response.text() to handle raw response
    .then(data => {
        console.log("Success:", data);
        // Display the response on the page
        const imgDiv = document.getElementById("wicon");
        //should be shot dead for this
        if (data==0) {
            imgDiv.src = "imgs/weather0.png";
            msg=msg+"je právě slunečno.";
        }
        else if (data<3) {
            imgDiv.src = "imgs/weather1.png";
            msg=msg+"je právě oblačno.";
        }
        else if (data<50) {
            imgDiv.src = "imgs/weather2.png";
            msg=msg+"je právě zataženo.";
        }
        else if (data<60) {
            imgDiv.src = "imgs/weather3.png";
            msg=msg+"jsou možné přeháňky.";
        }
        else if (data<70) {
            imgDiv.src = "imgs/weather4.png";
            msg=msg+"právě prší.";
        }
        else if (data<80) {
            imgDiv.src = "imgs/weather5.png";
            msg=msg+"právě sněží.";
        }
        else if (data<90) {
            imgDiv.src = "imgs/weather3.png";
            msg=msg+"jsou možné přeháňky.";
        }
        else {
            imgDiv.src = "imgs/weather6.png";
            msg=msg+"jsou právě bouřky.";
        }
        const responseDiv = document.getElementById("written");
        responseDiv.textContent = msg;
    })
    .catch((error) => {
        console.error("Error:", error);
        const responseDiv = document.getElementById("written");
        responseDiv.textContent = "An error occurred.";
    });
}

document.addEventListener("DOMContentLoaded", function() {
    PostData("50.46", "15.3", "1");
});