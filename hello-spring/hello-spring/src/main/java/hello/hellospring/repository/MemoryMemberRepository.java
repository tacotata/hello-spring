package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
//여기서 데이터 저장
public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;


    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)) ;
    }

    @Override
    public Optional<Member> findByName(String name) {
       return store.values().stream()
               //파라미터 네임이랑 같은지 확인
               .filter(member -> member.getName().equals(name))
               .findAny();    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStroe(){
        store.clear();
    }
}
