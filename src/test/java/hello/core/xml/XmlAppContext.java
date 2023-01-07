package hello.core.xml;

import hello.core.member.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

public class XmlAppContext {

    ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
    @Test
    @DisplayName("xml 테스트")
    void xmlAppContext(){

        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertTrue(memberService instanceof MemberService);
    }
}
