package notebook.controller;

import notebook.model.User;
import notebook.model.repository.GBRepository;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class UserController implements Controller {
    private final GBRepository repository;

    public UserController(GBRepository repository) {
        this.repository = repository;
    }

    public void saveUser(User user) {
        repository.create(user);
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

    public void updateUser(String userId, User update) {
        update.setId(Long.parseLong(userId));
        repository.update(Long.parseLong(userId), update);
    }

    public void deleteUser(Long userId) {
        System.out.println(repository.delete(userId) ? "Пользователь с ID : " + userId + " удалён." : "Пользователь с таким ID не найден");
    }

    public List<User> readAll() {
        return repository.findAll();
    }

    public User createUser() {
        String firstName = prompt("Имя: ");
        String lastName = prompt("Фамилия: ");
        String phone = prompt("Номер телефона: ");
        return new User(firstName, lastName, phone);
    }

    public String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine().toUpperCase(Locale.ROOT).replaceAll(" ", "").trim();
    }
}
