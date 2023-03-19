package yamsroun.ssia11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yamsroun.ssia11.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

}
