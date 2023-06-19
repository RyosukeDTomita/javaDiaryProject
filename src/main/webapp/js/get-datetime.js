// Use at userpage.jsp
function getNowDate() {
    var today = new Date();
            today.setDate(today.getDate());
            var yyyy = today.getFullYear();
            var mm = ("0" + (today.getMonth() + 1)).slice(-2);
            var dd = ("0" + today.getDate()).slice(-2);
            document.getElementById("dateTime").value= yyyy+ '-' + mm + '-' + dd;
        }
