package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /* 회원가입 */
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> checkMember = memberRepository.findByName(member.getName());
        if(checkMember.size() > 0) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /* 회원전체조회 */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /* 회원단건조회 */
    public Member findMember(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
