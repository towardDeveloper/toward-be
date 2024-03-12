package toward.towardbackend.web.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import toward.towardbackend.web.domain.entity.Member;

@Data
@NoArgsConstructor
@Schema(description = "member details response")
public class MemberDetailsResponseDto {


    @Schema(description = "회원 계졍", example = "testAccount")
    String account;

    @Schema(description = "회원 이름", example = "이원준")
    String name;

    public MemberDetailsResponseDto(Member member) {
        this.account = member.getAccount();
        this.name = member.getName();
    }
}
