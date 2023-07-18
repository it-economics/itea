package com.ite.itea.retail.adapters.out.persistence;

import com.ite.itea.retail.domain.entity.user.User;
import com.ite.itea.retail.domain.entity.user.UserRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FileSystemUserRepository implements UserRepository {

    private final File usersFile;

    public FileSystemUserRepository(File usersFile) {
        this.usersFile = usersFile;
    }

    public List<User> all() {
        return parseUsersFromFile(usersFile);
    }

    public Optional<User> byLastName(String lastName) {
        return parseUsersFromFile(usersFile).stream()
                .filter(user -> user.lastname().equals(lastName))
                .findFirst();
    }

    private List<User> parseUsersFromFile(File usersFile) {
        try {
            final var lines = Files.readAllLines(usersFile.toPath());
            return lines.stream()
                    .map(this::parseUser)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private User parseUser(String line) {
        String[] userFromFile = line.split(" ");
        String[] ordersFromFile = userFromFile[2].split(",");
        return new User(userFromFile[0], userFromFile[1], Arrays.asList(ordersFromFile));
    }
}
