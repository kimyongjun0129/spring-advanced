package org.example.expert.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    @Pointcut("execution(* org.example.expert.*.*.*.CommentAdminService.deleteComment(..)) || execution(* org.example.expert.*.*.*.UserAdminService.changeUserRole(..))")
    public void serviceMethods() {}

    @Before("serviceMethods()")
    public void beforeLogging() throws IOException {
        log.info("요청 사용자 ID : {}", request.getAttribute("userId"));
    }

    @After("serviceMethods()")
    public void afterLogging() throws Throwable {
    }
}
