package toward.towardbackend.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import toward.towardbackend.utils.MessageUtils;
import toward.towardbackend.web.exception.BaseExceptionResponse;
import toward.towardbackend.web.exception.BaseResponse;
import toward.towardbackend.web.controller.dto.response.KakaoLoginResponseDto;
import toward.towardbackend.web.exception.BaseResponseCode;
import toward.towardbackend.web.service.KakaoService;

import static toward.towardbackend.web.exception.BaseResponseCode.OK;

@Tag(name = "kakao", description = "카카오 로그인 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class KakaoController {

    private final KakaoService kakaoService;

    // 프론트에서 인가코드 받아오는 url
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = MessageUtils.SUCCESS),
            @ApiResponse(responseCode = "400", description = MessageUtils.ERROR,
                    content = @Content(schema = @Schema(implementation = BaseExceptionResponse.class)))
    })
    @Operation(summary = "카카오 코드 전송하는 곳", description = "카카오로부터 받은 코드 전송해주세요. 로그인 처리 후 인증 토큰, 이름, 회원 번호 리턴됩니다.")
    @GetMapping("/auth/kakao/callback")
    public BaseResponse<KakaoLoginResponseDto> getLogin(@Parameter(name = "code", example = "adb1b219fb", required = true ) @RequestParam("code") String code) { //(1)
        log.info("code = {}", code);
        return new BaseResponse<>(OK.getHttpStatus(), OK.getMessage(), kakaoService.kakaoLogin(code));
    }
}
