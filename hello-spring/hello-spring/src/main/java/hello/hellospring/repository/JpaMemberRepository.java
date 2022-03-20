package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    //jpa는 EntityManager로 모든걸 동작함
    //스프링부트가 자동으로 연결해주고 injection 받으면 됨
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public Member save(Member member) {
        //이렇게 하면 insert 쿼리 다 만들어서 쿼리에 집어넣고 setId까지 다해줌
         em.persist(member);
         return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
      Member member =  em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
         List<Member> result = em.createQuery("select m from Member m where m.name =:name", Member.class)
                 .setParameter("name", name)
                 .getResultList();

         return  result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        //alt + ctrl + n = inline
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}


