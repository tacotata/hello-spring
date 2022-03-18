package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

// 2. 자바 코드로 직접 스프링 빈 등록
//멤버서비스 멤버리포 스프링 빈에 등록하고
//스프링빈에 등록되어있는 멤버리포를 서비스에 넣어줌
@Configuration
public class SpringConfig {

      private final  DataSource dataSource;
    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        //return new MemoryMemberRepository();
       // return new JdbcMemberRepository(dataSource);
        //조립해줘야지 jdbc 템플릿
        return  new JdbcTemplateMemberRepository(dataSource);
    }

}
