package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig { //생성자 주입( 연결 )

    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderService -> new MemoryMemberRepository()
    //MemoryMemberRepository가 두개가 생성된다 이러면 싱글톤이 과연 깨지는거냐? No

    /* ConfigurationSingletonTest
    * 예상                                 결과
    * call AppConfig.memberService        * call AppConfig.memberService
    * call AppConfig.memberRepository     * call AppConfig.memberRepository
    * call AppConfig.memberRepository     * call AppConfig.orderService
    * call AppConfig.orderService
    * call AppConfig.memberRepository
    */

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() { //역할
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() { //역할
        return new RateDiscountPolicy();
//        return new FixDiscountPolicy();
    }
}
