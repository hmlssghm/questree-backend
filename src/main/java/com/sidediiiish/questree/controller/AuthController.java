package com.sidediiiish.questree.controller;

import com.sidediiiish.questree.domain.Auth;
import com.sidediiiish.questree.dto.MemberForm;
import com.sidediiiish.questree.repository.AuthRepository;
import com.sidediiiish.questree.repository.MemberRepository;
import io.jsonwebtoken.JwtBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

@AllArgsConstructor
@Controller
public class AuthController {
    JwtBuilder jwtBuilder;
    SecretKey secretKey;
    AuthRepository authRepository;
    MemberRepository memberRepository;

    @GetMapping("/login")
    public String getLoginPage() {
        return "members/login";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MemberForm form){
        if(memberRepository.findByName(form.getName()).isEmpty() ||
                memberRepository.findByName(form.getName()).isPresent() &&
                        !memberRepository.findByName(form.getName()).get().getPassword().equals(form.getPassword())){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        Date expiredAt = Date.from(Instant.now().plus(Duration.ofDays(1L)));
        String jwtId = UUID.randomUUID().toString();

        Map<String, Object> headerMap = new HashMap<String, Object>();
        headerMap.put("typ", "JWT");
        headerMap.put("alg", "HS256");

        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("name", form.getName());

        String token = jwtBuilder.setHeader(headerMap)
                .setClaims(claims)
                .setExpiration(expiredAt)
                .setId(jwtId)
                .signWith(secretKey)
                .compact();

        authRepository.save(Auth.create(token));
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @GetMapping("/svc")
    public ResponseEntity<String> svc(@RequestHeader("Authorization") String token){
        if(!authRepository.existsByToken(token.split(" ")[1])) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>("Login된 사용자입니다.", HttpStatus.OK);
    }

}
