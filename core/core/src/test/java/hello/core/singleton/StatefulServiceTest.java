package hello.core.singleton;

import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA : A사용자 10000원 주문
       // statefulService1.order("userA",  10000);
        //ThreadB : B사용자 20000원 주문
        //statefulService2.order("userB",  20000);
        //변경 무상태
        int userAPrice =  statefulService1.order("userA",  10000);
        int userBPrice = statefulService2.order("userB",  20000);


        //ThreadA: 사용자A 주문 금액 조회 -
      //  int price = statefulService1.getPrice();
        System.out.println("price = " + userAPrice);//price = 20000 인스턴스를 같은 애 사용하니가
//price = 10000
     //   Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }

}