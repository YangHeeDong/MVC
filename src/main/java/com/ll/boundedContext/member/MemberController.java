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

    public Member getMemberByLoginedMemberId(Rq rq){
        long loginedMemeberId = Long.parseLong(rq.getSessionAttributeByKey("loginedMemberId"));
        if(loginedMemeberId == -1){
            return null;
        }
        return memberService.getMemberById(loginedMemeberId);
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
            rq.setSessionAttribute("loginedMemberId", member.getId());
            rq.setSessionAttribute("loginedMemberLoginId", member.getLoginId());
        }

        rq.replace("/article/list",member.getLoginId()+"님 환영합니다");
    }

    @GetMapping("/member/logout")
    public void logout(Rq rq){
        rq.sessionReset();
        rq.replace("/article/list","로그아웃 되었습니다.");
    }

    @GetMapping("/member/myPage")
    public void showMyPage(Rq rq){

        Member loginedMember = getMemberByLoginedMemberId(rq);

        if(loginedMember == null){
            rq.replace("/member/login","로그인 후 이용해주세요");
            return;
        }

        rq.setAttr("loginedMemberEmail",loginedMember.getEmail());

        rq.view("/member/myPage");
    }

    @GetMapping("/member/delete")
    public void delete(Rq rq){

        Member loginedMember = getMemberByLoginedMemberId(rq);

        if(loginedMember == null){
            rq.replace("/member/login","로그인 후 이용해주세요");
            return;
        }

        memberService.delete(loginedMember.getId());
        
        rq.replace("/article/list","탈퇴 되었습니다.");
    }

    @PostMapping("/member/changePassword")
    public void changePassword(Rq rq){

        Member loginedMember = getMemberByLoginedMemberId(rq);

        if(loginedMember == null){
            rq.replace("/member/login","로그인 후 이용해주세요");
            return;
        }

        String oldPassword = rq.getParam("oldPassword",null);
        String newPassword = rq.getParam("newPassword",null);
        String newPasswordConfirm = rq.getParam("newPasswordConfirm",null);

        // 세 데이터중 하나라도 null이 아니라면
        if(oldPassword != null || newPassword != null || newPasswordConfirm != null){
            if(oldPassword == null || oldPassword.trim().isEmpty()){
                rq.historyBack("이전 비밀번호를 입력해줘잉");
                return;
            }

            if(newPassword == null || newPassword.trim().isEmpty()){
                rq.historyBack("새 비밀번호를 입력해줘잉");
                return;
            }

            if(newPasswordConfirm == null || newPasswordConfirm.trim().isEmpty()){
                rq.historyBack("새 비밀번호 확인을 입력해줘잉");
                return;
            }

            if(!newPassword.equals(newPasswordConfirm)){
                rq.historyBack("새 비밀번호와 비밀번호확인이 달라잉");
                return;
            }

            if(memberService.login(loginedMember.getLoginId(),oldPassword) == null){
                rq.historyBack("현재 비밀번호가 일치하지 않습니다.");
                return;
            }
        }



        memberService.chagePassword(loginedMember.getId(),newPassword);
        rq.sessionReset();
        rq.replace("/member/login","비밀번호가 수정 되었습니다.");
    }

}
