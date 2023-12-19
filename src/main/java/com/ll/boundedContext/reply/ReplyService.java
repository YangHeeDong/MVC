package com.ll.boundedContext.reply;

import com.ll.base.rq.Rq;
import com.ll.boundedContext.member.Member;
import com.ll.framwork.annotataion.Autowired;
import com.ll.framwork.annotataion.GetMapping;
import com.ll.framwork.annotataion.PostMapping;
import com.ll.framwork.annotataion.Service;

import java.util.List;

@Service
public class ReplyService {

    @Autowired
    ReplyRepository replyRepository;

    public List<Reply> getRepliesByArticleId(long articleId){
        return replyRepository.getRepliesByArticleId(articleId);
    }


    public long write(long articleId,long memberId, String body) {
        return replyRepository.write(articleId,memberId, body);
    }

    public Reply getReplyById(long replyId) {
        return replyRepository.getReplyById(replyId);
    }

    public void delete(long replyId) {
        replyRepository.delete(replyId);
    }

    public void modify(long replyId, String body) {
        replyRepository.modify(replyId, body);
    }
}
