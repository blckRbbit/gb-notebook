package notebook.view;

import notebook.controller.UserController;
import notebook.model.User;
import notebook.util.Commands;

import java.util.List;

public class UserView {
    private final UserController userController;

    public UserView(UserController userController) {
        this.userController = userController;
    }

    public void run() throws RuntimeException {
        Commands com;

        while (true) {
            String command = userController.prompt("Введите команду\n CREATE, READ, UPDATE, LIST, DELETE, NONE, EXIT: ");
            com = Commands.valueOf(command.toUpperCase());
            switch (com) {
                case CREATE:
                    User u = userController.createUser();
                    userController.saveUser(u);
                    break;


                case READ:
                    String id = userController.prompt("Enter user id: ");
                    System.out.println();
                    try {
                        User user = userController.readUser(Long.parseLong(id));
                        System.out.println(user + "\n");

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case UPDATE:
                    String userId = userController.prompt("Enter user id: ");
                    System.out.println();
                    userController.updateUser(userId, userController.createUser());
                    break;

                case LIST:
                    List<User> users = userController.readAll();
                    System.out.println();
                    for (User user: users) {
                        System.out.println(user);
                        System.out.println();
                    }
                    System.out.println();
                    break;

                case DELETE:
                    String removingUserId = userController.prompt("Enter user id: ");

                    System.out.println();
                    userController.delete(Long.valueOf(removingUserId));

                case EXIT:
                        System.err.println("Exiting...");
                        return;
                case NONE:
                    break;

            }
        }
    }
}
