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
class MemberJPARepositoryTest {

    @Autowired private MemberJPARepository memberJPARepository;

    @Test
    public void testMember() throws Exception {
        //given
        Member member = new Member("memberA");
        memberJPARepository.save(member);

        //when
        Member result = memberJPARepository.findById(member.getId());

        //then
        assertThat(result.getId()).isEqualTo(member.getId());
        assertThat(result.getUsername()).isEqualTo(member.getUsername());
        assertThat(result).isEqualTo(member);
    }
}