package study.springdatajpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.springdatajpa.entity.Member;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired private MemberRepository memberRepository;

    @Test
    public void testMember() throws Exception {
        //given
        Member member = new Member("memberA");
        memberRepository.save(member);

        //when
        Member result = memberRepository.findById(member.getId()).get();

        //then
        assertThat(result.getId()).isEqualTo(member.getId());
        assertThat(result.getUsername()).isEqualTo(member.getUsername());
        assertThat(result).isEqualTo(member);
    }
}