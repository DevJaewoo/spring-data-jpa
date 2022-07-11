package study.springdatajpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.springdatajpa.entity.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

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
        Member result = memberJPARepository.find(member.getId());

        //then
        assertThat(result.getId()).isEqualTo(member.getId());
        assertThat(result.getUsername()).isEqualTo(member.getUsername());
        assertThat(result).isEqualTo(member);
    }

    @Test
    public void basicCRUD() {
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        memberJPARepository.save(member1);
        memberJPARepository.save(member2);

        //단건 조회 검증
        Member findMember1 = memberJPARepository.findById(member1.getId()).get();
        Member findMember2 = memberJPARepository.findById(member2.getId()).get();
        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);

        //리스트 조회 검증
        List<Member> all = memberJPARepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        //카운트 검증
        long count = memberJPARepository.count();
        assertThat(count).isEqualTo(2);

        //삭제 검증
        memberJPARepository.delete(member1);
        memberJPARepository.delete(member2);
        long deletedCount = memberJPARepository.count();
        assertThat(deletedCount).isEqualTo(0);
    }
}