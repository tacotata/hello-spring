package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//구현체가 하나만 있을 때는 구현체 뒤에 관례상 imple이라고 많이 씀
public class MemberServiceImple implements MemberService{
    //인터페이스만 가지고 하면 오류 그래서 구현 객체 선택
    private final MemberRepository memberRepository ;

    @Autowired //ac.getBean(MemberRepository.class)
    public MemberServiceImple(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //MemoryMemberRepository 호출 됨
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
