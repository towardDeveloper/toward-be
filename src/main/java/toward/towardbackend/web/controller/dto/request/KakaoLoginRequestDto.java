package toward.towardbackend.web.controller.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import toward.towardbackend.web.controller.kakaoLoginDto.KakaoProfile;
import toward.towardbackend.web.domain.entity.Member;
import toward.towardbackend.web.domain.enums.MemberRole;

@Data
@NoArgsConstructor //컨트롤러에서 사용 안 됌.
public class KakaoLoginRequestDto {
    private String account;

    private String password;

    private String name;

    private String profileImageFileName;

    private Long kakaoIdentifier;

    public KakaoLoginRequestDto(KakaoProfile profile) {
        this.account = profile.getProperties().getNickname() + profile.getId(); //닉네임과 카카오 식별자를 가지고 임의로 account를 만듦.
        this.password = "password";
        this.name = profile.getProperties().getNickname();
        this.profileImageFileName = profile.getProperties().getProfile_image();
        this.kakaoIdentifier = profile.getId();
    }

    public Member toEntity(){
        return Member.builder()
                .account(this.account)
                .password(this.password)
                .name(this.name)
                .profileImageFileName(this.profileImageFileName)
                .kakaoIdentifier(this.kakaoIdentifier)
                .role(MemberRole.ROLE_MEMBER) //Role 지정해줘야 인증 할 수 있다.
                .build();
    }

}
