package com.example.demo.api;

import com.example.demo.dto.CommentForm;
import com.example.demo.entity.Comment;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;


    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentForm>> comments(@PathVariable Long articleId){

        List<CommentForm> forms = commentService.comments(articleId);

        return ResponseEntity.status(HttpStatus.OK).body(forms);
    }

    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentForm>> create(@PathVariable Long articleId, @RequestBody CommentForm form){
        List<CommentForm> forms = commentService.create(articleId, form);

//        CommentForm formRes = commentService.create(articleId, form);

        return ResponseEntity.status(HttpStatus.OK).body(forms);
    }

    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<List<CommentForm>> update(@PathVariable Long id, @RequestBody CommentForm form){
        List<CommentForm> forms = commentService.update(id, form);

        return ResponseEntity.status(HttpStatus.OK).body(forms);
    }

    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<List<CommentForm>> delete(@PathVariable Long id){
        List<CommentForm> forms = commentService.delete(id);


        return ResponseEntity.status(HttpStatus.OK).body(forms);
    }
}
