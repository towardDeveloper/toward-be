package toward.towardbackend.web.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import toward.towardbackend.config.auth.MemberDetails;
import toward.towardbackend.utils.MessageUtils;
import toward.towardbackend.web.controller.dto.request.LoginRequestDto;
import toward.towardbackend.web.controller.dto.response.SignUpResponseDto;
import toward.towardbackend.web.exception.BaseException;
import toward.towardbackend.web.exception.BaseExceptionResponse;
import toward.towardbackend.web.exception.BaseResponse;
import toward.towardbackend.web.controller.dto.request.SignUpRequestDto;
import toward.towardbackend.web.controller.dto.response.LoginResponseDto;
import toward.towardbackend.web.controller.dto.response.MemberDetailsResponseDto;
import toward.towardbackend.web.exception.BaseResponseCode;

import toward.towardbackend.web.service.MemberService;

import java.util.Objects;

import static toward.towardbackend.web.exception.BaseResponseCode.OK;


@Tag(name = "member", description = "멤버 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
@Slf4j
public class MemberController {


    private final MemberService memberService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = MessageUtils.SUCCESS),
            @ApiResponse(responseCode = "400", description = MessageUtils.BAD_REQUEST,
                    content = @Content(schema = @Schema(implementation = BaseExceptionResponse.class)))
    })
    @Operation(summary = "일반 회원가입", description = "account, password 기반 일반 회원가입입니다. <br>리턴 데이터는 회원번호입니다")
    @PostMapping("/signUp")
    public BaseResponse<SignUpResponseDto> signUp(@Parameter(name = "회원가입 위한 회원 정보들", required = true) @Valid @RequestBody SignUpRequestDto signUpRequestDto) {
        return new BaseResponse<>(OK.getHttpStatus(), OK.getMessage(), memberService.signUp(signUpRequestDto));
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = MessageUtils.SUCCESS),
            @ApiResponse(responseCode = "404", description = MessageUtils.NOT_FOUND,
                    content = @Content(schema = @Schema(implementation = BaseExceptionResponse.class)))
    })
    @Operation(summary = "일반 로그인", description = "account, password 기반 일반 로그인입니다.")
    @PostMapping("/login")
    public BaseResponse<LoginResponseDto> login(@Parameter(name = "로그인 위한 회원 정보들", required = true) @RequestBody LoginRequestDto loginRequestDto) {
        return new BaseResponse<>(OK.getHttpStatus(), OK.getMessage(), memberService.login(loginRequestDto));
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = MessageUtils.SUCCESS),
            @ApiResponse(responseCode = "401", description = MessageUtils.UNAUTHORIZED,
                    content = @Content(schema = @Schema(implementation = BaseExceptionResponse.class))),
            @ApiResponse(responseCode = "404", description = MessageUtils.NOT_FOUND,
                    content = @Content(schema = @Schema(implementation = BaseExceptionResponse.class)))
    })
    @Operation(summary = "멤버정보 조회", description = "parameter : memberId로 회원을 조회합니다. <br>인증토큰 필요")
    @GetMapping("/details/{memberId}")
    public BaseResponse<MemberDetailsResponseDto> memberDetails(@Parameter(name = "memberId", example = "1", required = true) @PathVariable Long memberId,
                                                                @AuthenticationPrincipal MemberDetails memberDetails) {
        log.info("memberDetails.getMemberId() = {}", memberDetails.getMemberId());
        if (!Objects.equals(memberId, memberDetails.getMemberId())) {
            throw new BaseException(BaseResponseCode.AUTHORIZATION_NOT_VALID);
        }
        return new BaseResponse<>(OK.getHttpStatus(), OK.getMessage(), memberService.getMember(memberId));
    }

}