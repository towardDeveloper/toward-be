package toward.towardbackend.web.repositpory;

import org.springframework.data.jpa.repository.JpaRepository;
import toward.towardbackend.web.domain.entity.Member;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long> {


    Optional<Member> findByAccount(String account);

    Optional<Member> findByKakaoIdentifier(Long kakaoIdentifier);

    boolean existsByAccount(String account);
}
