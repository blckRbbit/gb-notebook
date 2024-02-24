package notebook.view;

import notebook.controller.UserController;
import notebook.model.User;
import notebook.util.Commands;
import notebook.util.Strings;

import java.util.List;
import java.util.Scanner;

public class UserView {
    private final UserController userController;

    public UserView(UserController userController) {
        this.userController = userController;
    }

    public boolean run() throws Exception {
        Commands com;

        while (true) {
            String command = prompt("Введите команду: ");
            com = Commands.valueOf(command);
            if (com == Commands.EXIT) return false;
            String userId;
            switch (com) {
                case CREATE:
                    User u = userController.createUser(this);

                    String validationResult = validateNewUser(u);
                    if (!validationResult.isEmpty()) {
                        throw new Exception(validationResult);
                    }

                    userController.saveUser(u);
                    break;
                case READ:
                    String id = prompt("Идентификатор пользователя: ");
                    try {
                        User user = userController.readUser(Long.parseLong(id));
                        System.out.println(user);
                        System.out.println();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case UPDATE:
                    userId = prompt("Enter user id: ");
                    userController.updateUser(userId, userController.createUser(this));
                    break;
                case LIST:
                    List<User> users = userController.readAll();
                    for (User user : users) {
                        System.out.println(user);
                    }
                    break;
                case DELETE:
                    userId = prompt("Enter user id: ");
                    if (!userController.delete(userId)) {
                        System.out.println("Не удалось удалить запись");
                    }
                    break;
            }
        }
    }

    private String validateNewUser(User user) {
        String fn = user.getFirstName();
        if (fn.isEmpty()) {
            return "Имя должно быть заполнено";
        }
        String ln = user.getLastName();
        if (ln.isEmpty()) {
            return "Фамилия должна быть заполнена";
        }

        Strings checker = new Strings();
        if (checker.hasSpace(fn)) {
            return "Имя не должно содержать пробелов";
        }
        if (checker.hasSpace(ln)) {
            return "Фамилия не должна содержать пробелов";
        }

        return "";
    }
}
