package inflearn.springbootmvc1study.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  동시성 문제가 고려되지 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {//무조건 이것으로만 조회해야한다.
        return instance;
    }
    private MemberRepository() {//싱글톤 만들때는 private으로 생성자를 생성하지 못하게 막아야한다.
    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);

        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {//스토어 자체를 보호하기 위함
        return new ArrayList<>(store.values());//list를 조작해도 value를 건들고 싶지 않아서 이렇게 한다.
    }

    public void cleanStore() {
        store.clear();
    }
}
