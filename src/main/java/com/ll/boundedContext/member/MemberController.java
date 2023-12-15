package com.ll.boundedContext.member;

import com.ll.base.rq.Rq;
import com.ll.framwork.annotataion.Autowired;
import com.ll.framwork.annotataion.Controller;
import com.ll.framwork.annotataion.GetMapping;
import com.ll.framwork.annotataion.PostMapping;

import java.time.LocalDateTime;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/member/create")
    public void createMemberFrom(Rq rq){
        rq.view("/member/create");
    }

    @PostMapping("/member/create")
    public void createMember(Rq rq){

        String loginId = rq.getParam("loginId",null);
        if(loginId == null || loginId.trim().isEmpty()){
            rq.historyBack("아이디를 입력해줘잉");
            return;
        }

        String password = rq.getParam("password",null);
        if(password == null || password.trim().isEmpty()){
            rq.historyBack("비밀번호를 입력해줘잉");
            return;
        }

        String passwordConfirm = rq.getParam("passwordConfirm",null);
        if(passwordConfirm == null || passwordConfirm.trim().isEmpty()){
            rq.historyBack("비밀번호 확인을 입력해줘잉");
            return;
        }

        String email = rq.getParam("email",null);
        if(email == null || email.trim().isEmpty()){
            rq.historyBack("이메일을 입력해줘잉");
            return;
        }

        if(!password.equals(passwordConfirm)){
            rq.historyBack("비밀번호와 비밀번호확인이 달라잉");
            return;
        }

        // 이 구조가 좋을까 따로 함수를 만들어야 하나
        Member findMemberByLoginId = memberService.getMemberByLoginId(loginId);
        if(findMemberByLoginId != null){
            rq.historyBack("해당 아이디로 이미 가입 되어있어잉");
            return;
        }

        Member findMemberByEmail = memberService.getMemberByEmail(loginId);
        if(findMemberByEmail != null){
            rq.historyBack("해당 이메일로 이미 가입 되어있어잉");
            return;
        }

        memberService.create(loginId, password, email);

        rq.replace("/member/login","가입 되었습니다!");

    }

    @GetMapping("/member/login")
    public void loginForm(Rq rq){
        rq.view("/member/login");
    }

    @PostMapping("/member/login")
    public void doLogin(Rq rq){

        String loginId = rq.getParam("loginId",null);
        if(loginId == null || loginId.trim().isEmpty()){
            rq.historyBack("아이디를 입력해줘잉");
            return;
        }

        String password = rq.getParam("password",null);
        if(password == null || password.trim().isEmpty()){
            rq.historyBack("비밀번호를 입력해줘잉");
            return;
        }

        Member member = memberService.login(loginId,password);

        if(member == null){
            rq.historyBack("아이디 또는 비밀번호를 확인하세요");
            return;
        }else{
            rq.setSessionAttribute("memberId", member.getId());
            rq.setSessionAttribute("memberLoginId",member.getLoginId());
        }

        rq.replace("/article/list",member.getLoginId()+"님 환영합니다");
    }

    @GetMapping("/member/logout")
    public void logout(Rq rq){
        rq.sessionReset();
        rq.replace("/article/list","로그아웃 되었습니다.");
    }



}
