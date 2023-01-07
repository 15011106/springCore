package hello.core.Discout;

import hello.core.member.Member;

public interface DiscountPolicy {
    /**
     * @Returng 할인 대상 금액
     */
    int discount(Member member, int price);

}
