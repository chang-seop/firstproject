package com.example.firstproject.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class chefTest {

    // IoC 컨테이너로부터 가져올 수 있게 만드는 스프링 부트 방법
    @Autowired
    IngredientFactory ingredientFactory;

    @Autowired
    chef chef;

    @Test
    void 돈가스_요리하기() {
        // 준비
        //IngredientFactory ingredientFactory =  new IngredientFactory();
        //chef chef = new chef(ingredientFactory); // DI 삽입 (동작에 필요한 객체를 외부에서 받아옴)
        String menu = "돈가스";
        // 수행
        String food = chef.cook(menu);
        // 예상
        String expected = "한돈 등심으로 만든 돈가스";
        // 검증
        assertEquals(expected, food);
        System.out.println(food);
    }

    @Test
    void 스테이크_요리하기() {
        // 준비
        // ingredientFactory =  new IngredientFactory();
        //chef chef = new chef(ingredientFactory);
        String menu = "스테이크";
        // 수행
        String food = chef.cook(menu);
        // 예상
        String expected = "한우 꽃등심으로 만든 스테이크";
        // 검증
        assertEquals(expected, food);
        System.out.println(food);
    }

    @Test
    void 크리스피_치킨_요리하기() {
        // 준비
        //IngredientFactory ingredientFactory =  new IngredientFactory();
        //chef chef = new chef(ingredientFactory); //DI
        String menu = "크리스피 치킨";
        // 수행
        String food = chef.cook(menu);
        // 예상
        String expected = "국내산 10호 닭으로 만든 크리스피 치킨";
        // 검증
        assertEquals(expected, food);
        System.out.println(food);
    }
}