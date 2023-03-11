package yamsroun.ssia11.web.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import yamsroun.ssia11.domain.Member;
import yamsroun.ssia11.domain.MemberOtp;
import yamsroun.ssia11.service.MemberService;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;

    @PostMapping("/user/add")
    public void addUser(@RequestBody Member member) {
        memberService.addMember(member);
    }

    @PostMapping("/user/auth")
    public void auth(@RequestBody Member member) {
        memberService.auth(member);
    }

    @PostMapping("/otp/check")
    public void check(@RequestBody MemberOtp otp, HttpServletResponse response) {
        if (memberService.check(otp)) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
