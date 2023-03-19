package yamsroun.ssia10.domain;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "token")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CustomToken {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private Integer id;

    @Column(unique = true)
    private String identifier;

    @Setter
    private String token;

    public CustomToken(String identifier, String token) {
        this.identifier = identifier;
        this.token = token;
    }
}