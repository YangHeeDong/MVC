package com.ll.boundedContext.member;

import com.ll.framwork.annotataion.Autowired;
import com.ll.framwork.annotataion.Repository;
import com.ll.framwork.annotataion.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public void create(String loginId, String password, String email)  {
        password = encrypt(password);
        memberRepository.create(loginId,password,email);
    }


    public String encrypt(String text) {
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(text.getBytes());

            return bytesToHex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

    public Member login(String loginId, String password) {
        Member member = memberRepository.getMemberByLoginId(loginId);

        if(member == null){
            return null;
        }

        if(!member.getPassword().equals(encrypt(password))){
            return null;
        }

        return member;
    }

    public Member getMemberByLoginId(String loginId){
        return memberRepository.getMemberByLoginId(loginId);
    }

    public Member getMemberByEmail(String email) {
        return memberRepository.getMemberByEmail(email);
    }

    public Member getMemberById(long id) {
        return memberRepository.getMemberById(id);
    }
}
