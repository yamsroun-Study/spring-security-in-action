package yamsroun.ssia11.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "member_otp", schema = "ssia11")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class MemberOtp {

    @Id
    private String username;

    @Setter
    private String code;
}
