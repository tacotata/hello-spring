package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {


    MemberService memberService;
    MemoryMemberRepository memberRepository ;

// 멤버 서비스에 넣어줌// 외부에서 멤버리포지토리를 넣어줌  이런걸 DI 임
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach(){
        //테스트 끝날 때마다 저장소 지움 그래서 순서가 상관없어짐
        memberRepository.clearStroe();
    }

    /*
    * 회원가입
    * */

    @Test
    void 회원가입() {
        //given 주어졌어 이걸 기반으로 하구나
        Member member = new Member();
        member.setName("spring");

        //when 실행했어 이걸 검증하구나
    Long saveId = memberService.join(member);

        //then 결과가 이게 되야해 검증부구나
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//        try {
//            memberService.join(member2);
//            fail();
//        }catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        //then
    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}