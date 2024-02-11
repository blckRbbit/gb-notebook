package notebook.util;

public class UserDataValidator {
    public String validate(String userData){
        if(userData.isEmpty()){
            throw new IllegalArgumentException("Введены не корректные данные");
        }
        userData=userData.replaceAll(" ", "").trim();
        return userData;
    }
}
