package notebook.controller;

import notebook.model.User;
import notebook.model.repository.GBRepository;
import notebook.util.Commands;
import notebook.util.UserValidator;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class UserController {
    private final GBRepository repository;

    public UserController(GBRepository repository) {
        this.repository = repository;
    }

    public void saveUser(User user) {
        repository.create(user);
    }

    public void deleteUser(Long userId) {
        repository.delete(userId);
    }

    public User readUser(Long userId) throws Exception {
        List<User> users = repository.findAll();
        for (User user : users) {
            if (Objects.equals(user.getId(), userId)) {
                return user;
            }
        }

        throw new RuntimeException("User not found");
    }

    public List<User> readAll() {
        return repository.findAll();
    }

    public void updateUser(String userId, User update) {
        update.setId(Long.parseLong(userId));
        repository.update(Long.parseLong(userId), update);
    }

    public User createUser() {
        UserValidator validator = new UserValidator();
        User user;

        do {
            String firstName = prompt("Имя: ");
            String lastName = prompt("Фамилия: ");
            String phone = prompt("Номер телефона: ");

            user = new User(firstName, lastName, phone);

            try {
                user = validator.validate(user);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                // Устанавливаем user в null, чтобы повторно запросить ввод данных
                user = null;
            }
        } while (user == null); // Повторять запрос, если введены некорректные данные

        return user;
    }

    public String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine().toUpperCase(); // Преобразование входной строки в верхний регистр
    }

    public void printAllCommands() {
        System.out.println("Available commands:");
        for (Commands command : Commands.values()) {
            System.out.println(command);
        }
    }
}
