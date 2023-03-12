package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // 조회만 할때, 쓰는애들보다 조회만 하는 애들이 많으면 여기다 써줌
@RequiredArgsConstructor // lombok 사용하여 final 로 된 멤버변수만 생성자 생성.
public class MemberService {
    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    @Transactional // 얘는 읽기만 하는게 아니라 쓰기도 하니까. 여기다 추가하면 얘가 우선
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 인증 -> 있으면 exception 발생
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMemberName = memberRepository.findByName(member.getName());
        if(!findMemberName.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

}
