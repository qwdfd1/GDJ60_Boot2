// Join Form에서 검증

// ID 중복체크
$('#userName').blur(idDuplicateCheck);

// 비밀번호 입력 체크
$('#password').blur(function(){
    let text = '비밀번호는 필수 입력 항목입니다'
    if($(this).val() == '') {
        inputCheck($(this), text)
    }
    else {
        text = '';
        inputCheck($(this), text)
    }
    passwordCheck();
})

// 비밀번호 일치하는지 체크
$('#passwordCheck').blur(function(){
    passwordCheck();
})

// ID 중복체크 함수
function idDuplicateCheck(){
    
    $.ajax({
        type: "GET",
        url : "./idDuplicateCheck",
        data : {
            userName : $('#userName').val()
        },
        success : function(result){
            if(result) {
                console.log("중복된 아이디입니다.");
            }
            else {
                console.log("사용가능한 아이디입니다");
            }
            
        },
        error: function(){
            console.log('error');
        }
    })
}


// Input 태그 입력 체크
function inputCheck(input, text) {
    $(input).parent().find('.check').text(text);
}

// 비밀번호 일치 확인 함수
function passwordCheck() {
    let text = '비밀번호가 일치하지 않습니다';
    if($('#password').val() != $('#passwordCheck').val()) {
        inputCheck($('#passwordCheck'), text)
    }else {
        text = "비밀번호가 일치합니다"
        inputCheck($('#passwordCheck'), text)
    }
}



