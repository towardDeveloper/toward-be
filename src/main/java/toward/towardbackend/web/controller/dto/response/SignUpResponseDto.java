package toward.towardbackend.web.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "member signUp response")
public class SignUpResponseDto {

    @Schema(description = "회원번호", example = "1")
    Long memberId;

    public SignUpResponseDto(Long memberId) {
        this.memberId = memberId;
    }
}
