package com.example.website.service;

import com.example.website.domain.board.Board;
import com.example.website.domain.board.BoardRepository;
import com.example.website.domain.user.User;
import com.example.website.dto.board.BoardSaveRequestDto;
import com.example.website.dto.board.BoardUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public Long save(BoardSaveRequestDto boardSaveRequestDto, User user){
        boardSaveRequestDto.setUser(user);
        return boardRepository.save(boardSaveRequestDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable) {
        return boardRepository.findByTitleContainingOrContentContaining(title, content, pageable);
    }

    @Transactional(readOnly = true)
    public Board detail(Long id){
        return boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("[ ID : " + id + " ] 해당 ID가 존재하지 않습니다."));
    }

    @Transactional
    public void deleteById(Long id){
        boardRepository.deleteById(id);
    }

    @Transactional
    public Long update(Long id, BoardUpdateRequestDto boardUpdateRequestDto) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 id가 없습니다. id=" + id));
        board.update(boardUpdateRequestDto.getTitle(), boardUpdateRequestDto.getContent());
        return id;
    }

    @Transactional
    public int updateCount(Long id) {
        return boardRepository.updateCount(id);
    }
}
