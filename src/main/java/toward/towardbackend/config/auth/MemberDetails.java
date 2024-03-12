package toward.towardbackend.config.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import toward.towardbackend.web.domain.entity.Member;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 */

@RequiredArgsConstructor
@Getter
public class MemberDetails implements UserDetails {

    private final Long memberId;
    private final String account;
    private final String password;
    private final String role;

    public MemberDetails(Member member) {
        this.memberId = member.getId();
        this.account = member.getAccount();
        this.password = member.getPassword();
        this.role = member.getRole().toString();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return account;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
