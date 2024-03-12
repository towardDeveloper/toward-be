package toward.towardbackend.web.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import toward.towardbackend.config.jwt.JwtTokenProvider;
import toward.towardbackend.web.controller.dto.request.LoginRequestDto;
import toward.towardbackend.web.controller.dto.request.SignUpRequestDto;
import toward.towardbackend.web.controller.dto.response.LoginResponseDto;
import toward.towardbackend.web.controller.dto.response.MemberDetailsResponseDto;
import toward.towardbackend.web.controller.dto.response.SignUpResponseDto;
import toward.towardbackend.web.domain.entity.Member;
import toward.towardbackend.web.exception.BaseException;
import toward.towardbackend.web.exception.BaseResponseCode;
import toward.towardbackend.web.repositpory.MemberRepository;

@RequiredArgsConstructor
@Service
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;


    public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto) {
        signUpRequestDto.setPassword(passwordEncoder.encode(signUpRequestDto.getPassword()));
        if (memberRepository.existsByAccount(signUpRequestDto.getAccount())) {
            throw new BaseException(BaseResponseCode.USER_DUPLICATE);
        }

        Member member = memberRepository.save(signUpRequestDto.toEntity());
        return new SignUpResponseDto(member.getId());
    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Member member = memberRepository.findByAccount(loginRequestDto.getAccount()).orElseThrow(() -> new BaseException(BaseResponseCode.USER_NOT_FOUND));
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), member.getPassword())) {
            throw new BaseException(BaseResponseCode.INVALID_PASSWORD);
        }

        String token = jwtTokenProvider.createToken(member.getAccount());

        return new LoginResponseDto(token, member.getName(), member.getId());
    }

    public MemberDetailsResponseDto getMember(Long memberId){
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new BaseException(BaseResponseCode.USER_NOT_FOUND));
        return new MemberDetailsResponseDto(member);
    }
}
