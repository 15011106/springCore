package hello.core.order;

import hello.core.Discout.FixDiscountPolicy;
import hello.core.Discout.RateDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void createOrder(){
        MemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L,"name", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(new MemoryMemberRepository(), new RateDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 20000);
        Assertions.assertEquals(order.getDiscountPrice(), 2000);
    }

}