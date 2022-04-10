package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImple;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    //@Bean -> MemberService -> new MemoryMemberRepository()
    //@Bean -> OrderService -> new MemoryMemberRepository()
    //이러면 싱글톤 깨지는거 아니냐 두번 호출되는데 -> 같은 인스턴스가 조회되네

    //이렇게 호출할거 같은데
    //call AppConfig.memberService
    //call AppConfig.memberRepository"
    //call AppConfig.memberRepository"
    //call AppConfig.orderService
    //call AppConfig.memberRepository

    //결과
    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.orderService

    //역할과 구현 클래스가 한눈에 들어와야함 애플리케이션 전체 구성 어떻게 되어있는지 빠르게 파악
    @Bean
    public MemberService memberService(){
        System.out.println(" call AppConfig.memberService");
        return new MemberServiceImple(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
        System.out.println(" call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());

    }
    @Bean
    public DiscountPolicy discountPolicy(){
        return new RateDiscountPolicy();
    }
}
