package inflearn.springbootmvc1study.domain.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance(); //싱글톤이기 때문에 new 하면안된다.

    @AfterEach
    void afterEach() {
        memberRepository.cleanStore();
    }

    @Test
    void save() {
        //given
        Member member = new Member("hello", 20);

        //when
        Member saveMemmber = memberRepository.save(member);

        //then
        Member findMember = memberRepository.findById(saveMemmber.getId());
        assertThat(findMember).isEqualTo(saveMemmber);
    }

    @Test
    void findAll() {
        //given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);

        memberRepository.save(member1);
        memberRepository.save(member2);
        //when
        List<Member> result = memberRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1, member2);//result안에 member1객체와 member2객체가 있는지 확인
    }
}