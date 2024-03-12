package toward.towardbackend.web.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "test hello response")
public class HelloResponse {

    @Schema(description = "테스트데이터", example = "hello")
    String message;

    public HelloResponse(String message) {
        this.message = message;
    }
}
