package toward.towardbackend.web.exception;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BaseException extends RuntimeException{
    public final BaseResponseCode baseResponseCode;
}
