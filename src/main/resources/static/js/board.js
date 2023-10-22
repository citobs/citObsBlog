let index = {
    init: function (){
        $("#btn-save").on("click", () =>{
            this.save();
        });
    },

    save:function (){
        // alert('user의 save 함수 호출됨')
        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        };
        // console.log(data)
        //ajax 호출시 default가 비동기 호출
        // ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청
        $.ajax({
            type:"POST",
            url:"/api/board",
            data:JSON.stringify(data), //http body data가 어떤 타입인지
            contentType:"application/json; charset=utf-8",
            dataType:"json"//응답으로 온게 json이면 javascripts오브젝트로 변경

        }).done(function (resp){
            alert("글쓰기 완료!");
            console.log(resp)
            location.href="/";
        }).fail(function (error){
            alert(JSON.stringify(error))

        });
    }
}

index.init();