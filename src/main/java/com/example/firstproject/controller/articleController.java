package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j // 로깅을 위한 골뱅이(어노테이션)
public class articleController {

    @Autowired //스프링 부트가 미리 생성해 놓은 객체를 가져다가 자동 연결!
    private ArticleRepository articleRepository;
    @Autowired
    private CommentService commentService;

    @GetMapping("/articles/new") //get -> 클라이언트가 요청
    public String newArticleForm() {
        return "articles/new"; //view template 값을 준다
    }

    //데이터 DB에 삽입
    @PostMapping("/articles/create") // PostMapping - > 주소값을 일단 받고, 데이터들을 DTO 값으로 변환 후 매개변수로 전달
    public String createArticle(ArticleForm form) { // 매개변수를 받고 함수 실행
        //System.out.println(form.toString()); -> printf를 log.info 기능으로 대체
        log.info(form.toString());

        // 1. DTO를 변환! Entity!
        Article article = form.toEntity();
        //System.out.println(article.toString());
        log.info(article.toString());
        // 2. Repository에게 Entity를 DB안에 저장하게 함!
        Article saved = articleRepository.save(article);
        //System.out.println(saved.toString());
        log.info(saved.toString());

        return "redirect:/articles/" + saved.getId(); // view template 값
    }

    @GetMapping("/articles/{id}") //id : 변환될 숫자
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id);

        // 1. id로 데이터를 가져옴
        Article articleEntity = articleRepository.findById(id).orElse(null); // orElse - > 해당 값이 없으면 null을 반환
        List<CommentDto> commentDtos = commentService.comments(id);

        // 2. 가져온 데이터를 모델에 등록!
        model.addAttribute("article", articleEntity);
        model.addAttribute("commentDtos", commentDtos);

        // 3. 보여줄 페이지를 설정!
        return "/articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {
        // 1: 모든 Article을 가져온다!
        List<Article> articleEntityList = articleRepository.findAll();
        //강제 형변환 또는 함수 리턴 타입을 맞춰준다 List<Article> - > Iterable

        // 2: 가져온 Article 묶음을 뷰로 전달!
        model.addAttribute("articleList", articleEntityList);

        // 3: 뷰 페이지를 설정!
        return "articles/index"; //articles/index.mustache
    }

    @GetMapping("/articles/{id}/edit") //{id} id - > pathVariable : id 같아야함
    public String edit(@PathVariable Long id, Model model) { //수정 페이지
        // 수정할 데이터를 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 모델에 데이터를 등록
        model.addAttribute("article", articleEntity);

        // 뷰 페이지 설정
        return "articles/edit";
    }

    //데이터 수정
    @PostMapping("/articles/update")
    public String update(ArticleForm form) { //id가 동일하기 때문에 같은 위치에 삽입한다. 즉, 업데이트
        log.info(form.toString());

        // 1: DTO를 엔티티로 변환한다!
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());

        // 2: 엔티티를 DB로 저장한다!
        // 2-1 : DB에서 기존 데이터를 가져온다!
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);

        // 2-2 : 기존 데이터에 값을 갱신한다!
        if(target != null) {
            articleRepository.save(articleEntity); // 엔티티가 DB로 갱신
        }

        // 3: 수정 결과 페이지로 리다이렉트 한다!
        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable long id, Model model, RedirectAttributes rttr) {

        // 1: 삭제 대상을 가져온다!
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 2: 대상을 삭제한다!
        if(articleEntity != null) {
            articleRepository.delete(articleEntity);
            rttr.addFlashAttribute("msg", "삭제가 완료 되었습니다!");
        }

        // 3: 결과 페이지로 리다이렉트 한다!
        return "redirect:/articles";
    }
}
