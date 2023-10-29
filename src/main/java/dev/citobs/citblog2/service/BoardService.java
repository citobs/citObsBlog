package dev.citobs.citblog2.service;

import dev.citobs.citblog2.model.Board;
import dev.citobs.citblog2.model.RoleType;
import dev.citobs.citblog2.model.User;
import dev.citobs.citblog2.repository.BoardRepository;
import dev.citobs.citblog2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service //스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌,ioc해줌
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    //서비스함수 만들기
    @Transactional
    public void 글쓰기(Board board, User user) { //title, content
        //조회수 강제로 넣기
        board.setCount(0);
        board.setUser(user);
       boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public Page<Board> 글목록(Pageable pageable){
       return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Board 글상세보기(int id){
        return boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("글상세보기 실패 : id를 찾을수 없음");
                });
    }

    @Transactional
    public void 글삭제하기(int id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public void 글수정하기(int id, Board requestBoard) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("글 찾기 실패 : id를 찾을수 없음");
                }); // 영속화 완료
        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
    } // 해당 함수 종료시 트랜잭션이 Service가 종료 될 때 트랜잭션이 종료,
    // 이때 더티 체킹으로 - 자동ㅇ 업데이트가 됨 db flush
}
