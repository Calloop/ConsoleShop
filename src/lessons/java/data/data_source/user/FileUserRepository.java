package lessons.java.data.data_source.user;

import lessons.java.data.models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUserRepository implements UserRepository {

    private final String filePath;

    public FileUserRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void save(User user) {
        List<User> users = loadUsers();
        long nextId = getNextId(users);
        user.setId(nextId);
        users.add(user);
        saveUsers(users);
    }

    @Override
    public boolean findByEmailAndPassword(String email, String password) {
        List<User> users = loadUsers();
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean findByEmail(String email) {
        List<User> users = loadUsers();
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    private List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 3) {
                    throw new IllegalArgumentException("Неверный формат данных в файле users.txt");
                }
                long id = Long.parseLong(parts[0]);
                String email = parts[1];
                String password = parts[2];
                User user = new User(email, password);
                user.setId(id);
                users.add(user);
            }
        } catch (IOException e) {
            if (e instanceof FileNotFoundException) {
                saveUsers(users);
            } else {
                e.printStackTrace();
            }
        }
        return users;
    }

    private void saveUsers(List<User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (User user : users) {
                writer.write(user.getId() + "," + user.getEmail() + "," + user.getPassword() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private long getNextId(List<User> users) {
        if (users.isEmpty()) {
            return 1L;
        }
        return Math.max(users.stream().mapToLong(User::getId).max().orElse(0L), 1L) + 1;
    }
}