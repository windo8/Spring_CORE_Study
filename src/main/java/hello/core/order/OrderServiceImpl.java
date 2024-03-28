package hello.core.order;

import hello.core.discount.DiscountPoliicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPoliicy discountPoliicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPoliicy discountPoliicy) {
        this.memberRepository = memberRepository;
        this.discountPoliicy = discountPoliicy;
    }
      /*
      * DIP를 위반하지 않고 interface에만 의존 할 수 있도록
      * private DiscountPoliicy discountPoliicy;
      * 하지만 이렇게 작성하면 NullPointExeption이 발생한다
      *
      * */

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPoliicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //싱글톤 테스트 용도
    public MemberRepository getMemberRepository() {
        return  memberRepository;
    }
}
