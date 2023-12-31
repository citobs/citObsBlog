package dev.citobs.citblog2.controller;

import dev.citobs.citblog2.auth.PrincipalDetail;
import dev.citobs.citblog2.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    //data를 가져올때 model이 필요함.
    @GetMapping({"","/"})
    public String index(Model model, @PageableDefault(size=3, sort = "id",direction = Sort.Direction.DESC) Pageable pageable){ //컨트롤에서 세션을 어떻게 찾아?
        //WEB-INF/views/saveForm.jsp
        model.addAttribute("boards", boardService.글목록(pageable));
        return "index"; //viewResolver작동!
    }

    @GetMapping("/board/{id}")
    public String findById(@PathVariable int id, Model model){
        model.addAttribute("board",boardService.글상세보기(id));
        return "board/detail";
    }

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable int id, Model model){
        model.addAttribute("board", boardService.글상세보기(id));
        return "board/updateForm";
    }

    //user권한이 필요
    @GetMapping("/board/saveForm")
    public String saveForm(){
        return "board/saveForm";
    }
}
