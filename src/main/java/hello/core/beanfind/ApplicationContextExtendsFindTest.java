package hello.core.beanfind;

import hello.core.Discout.DiscountPolicy;
import hello.core.Discout.FixDiscountPolicy;
import hello.core.Discout.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(testConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회시 자식이 둘 이상 있으면 중복 오류가 발생한다.")
    void findBeanByParentTypeDuplicate(){
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class, ()-> ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입으로 조회시 자식이 둘 이상 있으면 이름으로 조회한다.")
    void findBeanByParentTypeBeanName(){
        DiscountPolicy discountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        Assertions.assertTrue(discountPolicy instanceof RateDiscountPolicy);
    }
    @Test
    @DisplayName("특정 하위 타입으로 조회한다.")
    void findBeanBySubType(){
        RateDiscountPolicy rateDiscountPolicy = ac.getBean(RateDiscountPolicy.class);
        Assertions.assertTrue(rateDiscountPolicy instanceof RateDiscountPolicy);
    }

    @Test
    @DisplayName("부모 타입으로 조회한다.")
    void findAllBeanByParentType(){
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        Assertions.assertEquals(beansOfType.size(),2);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + "value = " + beansOfType.get(key));
        }
    }
    @Test
    @DisplayName("부모 타입으로 모두 조회한다 - Object")
    void findAllBeanByObjectType(){
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + "value = " + beansOfType.get(key));
        }
    }
    @Configuration
    static class testConfig{
        @Bean
        public DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }
    }



}
