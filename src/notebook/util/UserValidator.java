package notebook.util;

import notebook.model.User;

public class UserValidator {
    public User validate(User user) {
        if (isFieldEmpty(user.getFirstName())
                || isFieldEmpty(user.getLastName())
                || isFieldEmpty(user.getPhone())) {
            throw new IllegalArgumentException("Введены некорректные данные");
        }
        user.setFirstName(user.getFirstName().replaceAll(" ", "").trim());
        user.setLastName(user.getLastName().replaceAll(" ", "").trim());
        user.setPhone(user.getPhone().replaceAll(" ", "").trim());
        return user;
    }

    private boolean isFieldEmpty(String field) {
        return field == null || field.trim().isEmpty();
    }
}
