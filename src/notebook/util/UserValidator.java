package notebook.util;

import notebook.model.User;

public class UserValidator {
    public User validate(User user) {
        String name = user.getFirstName().replaceAll(" ", "").trim();
        String lName = user.getLastName().replaceAll(" ", "").trim();
        user.setFirstName(name);
        user.setLastName(lName);
        return user;

    }

//    public String removeSpacesInLine (String stringIn){
//        stringIn.replaceAll(" ","").trim();
//        return stringIn;
//    }
}
