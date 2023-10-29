package dev.citobs.citblog2.controller.api;

import dev.citobs.citblog2.auth.PrincipalDetail;
import dev.citobs.citblog2.dto.ResponseDto;
import dev.citobs.citblog2.model.Board;
import dev.citobs.citblog2.model.User;
import dev.citobs.citblog2.service.BoardService;
import dev.citobs.citblog2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class BoardApiController {

//    @Autowired
//    private HttpSession session;
    //유저 정보 넘겨주기
    @Autowired
    private BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
        boardService.글쓰기(board, principal.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK, 1);

    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto <Integer> deleteById(@PathVariable int id) {
        boardService.글삭제하기(id);
        return new ResponseDto<Integer>(HttpStatus.OK, 1);
    }

    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board) {
        boardService.글수정하기(id, board);
        return new ResponseDto<Integer>(HttpStatus.OK, 1);

    }


}
