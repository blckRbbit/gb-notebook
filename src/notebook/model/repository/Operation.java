package notebook.model.repository;

import java.util.List;

/**
 * Data Access Object (DAO) слой, с методами для работы с БД
 */
public interface Operation {
    List<String> readAll();
    void saveAll(List<String> data);
}
