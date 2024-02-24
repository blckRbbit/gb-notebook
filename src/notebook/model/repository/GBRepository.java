package notebook.model.repository;

import notebook.model.User;
import notebook.view.UserView;

import java.util.List;
import java.util.Optional;

public interface GBRepository {
    List<User> findAll();
    User create(User user);
    Optional<User> findById(Long id);
    Optional<User> update(Long userId, User user);
    boolean delete(Long id);
    User createUser(UserView view);
    List<String> readAll();
    void saveAll(List<String> data);
}
