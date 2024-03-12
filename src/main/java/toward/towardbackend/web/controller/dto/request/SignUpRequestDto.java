package toward.towardbackend.web.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import toward.towardbackend.web.domain.entity.Member;
import toward.towardbackend.web.domain.enums.MemberRole;

@Data
@NoArgsConstructor
@Schema(description = "회원가입 요청 정보")
public class SignUpRequestDto {

    @NotBlank
    @Schema(description = "계정", example = "testAccount")
    private String account;

    @NotBlank
    @Schema(description = "비밀번호", example = "testPassword")
    private String password;

    @NotBlank
    @Schema(description = "이름", example = "이원준")
    private String name;

    @Schema(description = "프로필 사진 경로")
    private String profileImageFileName;

    public Member toEntity(){
        return Member.builder()
                .account(account)
                .password(password)
                .name(name)
                .profileImageFileName(profileImageFileName)
                .role(MemberRole.ROLE_MEMBER) //Role 지정해줘야 인증 할 수 있다.
                .build();
    }
}
