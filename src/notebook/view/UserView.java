package notebook.view;

import notebook.controller.UserController;
import notebook.model.User;
import notebook.util.Commands;

public class UserView {
    private final UserController userController;

    public UserView(UserController userController) {
        this.userController = userController;
    }

    public void run(){
        Commands com;

        while (true) {
            String command = userController.prompt("Введите команду: ");
            // Проверяем, ввел ли пользователь команду
            if (command.trim().isEmpty()) {
                com = Commands.NONE;  // Запускаем команду NONE
            } else {
                try {
                    com = Commands.valueOf(command);
                } catch (IllegalArgumentException e) {
                    System.out.println("Неверная команда. Пожалуйста, повторите ввод.");
                    continue; // Перезапускаем цикл
                }
            }

            if (com == Commands.EXIT) return;
            switch (com) {
                case NONE:
                    userController.printAllCommands();
                    break;
                case CREATE:
                    User u = userController.createUser();
                    userController.saveUser(u);
                    break;
                case READ:
                    String id = userController.prompt("Идентификатор пользователя: ");
                    try {
                        User user = userController.readUser(Long.parseLong(id));
                        System.out.println(user);
                        System.out.println();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case LIST:
                    System.out.println(userController.readAll());
                    break;
                case UPDATE:
                    String userId = userController.prompt("Введите идентификатор пользователя: ");
                    userController.updateUser(userId, userController.createUser());
                    break;
                case DELETE:
                    String userId2 = userController.prompt("Ввведите идентификатор пользователя: ");
                    userController.deleteUser(Long.parseLong(userId2));
                    break;
            }
        }
    }
}
