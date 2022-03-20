package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//JpaRepository 스프링 데이터 jpa 가 제공하는거 구현체 , 등록 crud 단순조회 다 제공됨

public interface SpringDataJpaMemberRepository  extends JpaRepository<Member, Long>,MemberRepository {
    // Jpql select m from Member m where m,name =?
    //인터페이스만으로 끝이남 , 메서드 이름만으로 조회 기능 제공
    //페이징 기능 자동 제공
    //동적쿼리는 qauerydsl 라이브러리 사용
    //jpa도 순수한 쿼리 사용가능 , jdbcTemplate , 마이바티스 같이 사용해도 됨
    @Override
    Optional<Member> findByName(String name);
}
