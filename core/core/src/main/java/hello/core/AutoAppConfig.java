package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
//컴포넌트 스캔해서 자동 등록하는데 excludeFilters 이건 뺄거를 지정해줌
//지금 AppConfig도 Configuration 등록되어있어서
//실무에서는 제외 안함 기존 예제 코드 떄문에 이 방법
@ComponentScan(
        //모든 자바코드에서 찾으면 오래 걸리니까 basePackages 사용
        //basePackages = "hello.core.member",
        //지정한 클래스의 패키지를 탐색 시작 위치로 지정한다
        //basePackageClasses = AutoAppConfig.class,
        //지정안하면  ?? 면 @ComponentScan 이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다
        //그래서 설정 정보 클래스의 위치를 프로젝트 최상단에 두는 것!!
        excludeFilters  = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    @Bean(name ="memoryMemberRepository")
    MemberRepository memberRepository(){
        return  new MemoryMemberRepository();
    }
}
