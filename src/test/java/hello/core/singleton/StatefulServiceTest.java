package hello.core.singleton;

import hello.core.AppConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {


    @Test
    void StatefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // tA : A 10000
        int userAPrice = statefulService1.order("userA", 10000);

        // tB : B 20000
        int userBPrice = statefulService2.order("userB", 20000);

//        int price = statefulService1.getPrice();
        System.out.println("price = " + userAPrice);

//        Assertions.assertEquals(statefulService1.getPrice(),20000);
    }

    @Configuration
    static class TestConfig{

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }

    }
}