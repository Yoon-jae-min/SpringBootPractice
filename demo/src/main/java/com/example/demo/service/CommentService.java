package com.example.demo.service;

import com.example.demo.dto.ArticleForm;
import com.example.demo.dto.CommentForm;
import com.example.demo.entity.Article;
import com.example.demo.entity.Comment;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentForm> comments(Long articleId){

        List<Comment> comments = commentRepository.findByArticleId(articleId);

//        List<CommentForm> forms = new ArrayList<CommentForm>();
//
//        for (Comment c : comments) {
//            CommentForm form = CommentForm.createCommentForm(c);
//            forms.add(form);
//        }
        return comments.stream().map(comment -> CommentForm.createCommentForm(comment)).collect(Collectors.toList());
    }

    @Transactional
    public List<CommentForm> create(Long articleId, CommentForm form){

        Article article = articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! " + " 대상 게시글이 없습니다."));

        Comment comment = Comment.createComment(form, article);

        commentRepository.save(comment);

        return comments(articleId);
    }

    @Transactional
    public List<CommentForm> update(Long id, CommentForm form){
        Comment target = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패! 대상 댓글이 없습니다."));

        target.patch(form);

        commentRepository.save(target);

        return comments(form.getArticleId());
    }

    @Transactional
    public List<CommentForm> delete(Long id){
        Comment target = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! 대상 댓글이 없습니다."));

        commentRepository.delete(target);

        CommentForm targetForm = CommentForm.createCommentForm(target);

        return comments(targetForm.getArticleId());
    }
}
