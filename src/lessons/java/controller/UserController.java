package lessons.java.controller;

import lessons.java.data.models.User;
import lessons.java.data.service.UserService;

import java.util.Scanner;

public class UserController {
    private final UserService userService;
    private final Scanner scanner;

    public UserController(UserService userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    public void register() {
        String email;
        String password;
        boolean isRegistrationCompleted = false;

        do {
            System.out.println("Введите логин (почту): ");
            email = scanner.nextLine();

            if (email.isEmpty()) {
                System.err.println("Поле не должно быть пустым.");
            } else {
                boolean foundUser = userService.checkForUserExistence(email);

                if (foundUser) {
                    System.err.println("Такой пользователь уже существует.");
                } else {
                    isRegistrationCompleted = true;
                }
            }
        } while (email.isEmpty() || !isRegistrationCompleted);

        do {
            System.out.println("Введите пароль: ");
            password = scanner.nextLine();

            if (password.isEmpty()) {
                System.err.println("Поле не должно быть пустым.");
            }
        } while (password.isEmpty());

        User user = new User(email, password);

        User registeredUser = userService.register(user);

        if (registeredUser != null) {
            System.out.println("\nРегистрация успешна! Ваш ID: " + registeredUser.getId());
        } else {
            System.err.println("\n Ошибка при регистрации.");
            menu();
        }
    }

    public void login() {
        String email;
        String password;

        do {
            System.out.println("Введите логин (почту): ");
            email = scanner.nextLine();

            if (email.isEmpty()) {
                System.err.println("Поле не должно быть пустым.");
            }
        } while (email.isEmpty());

        do {
            System.out.println("Введите пароль: ");
            password = scanner.nextLine();

            if (password.isEmpty()) {
                System.err.println("Поле не должно быть пустым.");
            }
        } while (password.isEmpty());

        boolean loggedIn = userService.login(email, password);

        if (loggedIn) {
            System.out.println("\nУспешная авторизация!");
        } else {
            System.err.println("\nНеверный логин или пароль. Попробуйте снова.");
            menu();
        }
    }

    public void menu() {
        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Регистрация");
            System.out.println("2. Авторизация");
            System.out.println("3. Выход");
            System.out.print("Введите номер действия: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    return;
                case 3:
                    System.exit(0);
                default:
                    System.err.println("Неверный выбор. Повторите ввод.");
            }
        }
    }
}