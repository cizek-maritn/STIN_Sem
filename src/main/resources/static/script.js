function PostData(lat,lon,days) {
    data = {
        lat: lat,
        lon: lon,
        days: days
    }
    const weekday = ["Neděle","Pondělí","Úterý","Středa","Čtvrtek","Pátek","Sobota"];
    const d = new Date();
    //var msg = "Na Vámi určeném místě na souřadnicích "+lat+" "+lon+" ";
    fetch('/submit', {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
    .then(response => response.text()) // Use response.text() to handle raw response
    .then(data => {
        data=JSON.parse(data);
        console.log("Success:", data);
        // Display the response on the page
        for (let i=0;i<data.length;i++) {
            if (data.length==1) {
                var msg = "Na Vámi určeném místě na souřadnicích "+lat+" "+lon+" je právě";
            } else {
                let day = weekday[(d.getDay()+i)%7];
                var msg = day;
            }
            var divName = "wicon" + i;
            const imgDiv = document.getElementById(divName);
            //should be shot dead for this
            if (data[i]==0) {
                imgDiv.src = "imgs/weather0.png";
                msg=msg+" slunečno.";
            }
            else if (data[i]<3) {
                imgDiv.src = "imgs/weather1.png";
                msg=msg+" oblačno.";
            }
            else if (data[i]<50) {
                imgDiv.src = "imgs/weather2.png";
                msg=msg+" zataženo.";
            }
            else if (data[i]<60) {
                imgDiv.src = "imgs/weather3.png";
                msg=msg+" přeháňky.";
            }
            else if (data[i]<70) {
                imgDiv.src = "imgs/weather4.png";
                msg=msg+" prší.";
            }
            else if (data[i]<80) {
                imgDiv.src = "imgs/weather5.png";
                msg=msg+" sněží.";
            }
            else if (data[i]<90) {
                imgDiv.src = "imgs/weather3.png";
                msg=msg+" přeháňky.";
            }
            else {
                imgDiv.src = "imgs/weather6.png";
                msg=msg+" bouřky.";
            }
            const responseDiv = document.getElementById("w"+i);
            responseDiv.textContent = msg;
        }
    })
    .catch((error) => {
        console.error("Error:", error);
        const responseDiv = document.getElementById("w0");
        responseDiv.textContent = "An error occurred.";
    });
}

function histData(lat,lon,date) {
    data = {
        lat: lat,
        lon: lon,
        dat: date
    }
    fetch('/submitHist', {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
    .then(response => response.text()) // Use response.text() to handle raw response
    .then(data => {
        data=JSON.parse(data);
        console.log("Success:", data);
        // Display the response on the page
        for (let i=0;i<data.length;i++) {
            var divName = "wicon" + i;
            const imgDiv = document.getElementById(divName);
            //should be shot dead for this
            if (data[i]==0) {
                imgDiv.src = "imgs/weather0.png";
            }
            else if (data[i]<3) {
                imgDiv.src = "imgs/weather1.png";
            }
            else if (data[i]<50) {
                imgDiv.src = "imgs/weather2.png";
            }
            else if (data[i]<60) {
                imgDiv.src = "imgs/weather3.png";
            }
            else if (data[i]<70) {
                imgDiv.src = "imgs/weather4.png";
            }
            else if (data[i]<80) {
                imgDiv.src = "imgs/weather5.png";
            }
            else if (data[i]<90) {
                imgDiv.src = "imgs/weather3.png";
            }
            else {
                imgDiv.src = "imgs/weather6.png";
            }
        }
    })
    .catch((error) => {
        console.error("Error:", error);
        alert("Nastala chyba.")
    });
}