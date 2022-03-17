package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

//미리 검증할 틀 먼저 만드는건 ttd 테스트주도 개발 
// 이건 구현 만들고 테스트 돌려봄
//테스트 코드없이 개발 불가능
public class MemoryMemberRepositoryTest {
    //순서가 보장 안됨 그래서 깔끔하게 clear 해야함
    MemoryMemberRepository repository = new MemoryMemberRepository() ;
    @AfterEach
    public void afterEach(){
        //테스트 끝날 때마다 저장소 지움 그래서 순서가 상관없어짐
    repository.clearStroe();
    }
        @Test
        public void save() {
        Member member = new Member();
        member.setName("spring");
        repository.save(member);

       Member result = repository.findById(member.getId()).get();
           // Assertions.assertEquals(member,result);
            Assertions.assertThat(member).isEqualTo(result);

        }

        @Test
        public void findByName(){
            Member member1 = new Member();
            member1.setName("spring1");
            repository.save(member1);

            Member member2 = new Member();
            member2.setName("spring1");
            repository.save(member2);

            repository.save(member2);
            Member result = repository.findByName("spring1").get();

            Assertions.assertThat(result).isEqualTo(member1);


        }

            @Test
            public void findAll(){
            Member member1 = new Member();
            member1.setName("spring1");
            repository.save(member1);

            Member member2 = new Member();
            member2.setName("spring2");
            repository.save(member2);

            List<Member> result = repository.findAll();
            Assertions.assertThat(result.size()).isEqualTo(2);
            }
    }

