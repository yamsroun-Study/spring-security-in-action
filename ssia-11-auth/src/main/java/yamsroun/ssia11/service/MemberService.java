package yamsroun.ssia11.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yamsroun.ssia11.domain.Member;
import yamsroun.ssia11.domain.MemberOtp;
import yamsroun.ssia11.repository.MemberOtpRepository;
import yamsroun.ssia11.repository.MemberRepository;
import yamsroun.ssia11.util.GenerateCodeUtils;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final MemberOtpRepository memberOtpRepository;

    public void addMember(Member member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberRepository.save(member);
    }

    public void auth(Member member) {
        boolean noAuth = memberRepository.findById(member.getUsername())
            .map(m -> renewOtpIfPasswordEquals(member, m))
            .isEmpty();
        if (noAuth) {
            throw new BadCredentialsException("Bad credentials");
        }
    }

    private Member renewOtpIfPasswordEquals(Member member, Member m) {
        if (passwordEncoder.matches(member.getPassword(), m.getPassword())) {
            renewOtp(m);
            return m;
        }
        return null;
    }

    private void renewOtp(Member member) {
        String code = GenerateCodeUtils.generateCode();
        memberOtpRepository.findById(member.getUsername())
            .ifPresentOrElse(
                otp -> updateOtpCode(otp, code),
                () -> createOtp(member.getUsername(), code)
            );
    }

    private void updateOtpCode(MemberOtp otp, String code) {
        otp.setCode(code);
    }

    private void createOtp(String username, String code) {
        MemberOtp otp = new MemberOtp(username, code);
        memberOtpRepository.save(otp);
    }

    public boolean check(MemberOtp otp) {
        return memberOtpRepository.findById(otp.getUsername())
            .map(o -> otp.getCode().equals(o.getCode()))
            .orElse(false);
    }
}
