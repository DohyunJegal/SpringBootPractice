package com.example.website.controller.api;

import com.example.website.config.auth.PrincipalDetail;
import com.example.website.dto.board.BoardSaveRequestDto;
import com.example.website.dto.board.BoardUpdateRequestDto;
import com.example.website.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BoardApiController {
    private final BoardService boardService;

    @PostMapping("/api/v1/board")
    public Long save(@RequestBody BoardSaveRequestDto boardSaveRequestDto, @AuthenticationPrincipal PrincipalDetail principalDetail){
        return boardService.save(boardSaveRequestDto, principalDetail.getUser());
    }

    @DeleteMapping("/api/v1/board/{id}")
    public Long deleteById(@PathVariable Long id){
        boardService.deleteById(id);
        return id;
    }

    @PutMapping("/api/v1/board/{id}")
    public Long update(@PathVariable Long id, @RequestBody BoardUpdateRequestDto boardUpdateRequestDto) {
        return boardService.update(id, boardUpdateRequestDto);
    }
}