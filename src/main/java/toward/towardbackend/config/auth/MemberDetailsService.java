package toward.towardbackend.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import toward.towardbackend.web.domain.entity.Member;
import toward.towardbackend.web.repositpory.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberDetailsService  implements UserDetailsService {

    private final MemberRepository memberRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByAccount(username).orElseThrow(() -> new UsernameNotFoundException("user name not found!"));
        return new MemberDetails(member);
    }
}
