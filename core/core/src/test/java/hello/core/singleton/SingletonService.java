package hello.core.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonService {

    // 자기 자신을 내부에 private으로 가지고 있는데  뭘로 가지고 있냐면 static으로 가지고 있음
    // 이렇게 하면 class레벨에 올라가기 때문에 딱 하나만 존재하게 된다.
    // 자기 자신을 instance 객체를 딱 생성해서 여기 안에만 들어가 있는거지
        private static final  SingletonService  instance = new SingletonService();

        public static SingletonService getInstance(){
            return instance;
        }

        private  SingletonService(){
        }

        public void logic(){
            System.out.println("싱클톤 객체 로직 호출");
        }


    }
