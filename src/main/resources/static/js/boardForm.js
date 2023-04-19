/* 
* board Form 유효성 검사
*/

// const submitButton = document.getElementById("submitButton");
// const title = document.getElementById("title");
// const writer = document.getElementById("writer");
// const contactForm = document.getElementById("contactForm");

// submitButton.addEventListener("click", function(){
//     if(title.value == "") {
//         alert("제목을 입력해주세요");
//         return;
//     }

//     if(writer.value == "") {
//         alert("작성자를 입력해주세요");
//         return;
//     }

//     contactForm.submit();

// })

$('#submitButton').click(function(){

    console.log("으헤헬헬헬")
    if($('#title').val() == "") {
        alert("제목을 입력해주세요");
        return;
    }

    if($('#writer').val() == "") {
        alert("작성자를 입력해주세요");
        return;
    }

    $('#contactForm').submit();
})
