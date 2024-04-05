package com.example.demo.controller;

import com.example.demo.dto.MemberForm;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @Autowired
    private MemberRepository membersave;

    @GetMapping("/joins/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/join")
    public String createMember(MemberForm form){

        System.out.println(form.toString());

        Member member = form.toEntity();

        System.out.println(member.toString());

        Member saved = membersave.save(member);

        System.out.println(saved.toString());

        return "";
    }
}
