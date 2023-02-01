package yamsroun.ssiach6.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class Authority {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auth_id")
    private Integer id;

    private String name;

    @JoinColumn(name = "member_id")
    @ManyToOne
    private User user;
}
