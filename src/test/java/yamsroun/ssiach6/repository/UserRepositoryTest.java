package yamsroun.ssiach6.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import yamsroun.ssiach6.domain.User;

import java.util.List;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void findAll() {
        List<User> all = userRepository.findAll();
        System.out.println("all=" + all);
    }

}