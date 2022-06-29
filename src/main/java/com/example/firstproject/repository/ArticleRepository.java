package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

//JPA에서 제공하는 repository 인터페이스를 활용하여 쉽게 만들기
//interface로 받는 이유 : (JPA)CrudRepository가 interface로 선언 되어 있으며, CrudRepository를 상속받아 함수로만 쓰여지기 때문에 interface 사용
public interface ArticleRepository extends CrudRepository<Article, Long> { //<관리대상entity, entity의 대표값의 타입>

    @Override
    ArrayList<Article> findAll();
}
