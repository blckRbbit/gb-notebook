package notebook.controller;

import notebook.model.User;
import notebook.model.repository.GBRepository;
import notebook.view.UserView;

import java.util.List;
import java.util.Objects;

public class UserController {
    private final GBRepository repository;

    public UserController(GBRepository repository) {
        this.repository = repository;
    }

    public void saveUser(User user) {
        repository.create(user);
    }

    public User readUser(Long userId) throws Exception {
        List<User> users = repository.findAll();
        for (User user : users) {
            if (Objects.equals(user.getId(), userId)) {
                return user;
            }
        }

        throw new RuntimeException("User not found");
    }

    public void updateUser(String userId, User user) {
        Long id = Long.parseLong(userId);
        user.setId(id);
        repository.update(id, user);
    }

    public List<User> readAll() {
        return repository.findAll();
    }

    public boolean delete(String id) {
        return repository.delete(Long.parseLong(id));
    }

    public User createUser(UserView view) {
        return repository.createUser(view);
    }
}
