package notebook.view;

import notebook.controller.UserController;
import notebook.model.User;
import notebook.util.Commands;
import notebook.util.Validator;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class UserView {
    private final UserController userController;

    public UserView(UserController userController) {
        this.userController = userController;
    }

    public String scheckLine(String line){
        line = line.trim().replace(" ", "");
        if(!line.isEmpty()){
            return line;
        }else {
            System.out.println("Значение не может быть пустым.");
            line = prompt("Введите корректные данные: ");
            return line;
        }
    }

    public void listCommands(){
        String inputListCommands = Arrays.toString(Commands.values())
                .replace("[", "")
                .replace("]", "");
        String[] test = inputListCommands.split(", ");
        for (int i = 0; i < test.length; i++) {
            if(!test[i].equals("NONE")) {
                System.out.println(test[i]);
            }
        }
    }
    public void run(){
        Commands com;

        while (true) {
            String id;
            String command = prompt("Введите команду: ").toUpperCase();
            try {
                com = Commands.valueOf(command);
                if (com == Commands.EXIT) return;
                switch (com) {
                    case CREATE:
                        User u = createUser();
                        userController.saveUser(u);
                        break;
                    case READ:
                        id = prompt("Идентификатор пользователя: ");
                        try {
                            User user = userController.readUser(Long.parseLong(id));
                            System.out.println(user);
                            System.out.println();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case READALL:
                        System.out.println(userController.readAll());
                        break;
                    case UPDATE:
                        id = prompt("Введите идентификатор пользователя: ");
                        userController.updateUser(id, createUser());
                        break;
                    case LIST:
                        System.out.println("Список доступных команд: ");
                        listCommands();
                        break;
                    case DELETE:
                        id = prompt("Идентификатор пользователя на удаление: ");
                        userController.deleteUser(id);
                        break;
                }
            }catch (Exception e){
                System.out.println("Неверная команда или неверный индекс.\nПовторите попытку.");
            }
        }

    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    private User createUser() {
        String firstName = scheckLine(prompt("Имя: "));
        String lastName = scheckLine(prompt("Фамилия: "));
        String phone = scheckLine(prompt("Номер телефона: "));
        return new User(firstName, lastName, phone);
    }
}
