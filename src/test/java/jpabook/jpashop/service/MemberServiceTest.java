package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class) // ??? junit 실행할때, "나 스프링이랑 같이 엮어서 실행할래!"
@SpringBootTest // 스프링 컨테이너 안에서 이 테스트를 돌리기 위함. 안하면 저 Autowired 실패함. 스프링부트를 띄워놓고 테스트하려먼 필수
@Transactional // 얘를 걸어둬야 롤백이 됨. 다른데 붙이면 롤백안되고 테스트 케이스에서만 롤백됨.
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    @Rollback(false) //이렇게 말고
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long savedId = memberService.join(member);

        //then
        // assertEpuals -> org.junit.Assert.* 에 있는 놈.
        //em.flush(); // 이렇게 하면 DB에 쿼리문이 강제로 날라감. 즉 DB에 반영됨. 하지만 테스트가 끝마녀 Transactional에 의해 insert문도 롤백됨.
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        memberService.join(member2);
//        try { -> @Test 위에 처럼 하면됨
//            memberService.join(member2); // 여기서 발생하는 예외를 잡아야 되니까.
//        } catch (IllegalStateException e) {
//            return;
//        }

        //then
        fail("예외가 발생해야 한다."); // fail() -> 얘도 org.junit.Assert.* 에 있는 애. 코드가 돌다가 여기까지 오면 안됨. 오면 뭔가 잘못된거임.
    }
}