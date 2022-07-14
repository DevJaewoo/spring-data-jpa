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

    @Test
    public void findByUsernameAndAgeGreaterThan() throws Exception {
        //given
        Member member1 = new Member("member", 10);
        Member member2 = new Member("member", 20);
        memberJPARepository.save(member1);
        memberJPARepository.save(member2);

        //when
        List<Member> result = memberJPARepository.findByUsernameAndAgeGreaterThan("member", 15);

        //then
        assertThat(result.get(0).getUsername()).isEqualTo("member");
        assertThat(result.get(0).getAge()).isEqualTo(20);
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    public void testNamedQuery() throws Exception {
        //given
        Member member1 = new Member("member", 10);
        Member member2 = new Member("member", 20);
        memberJPARepository.save(member1);
        memberJPARepository.save(member2);

        //when
        List<Member> result = memberJPARepository.findByUsername("member");

        //then
        assertThat(result.get(0).getUsername()).isEqualTo("member");
        assertThat(result.get(0).getAge()).isEqualTo(10);
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    public void paging() throws Exception {
        //given
        memberJPARepository.save(new Member("member1", 10));
        memberJPARepository.save(new Member("member2", 10));
        memberJPARepository.save(new Member("member3", 10));
        memberJPARepository.save(new Member("member4", 10));
        memberJPARepository.save(new Member("member5", 10));
        memberJPARepository.save(new Member("member6", 10));

        int age = 10;
        int offset = 0;
        int limit = 3;

        //when
        List<Member> members = memberJPARepository.findByPage(age, offset, limit);
        long totalCount = memberJPARepository.totalCount(age);

        //then
        assertThat(members.size()).isEqualTo(3);
        assertThat(totalCount).isEqualTo(6);
    }

    @Test
    public void bulkUpdate() throws Exception {
        //given
        memberJPARepository.save(new Member("member1", 10));
        memberJPARepository.save(new Member("member2", 20));
        memberJPARepository.save(new Member("member3", 30));
        memberJPARepository.save(new Member("member4", 40));
        memberJPARepository.save(new Member("member5", 50));
        memberJPARepository.save(new Member("member6", 60));

        //when
        int resultCount = memberJPARepository.bulkAgePlus(20);

        //then
        assertThat(resultCount).isEqualTo(5);
    }
}