package notebook.util;

import java.util.Scanner;

public class Validator {
    public String scheckLine(String line) {
        String str = "";
    int i = 0;
        while(i < 5) {
            if(!line.isEmpty()){
                str = line;
            }else{
                Scanner scanner = new Scanner(System.in);
                System.out.print("Попытка номер " + (i + 1) + "Поле не может быть пустым: ");
                line = scanner.nextLine();
            }
            i++;
        }
        str = "Default";
        return str;
    }
}
