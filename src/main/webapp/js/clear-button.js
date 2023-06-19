function loginClearAll() {
    for (var i=0; i < document.forms.length; i++) {
        clearForm(document.forms[i]);
    }
}

function clearForm(form) {
    for ( var i=0; i< form.elements.length; ++i) {
        clearElement(form.elements[i]);
    }
}

function clearElement(element) {
    switch(element.type) {
        case "text":
        case "password":
        case "":
            element.value = "";
        default:
    }
}