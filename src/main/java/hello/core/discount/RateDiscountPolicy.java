package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
@MainDiscountPolicy
//@Primary 는 기본값 처럼 동작하고 @Qualifier 는 매우 상세하게 동작 스프링은 자동보다는 수동이 우선 순위가 높다
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100; //10% 할인하는 로직
        } else {
            return 0;
        }
    }
}
