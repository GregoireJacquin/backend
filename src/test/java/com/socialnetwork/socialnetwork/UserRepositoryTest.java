package com.socialnetwork.socialnetwork;
import static org.assertj.core.api.Assertions.assertThat;

import com.socialnetwork.socialnetwork.user.User;
import com.socialnetwork.socialnetwork.user.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {
    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    UserRepository  userRepository;

    @Test
    public void findByUsername_whenUserExists_returnsUser(){
        User user = new User();
        user.setUsername("test-username");
        user.setDisplayName("test-displaynme");
        user.setPassword("P4ssword");

        testEntityManager.persist(user);

        User inDB = userRepository.findByUsername("test-username");
        assertThat(inDB).isNotNull();
    }
    @Test
    public void findByUsername_whenUserDoesNotExists_returnsUser(){
        User inDB = userRepository.findByUsername("testnoexist");
        assertThat(inDB).isNull();
    }
}
