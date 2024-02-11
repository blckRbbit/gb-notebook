package notebook.view;

import notebook.controller.UserController;
import notebook.model.User;
import notebook.util.Commands;
import notebook.util.UserDataValidator;
import notebook.util.UserValidator;

import java.util.List;
import java.util.Scanner;

import static jdk.nashorn.internal.objects.NativeString.toUpperCase;

public class UserView {
    private final UserController userController;

    public UserView(UserController userController) {
        this.userController = userController;
    }

    public void run(){
        Commands com;

        while (true) {
            String command = prompt("Введите команду: ");
            com = Commands.valueOf(toUpperCase(command));
            if (com == Commands.EXIT) return;
            switch (com) {
                case CREATE:
                    User u = createUser();
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
                    String userId = prompt("Enter user id: ");
                    userController.updateUser(userId, updateUserData());
                case LIST:
                    List<User> list = userController.readAll();
                    for(User user : list){
                        System.out.println(user);
                    }
                    break;
                case DELETE:
                    userId = prompt("Enter user id: ");
                    userController.deleteUser(Long.parseLong(userId));
                    break;
            }
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    private User createUser() {
        String firstName = prompt("Имя: ");
        UserDataValidator dataValidator = new UserDataValidator();
        firstName =dataValidator.validate(firstName);

        String lastName = prompt("Фамилия: ");
        lastName =dataValidator.validate(lastName);

        String phone = prompt("Номер телефона: ");
        phone =dataValidator.validate(phone);

//        UserValidator validator = new UserValidator();
        return new User(firstName, lastName, phone);
//        return validator.validate(new User(firstName, lastName, phone));
    }
    private User updateUserData(){
        String firstName = prompt("Имя: ");
        String lastName = prompt("Фамилия: ");
        String phone = prompt("Номер телефона: ");
        return new User(firstName, lastName, phone);
    }
}
