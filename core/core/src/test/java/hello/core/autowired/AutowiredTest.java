package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

   static class  TestBean{
        //(required = true) 기본값 빈으로 등록된거 없어서 터짐
        @Autowired(required = false) //1번 자체 호출이 안됨
        // 의존관계가 없어서 메소드 자체 호출이 안됨
        //member는 스프링빈이 관리하는 컨테이너가 아니다
       public void setNoBean1(Member noBean1){
            System.out.println("noBean1 = " + noBean1);
        }

       @Autowired
       //호출은 되나 null로 들어감
       public void setNoBean2(@Nullable Member noBean2){
           System.out.println("noBean2 = " + noBean2);//null
       }
       
       @Autowired
       //자바 8 Optional
       public void setNoBean(Optional<Member> noBean3){
           System.out.println("noBean3 = " + noBean3);//Optional.empty
       }
   }
}
