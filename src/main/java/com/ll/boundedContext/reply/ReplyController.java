package com.ll.boundedContext.reply;

import com.ll.base.rq.Rq;
import com.ll.boundedContext.member.Member;
import com.ll.boundedContext.member.MemberController;
import com.ll.framwork.annotataion.Autowired;
import com.ll.framwork.annotataion.Controller;
import com.ll.framwork.annotataion.GetMapping;
import com.ll.framwork.annotataion.PostMapping;

import java.util.List;

@Controller
public class ReplyController {
    @Autowired
    ReplyService replyService;

    @Autowired
    MemberController memberController;

    public List<Reply> getRepliesByArticleId(long articleId){
        return replyService.getRepliesByArticleId(articleId);
    }

    @PostMapping("/reply/create/{id}")
    public void createReply(Rq rq){

        Member loginedMember = memberController.getMemberByLoginedMemberId(rq);

        if(loginedMember == null){
            rq.replace("/member/login","로그인 후 이용해주세요");
            return;
        }

        long articleId = rq.getLongParam("id",0);

        if(articleId == 0){
            rq.historyBack("게시글 번호가 없습니다.");
            return;
        }

        String body = rq.getParam("body",null);

        if(body == null || body.trim().length() == 0){
            rq.historyBack("댓글 내용을 입력해 주세요.");
            return;
        }

        long id = replyService.write(articleId,loginedMember.getId(),body);

        // 등록후 상세 내용
        rq.replace("/article/detail/%d".formatted(articleId), "댓글이 등록 되었습니다.");
    }

    @GetMapping("/reply/delete/{id}")
    public void deleteReply(Rq rq){

        Member loginedMember = memberController.getMemberByLoginedMemberId(rq);

        if(loginedMember == null){
            rq.replace("/member/login","로그인 후 이용해주세요");
            return;
        }

        long replyId = rq.getLongParam("id",0);

        if(replyId == 0){
            rq.historyBack("댓글 번호가 없습니다.");
            return;
        }

        Reply reply = replyService.getReplyById(replyId);

        if(reply.getMemberId() != loginedMember.getId()){
            rq.historyBack("권한이 없습니다.");
            return;
        }

        replyService.delete(reply.getId());

        // 등록후 상세 내용
        rq.replace("/article/detail/%d".formatted(reply.getArticleId()), "댓글이 삭제 되었습니다.");
    }

    @PostMapping("/reply/modify/{id}")
    public void showModifyForm(Rq rq){

        Member loginedMember = memberController.getMemberByLoginedMemberId(rq);

        if(loginedMember == null){
            rq.replace("/member/login","로그인 후 이용해주세요");
            return;
        }

        long replyId = rq.getLongParam("id",0);

        if(replyId == 0){
            rq.historyBack("댓글 번호가 없습니다.");
            return;
        }

        Reply reply = replyService.getReplyById(replyId);

        if(reply.getMemberId() != loginedMember.getId()){
            rq.historyBack("권한이 없습니다.");
            return;
        }

        String body = rq.getParam("body",null);

        if(body == null || body.trim().length() == 0){
            rq.historyBack("댓글 내용을 입력해 주세요.");
            return;
        }

        replyService.modify(reply.getId(),body);

        rq.replace("/article/detail/%d".formatted(reply.getArticleId()), "수정되었다리.");
    }

    @GetMapping("/reply/modify/{id}")
    public void doModify(Rq rq){

        Member loginedMember = memberController.getMemberByLoginedMemberId(rq);

        if(loginedMember == null){
            rq.replace("/member/login","로그인 후 이용해주세요");
            return;
        }

        long replyId = rq.getLongParam("id",0);

        if(replyId == 0){
            rq.historyBack("댓글 번호가 없습니다.");
            return;
        }

        Reply reply = replyService.getReplyById(replyId);

        if(reply.getMemberId() != loginedMember.getId()){
            rq.historyBack("권한이 없습니다.");
            return;
        }

        rq.setAttr("reply",reply);
        rq.view("/reply/modify");
    }


}
