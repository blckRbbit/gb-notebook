package notebook.view;

import notebook.controller.UserController;
import notebook.model.User;
import notebook.util.Commands;
import notebook.util.UserValidator;

import java.util.List;
import java.util.Scanner;

public class UserView {
    private final UserController userController;

    public UserView(UserController userController) {
        this.userController = userController;
    }

    public void run() throws RuntimeException {
        Commands com;

        while (true) {
            String command = prompt("Введите команду\n CREATE, READ, UPDATE, LIST, DELETE, NONE, EXIT: ");
            com = Commands.valueOf(command.toUpperCase());
            if (com == Commands.EXIT) return;
            switch (com) {
                case CREATE:
                    User u = createUser();
                    userController.saveUser(u);
                    break;

                case READ:
                    String id = prompt("Enter user id: ");
                    System.out.println();
                    try {
                        User user = userController.readUser(Long.parseLong(id));
                        System.out.println(user + "\n");

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case UPDATE:
                    String userId = prompt("Enter user id: ");
                    System.out.println();
                    userController.updateUser(userId, createUser());

                case LIST:
                    List<User> users = userController.readAll();
                    System.out.println();
                    for (User user: users) {
                        System.out.println(user);
                        System.out.println();
                    }

                case DELETE:
                    Long removingUserId = Long.valueOf(prompt("Enter user id: "));
                    // почему то при запуске в консоле LIST, выводит "Enter user id: "
                    System.out.println();
                    userController.delete(removingUserId);




            } // todo добавить исключения
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    private User createUser() { // todo подумать может перенести в другой слой
        UserValidator validator = new UserValidator();

        String firstName = prompt("Имя: ");
        String lastName = prompt("Фамилия: ");
        String phone = prompt("Номер телефона: ");
        return validator.validate(new User(firstName, lastName, phone));
    }
}
