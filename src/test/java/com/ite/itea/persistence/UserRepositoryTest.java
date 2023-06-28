package com.ite.itea.persistence;

import com.ite.itea.domain.user.UserRepository;
import com.ite.itea.persistence.user.UserMapper;
import com.ite.itea.persistence.user.FileSystemUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static org.assertj.core.api.BDDAssertions.then;

class UserRepositoryTest {

    private File file;

    private UserRepository userRepository;

    private UserMapper userMapper;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        URL usersFileURL = getClass().getClassLoader().getResource("usersWithOrders/users.txt");
        String usersFilePath = Objects.requireNonNull(usersFileURL).getFile();
        file = new File(URLDecoder.decode(usersFilePath, StandardCharsets.UTF_8));
        userMapper = new UserMapper();
        userRepository = new FileSystemUserRepository(file, userMapper);
    }

    @Test
    void shouldReturnAllUsersWhenAllUsersAreRequested() {
        var users = userRepository.getAllUsers();

        then(users.size()).isEqualTo(4);
        then(users.get(0).firstname()).isEqualTo("Peter");
        then(users.get(0).lastname()).isEqualTo("Pan");
        then(users.get(1).firstname()).isEqualTo("Captain");
        then(users.get(1).lastname()).isEqualTo("Hook");
        then(users.get(2).firstname()).isEqualTo("Tinker");
        then(users.get(2).lastname()).isEqualTo("Bell");
        then(users.get(3).firstname()).isEqualTo("Lost");
        then(users.get(3).lastname()).isEqualTo("Boys");
    }

    @Test
    void shouldReturnCaptainHookWhenAUserWithTheNameHookIsSearched() {
        var user = userRepository.getUserByLastname("Hook");

        then(user.firstname()).isEqualTo("Captain");
        then(user.lastname()).isEqualTo("Hook");
    }

}