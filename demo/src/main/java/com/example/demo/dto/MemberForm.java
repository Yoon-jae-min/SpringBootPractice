package com.example.demo.dto;

import com.example.demo.entity.Member;

public class MemberForm {
    private String email;

    private String password;

    public MemberForm(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "MemberForm{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Member toEntity() { return new Member(email, password); }
}
