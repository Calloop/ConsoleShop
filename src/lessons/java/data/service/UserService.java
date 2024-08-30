package lessons.java.data.service;

import lessons.java.data.data_source.user.FileUserRepository;
import lessons.java.data.models.User;

public class UserService {
    private final FileUserRepository userRepository;

    public UserService(FileUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(User user) {
        userRepository.save(user);
        return user;
    }

    public boolean checkForUserExistence(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean login(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }
}