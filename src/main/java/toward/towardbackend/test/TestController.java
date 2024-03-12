package toward.towardbackend.test;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import toward.towardbackend.utils.MessageUtils;
import toward.towardbackend.web.controller.dto.response.HelloResponse;
import toward.towardbackend.web.exception.BaseExceptionResponse;
import toward.towardbackend.web.exception.BaseResponse;
import toward.towardbackend.web.exception.BaseResponseCode;

import static toward.towardbackend.web.exception.BaseResponseCode.OK;

@Tag(name = "test", description = "테스트 API")
@Controller
@Slf4j
@RequestMapping("/api")
public class TestController {

    @GetMapping("/hello")
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = MessageUtils.SUCCESS),
            @ApiResponse(responseCode = "400", description = MessageUtils.BAD_REQUEST,
                    content = @Content(schema = @Schema(implementation = BaseExceptionResponse.class)))
    })
    public BaseResponse<HelloResponse> hello() {
        log.info("GET : /api/hello");
        return new BaseResponse<>(OK.getHttpStatus(), OK.getMessage(), new HelloResponse("hello"));
    }





}
