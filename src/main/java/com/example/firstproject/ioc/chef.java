package com.example.firstproject.ioc;

import org.springframework.stereotype.Component;

@Component
public class chef {

    // 셰프는 식재료 공장을 알고있음
    private IngredientFactory ingredientFactory;
    // 셰프가 식재료 공장과 협업하기 위한 DI (외부에서 객체정보를 이공장에 대한 정보를 받아오기)
    public chef(IngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    public String cook(String menu) {
        // Ingredient는 Beef와 pork의 부모 클래스
        // 재료 준비
        //pork pork = new pork("한돈 등심");
        //Beef beef = new Beef("한우 꽃등심");
        Ingredient ingredient = ingredientFactory.get(menu);

        // 요리 반환
        return ingredient.getName() + "으로 만든 " + menu;
    }
}
