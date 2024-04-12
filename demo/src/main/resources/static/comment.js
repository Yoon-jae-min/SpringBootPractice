let commentCreateBtn = document.querySelector("#comment-create-btn");

commentCreateBtn.addEventListener("click", function(){
    let comment = {
        nickname: document.querySelector("#new-comment-nickname").value,
        body: document.querySelector("#new-comment-body").value,
        articleId: document.querySelector("#new-comment-article-id").value
    };

    let url = "/api/articles/" + comment.articleId + "/comments";
    fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(comment)
    }).then(response => {
        let msg = (response.ok) ? "댓글이 등록됐습니다." : "댓글 등록 실패..!";

        alert(msg);

        window.location.reload();
    });
});



let commentEditModal = document.querySelector("#comment-edit-modal");

commentEditModal.addEventListener("show.bs.modal", function(event){
    let triggerBtn = event.relatedTarget;

    let id = triggerBtn.getAttribute("data-bs-id");
    let nickname = triggerBtn.getAttribute("data-bs-nickname");
    let body = triggerBtn.getAttribute("data-bs-body");
    let articleId = triggerBtn.getAttribute("date-bs-article-id");

    document.querySelector("#edit-comment-nickname").value = nickname;
    document.querySelector("#edit-comment-body").value = body;
    document.querySelector("#edit-comment-id").value = id;
    document.querySelector("#edit-comment-article-id").value=  articleId;
});


let commentUpdateBtn = document.querySelector("#comment-update-btn");

commentUpdateBtn.addEventListener("click",function(){
    let comment = {
        id: document.querySelector("#edit-comment-id").value,
        nickname: document.querySelector("#edit-comment-nickname").value,
        body: document.querySelector("#edit-comment-body").value,
        article_id: document.querySelector("#edit-comment-article-id").value
    }

    let url = "/api/comments/" + comment.id;

     fetch(url, {
        method: "PATCH",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(comment)
     }).then(response => {
        let msg = (response.ok) ? "댓글이 수정됐습니다." : "댓글 수정 실패..!";

        alert(msg);
        window.location.reload();
     })
});


const commentDeleteBtns = document.querySelectorAll(".comment-delete-btn");

commentDeleteBtns.forEach(btn => {
    btn.addEventListener('click', () => {
        const commentDeleteBtn = event.target;

        const commentId = commentDeleteBtn.getAttribute("data-comment-id");

        console.log(`삭제 버튼 클릭: ${commentId}번 댓글`);

        const url = `/api/comments/${commentId}`;

        fetch(url, {
            method: "DELETE"
        }).then(response => {
            if(!response.ok){
                alert("댓글 삭제 실패...!");
                return;
            }

            alert(`${commentId}번 댓글을 삭제했습니다.`);
            window.location.reload();
        });
    });
});