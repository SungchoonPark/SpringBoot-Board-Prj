'use strict'
let index = {
    init: function () {
        $("#btn-save").on("click", ()=>{
            this.postSave();
        });
        $("#btn-delete").on("click", ()=>{
            this.postDelete();
        });
        $("#btn-update").on("click", ()=>{
            this.postUpdate();
        })

        $("#btn-reply-save").on("click", ()=>{
            this.replySave();
        });
        $("#btn-reply-delete").on("click", ()=>{
            this.replyDelete();
        });
    },

    postSave: function () {

        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
        };

        let form = $('#form')[0];
        let formData = new FormData(form);

        formData.append('file', $('#file'));
        formData.append('key', new Blob([JSON.stringify(data)], {type: "application/json"}));

        $.ajax({
            type: "POST",
            url: "/api/board",
            data: formData,
            contentType: false,
            processData: false,
        }).done(function (resp){
            alert("게시글이 등록되었습니다.");
            location.href="/";
        }).fail(function (error){
            alert(JSON.stringify(error))
        })
    },

    postUpdate: function () {
        let id = $("#id").val();
        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
        }

        $.ajax({
            type: "PUT",
            url: `/api/board/${id}`,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (resp) {
            alert("글이 수정되었습니다.");
            console.log(resp);
            location.href=`/board/${id}`;
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },

    postDelete: function () {
        let id = $("#id").val();

        $.ajax({
            type: "DELETE",
            url: `/api/board/${id}`,
            dataType: "json"
        }).done(function (resp){
            alert("글삭제가 완료되었습니다.");
            location.href="/";
        }).fail(function (error){
            alert(JSON.stringify(error));
        })
    },

    replySave: function () {
        let data = {
            boardId: $("#boardId").val(),
            content: $("#reply-content").val(),
        };

        $.ajax({
            type: "POST",
            url: `/api/board/${data.boardId}/reply`,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (resp) {
            alert("댓글이 등록되었습니다.");
            console.log(resp);
            location.href=`/board/${data.boardId}`;
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },

    replyDelete: function (boardId, replyId) {

        $.ajax({
            type: "DELETE",
            url: `/api/board/${replyId}/reply`,
            dataType: "json"
        }).done(function (resp){
            alert("댓글이 삭제되었습니다.");
            location.href=`/board/${boardId}`;
        }).fail(function (error){
            alert(JSON.stringify(error));
        })
    },

}

index.init();