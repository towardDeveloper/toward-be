package toward.towardbackend.web.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;

@Data
public class KakaoLoginResponseDto {

    @Schema(description = "JWT 인증 토큰", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QWNjb3VudCIsImFjY291bnQiOiJ0ZXN0QWNjb3VudCIsImlhdCI6MTcxMDIyMTI1MCwiZXhwIjoxNzEwODI2MDUwfQ.wpMIUytr8MpqxGpFAJIlF8kG9OSm2KJE7xeUWQHVnAU")
    String token;

    @Schema(description = "회원 이름", example = "이원준")
    String memberName;

    @Schema(description = "회원 번호", example = "1")
    Long memberId;

    public KakaoLoginResponseDto(String token, String memberName, Long memberId) {
        this.token = token;
        this.memberName = memberName;
        this.memberId = memberId;
    }
}
