package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

public class SingletonWithPrototypeTest1 {
    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1);


    }

    @Test
    void singletonClientUserPrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class,PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        Assertions.assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean1.logic();
        Assertions.assertThat(count2).isEqualTo(1);
        //그래서 2가 반환되는거야 아오


    }

    @Scope("singleton")
    static class ClientBean{
      //  private final PrototypeBean prototypeBean;// 생성시점에 주입 그래서 계속 같은거 사용함

        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;

//        @Autowired
//        //이 때 프로토타입 내놔 !! 만져서 던져 줌
//        public ClientBean(PrototypeBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }

        public int logic(){
            //prototypeBeanProvider getObject 호출하면 그 때서야 스프링 컨테이너에서 프로토타입 찾아서 반환
            //찾아주는 기능만 제공
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
           int count =  prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
        static class PrototypeBean {
            private int count =0 ;

            public void addCount(){
                count++;
            }

            public int getCount(){
                return count;
            }
        @PostConstruct
        public void init(){
                System.out.println("PrototypeBean.init" + this);
            }
        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");

        }
        }

}
