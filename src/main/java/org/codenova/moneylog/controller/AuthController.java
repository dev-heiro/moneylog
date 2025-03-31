package org.codenova.moneylog.controller;


import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codenova.moneylog.service.KakaoApiService;
import org.codenova.moneylog.vo.KakaoTokenResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/auth")
public class AuthController {
    private KakaoApiService kakaoApiService;


    @GetMapping("/login")
    public String loginHandle(Model model) {
        // log.info("loginHandle...executed");

        model.addAttribute("kakaoClientId", "847b759335c33e6b4f353f471db9a868");
        model.addAttribute("kakaoRedirectUri", "http://192.168.10.62:8080/auth/kakao/callback");

        return "auth/login";
    }


    @GetMapping("/kakao/callback")
    public String kakaoCallbackHandle(@RequestParam("code") String code) throws JsonProcessingException {
        // log.info("code = {}", code);
        KakaoTokenResponse response =kakaoApiService.exchangeToken(code);
        log.info("response.idToken = {}", response.getIdToken());

        DecodedJWT decodedJWT = JWT.decode(response.getIdToken());
        String sub = decodedJWT.getClaim("sub").toString();
        String nickname = decodedJWT.getClaim("nickname").toString();
        String picture = decodedJWT.getClaim("picture").toString();

        log.info("decodedJWT: sub={}, nickname={}, picture={} ", sub, nickname, picture);
        return "redirect:/";
    }
}
