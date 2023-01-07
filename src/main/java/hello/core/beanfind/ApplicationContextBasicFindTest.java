package hello.core.beanfind;
import hello.core.AppConfig;
import hello.core.member.Member;
import hello.core.member.MemberService;

import hello.core.member.MemberServiceImpl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);


    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertTrue(memberService instanceof MemberServiceImpl);
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType() {

        MemberService memberService = ac.getBean(MemberService.class);
        assertTrue(memberService instanceof MemberServiceImpl);

    }
    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2() {

        MemberServiceImpl memberService = ac.getBean("memberService",MemberServiceImpl.class);
        assertTrue(memberService instanceof MemberServiceImpl);

    }

    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanByNameX() {

//        MemberService xxxxx = ac.getBean("xxxxx", MemberService.class);
        assertThrows(NoSuchBeanDefinitionException.class, ()-> ac.getBean("xxxxx", MemberService.class));
    }

}