package com.example.sherlock_chan_car_rental_service.security;


import com.example.sherlock_chan_car_rental_service.security.service.TokenService;
import io.jsonwebtoken.Claims;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Configuration
public class SecurityAspect {

    @Value("${oauth.jwt.secret}")
    private String jwtSecret;

    private TokenService tokenService;

    public SecurityAspect(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Around("@annotation(com.example.sherlock_chan_car_rental_service.security.CheckSecurity)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        String token = null;
        for (int i = 0; i < methodSignature.getParameterNames().length; i++) {
            if (methodSignature.getParameterNames()[i].equals("authorization")) {
                if (joinPoint.getArgs()[i].toString().startsWith("Bearer")) {
                    token = joinPoint.getArgs()[i].toString().split(" ")[1];
                }
            }
        }

        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Claims claims = tokenService.parseToken(token);

        if (claims == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        CheckSecurity checkSecurity = method.getAnnotation(CheckSecurity.class);
        String type = claims.get("type", String.class);
        if (Arrays.asList(checkSecurity.userTypes()).contains(type)) {
            return joinPoint.proceed();
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

}
