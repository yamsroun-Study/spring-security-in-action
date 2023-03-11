package yamsroun.ssia11.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import yamsroun.ssia11.domain.Member;
import yamsroun.ssia11.domain.MemberOtp;
import yamsroun.ssia11.repository.MemberOtpRepository;
import yamsroun.ssia11.repository.MemberRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    private static final String USERNAME = "TEST";
    private static final String PASSWORD = "PASSWORD";
    private static final String OTP = "abcd1234";

    @Mock PasswordEncoder passwordEncoder;
    @Mock MemberRepository memberRepository;
    @Mock MemberOtpRepository memberOtpRepository;

    @InjectMocks MemberService memberService;

    @Test
    void addMember() {
        //given
        Member member = new Member(USERNAME, PASSWORD);
        //when
        memberService.addMember(member);
        //then
        assertThat(member.getPassword()).isNotEqualTo(PASSWORD);
        then(memberRepository).should().save(member);
    }

    @Test
    @DisplayName("auth() - 인증 성공 - OTP 없는 경우")
    void auth_success_hasNoOtp() {
        //given
        Member member = new Member(USERNAME, PASSWORD);
        given(memberRepository.findById(USERNAME))
            .willReturn(Optional.of(member));
        given(passwordEncoder.matches(PASSWORD, member.getPassword()))
            .willReturn(true);
        given(memberOtpRepository.findById(USERNAME))
            .willReturn(Optional.empty());

        //when
        memberService.auth(member);
        //then
        then(memberOtpRepository).should().save(any());
    }

    @Test
    @DisplayName("auth() - 인증 성공 - OTP 있는 경우")
    void auth_success_hasOtp() {
        //given
        Member member = new Member(USERNAME, PASSWORD);
        given(memberRepository.findById(member.getUsername()))
            .willReturn(Optional.of(member));
        given(passwordEncoder.matches(PASSWORD, member.getPassword()))
            .willReturn(true);

        MemberOtp otp = new MemberOtp(USERNAME, OTP);
        given(memberOtpRepository.findById(USERNAME))
            .willReturn(Optional.of(otp));

        //when
        memberService.auth(member);
        //then
        then(memberOtpRepository).should(never()).save(any());
    }

    @Test
    @DisplayName("auth() - 인증 실패 - 다른 패스워드")
    void auth_fail_wrongPassword() {
        //given
        Member member = new Member(USERNAME, PASSWORD);
        given(memberRepository.findById(member.getUsername()))
            .willReturn(Optional.of(member));
        given(passwordEncoder.matches(PASSWORD, member.getPassword()))
            .willReturn(false);
        //when, then
        assertThatThrownBy(() -> memberService.auth(member))
            .isInstanceOf(BadCredentialsException.class);
    }

    @Test
    @DisplayName("auth() - 인증 실패 - 없는 Member")
    void auth_fail_hasNoMember() {
        //given
        Member member = new Member(USERNAME, PASSWORD);
        given(memberRepository.findById(member.getUsername()))
            .willReturn(Optional.empty());
        //when, then
        assertThatThrownBy(() -> memberService.auth(member))
            .isInstanceOf(BadCredentialsException.class);
    }

    @Test
    @DisplayName("check() - 체크 성공")
    void check_success() {
        //given
        MemberOtp otp = new MemberOtp(USERNAME, OTP);
        given(memberOtpRepository.findById(USERNAME))
            .willReturn(Optional.of(otp));
        //when
        boolean result = memberService.check(otp);
        //then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("check() - 체크 실패 - OTP 코드 다름")
    void check_fail_notEqualsOtp() {
        //given
        MemberOtp otp = new MemberOtp(USERNAME, OTP);
        MemberOtp otp2 = new MemberOtp(USERNAME, OTP + "2");
        given(memberOtpRepository.findById(USERNAME))
            .willReturn(Optional.of(otp2));
        //when
        boolean result = memberService.check(otp);
        //then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("check() - 체크 실패 - OTP 없음")
    void check_fail_hasNoOtp() {
        //given
        MemberOtp otp = new MemberOtp(USERNAME, OTP);
        given(memberOtpRepository.findById(USERNAME))
            .willReturn(Optional.empty());
        //when
        boolean result = memberService.check(otp);
        //then
        assertThat(result).isFalse();
    }
}