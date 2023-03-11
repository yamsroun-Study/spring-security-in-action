package yamsroun.ssia11.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "member", schema = "ssia11")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Member {

    @Id
    private String username;

    @Setter
    private String password;
}
