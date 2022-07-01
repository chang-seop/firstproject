package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController // RestAPI 용 컨트롤러! 데이터(JSON)를 반환
public class ArticleApiController {

    @Autowired
    ArticleRepository articleRepository;

    //GET
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleRepository.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Article arrays(@PathVariable long id) {
        return articleRepository.findById(id).orElse(null);
    }

    //POST
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto) { //@RequestBody: 요청의 바디에서 데이터를 받아오기
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

    //PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable long id, //ResponseEntity<Article> ResponseEntity에 Article 데이터가 담겨서 JSON으로 반환
                                          @RequestBody ArticleForm dto) {
        // 1 : 수정용 엔티티 생성
        Article article = dto.toEntity();
        log.info("id {}, article: {}", id, article.toString());
        // 2 : 대상 엔티티를 조회
        Article target = articleRepository.findById(id).orElse(null);

        // 3 : 잘못된 요청 처리(대상이 없거나, id가 다른 경우)
        if( target == null || id != article.getId()) {
            // 400, 잘못된 요청 응답!
            log.info("잘못된 요청! id {}, article: {}", id, article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // 4 : 업데이트 및 정상 응답(200)
        target.patch(article);  //수정을 할 때 title과 content 중 하나만 수정이 됐을 경우 원래 있던 데이터와 가져온 NULL이 아닌 데이터를 덧씌우는 역할
        Article updated = articleRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }
    //DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable long id) {
        //대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        //잘못된 요청 처리
        if(target == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        //대상 삭제
        articleRepository.delete(target);
        //데이터 반환
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
