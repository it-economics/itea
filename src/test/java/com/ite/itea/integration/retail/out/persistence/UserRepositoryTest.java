package com.ite.itea.integration.retail.out.persistence;

import com.ite.itea.retail.domain.entity.user.UserRepository;
import com.ite.itea.retail.adapters.out.persistence.FileSystemUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

class UserRepositoryTest {

    private UserRepository userRepository;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        URL usersFileURL = getClass().getClassLoader().getResource("usersWithOrders/users.txt");
        String usersFilePath = Objects.requireNonNull(usersFileURL).getFile();
        File file = new File(URLDecoder.decode(usersFilePath, StandardCharsets.UTF_8));
        userRepository = new FileSystemUserRepository(file);
    }

    @Test
    void shouldReturnAllUsersWhenAllUsersAreRequested() {
        var users = userRepository.all();

        assertThat(users.size()).isEqualTo(4);

        assertThat(users.get(0).firstname()).isEqualTo("Peter");
        assertThat(users.get(0).lastname()).isEqualTo("Pan");

        assertThat(users.get(1).firstname()).isEqualTo("Captain");
        assertThat(users.get(1).lastname()).isEqualTo("Hook");

        assertThat(users.get(2).firstname()).isEqualTo("Tinker");
        assertThat(users.get(2).lastname()).isEqualTo("Bell");

        assertThat(users.get(3).firstname()).isEqualTo("Lost");
        assertThat(users.get(3).lastname()).isEqualTo("Boys");
    }

    @Test
    void shouldReturnCaptainHookWhenAUserWithTheNameHookIsSearched() {
        var user = userRepository.byLastName("Hook");

        assertThat(user).isPresent();

        assertThat(user.get().firstname()).isEqualTo("Captain");
        assertThat(user.get().lastname()).isEqualTo("Hook");
    }

}
