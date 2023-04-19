let param = '';
let max = 0;
let count = 0;

function setParam(p) {
    param = p;
}

$('#addFiles').click(function(){
    let str = '<div class="input-group mb-3">'
    str += '<input class="form-control" type="file" name="'+param+'" id="formFile">';
    str += '<button class="btn btn-outline-danger" type="button" id="button-addon2">X</button>'
    str += '</div>';

    $('#fileList').append(str);
    

})