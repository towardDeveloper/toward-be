package toward.towardbackend.web.controller.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "로그인 요청 정보")
public class LoginRequestDto {

    @Schema(description = "회원 계정", example = "testAccount")
    String account;

    @Schema(description = "회원 비밀번호", example = "testPassword")
    String password;
}
