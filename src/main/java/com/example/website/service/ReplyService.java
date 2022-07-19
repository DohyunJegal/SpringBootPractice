package com.example.website.service;

import com.example.website.domain.board.Board;
import com.example.website.domain.board.BoardRepository;
import com.example.website.domain.reply.Reply;
import com.example.website.domain.reply.ReplyRepository;
import com.example.website.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public void replySave(Long boardId, Reply reply, User user){
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("[ " + boardId + " ] 해당 boardId가 존재하지 않습니다."));
        reply.save(board, user);
        replyRepository.save(reply);
    }

    @Transactional
    public void replyDelete(Long replyId){
        replyRepository.deleteById(replyId);
    }
}
