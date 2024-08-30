package lessons.java.data.data_source.user;

import lessons.java.data.models.User;

public interface UserRepository {
    void save(User user);
    boolean findByEmailAndPassword(String email, String password);
    boolean findByEmail(String email);
}