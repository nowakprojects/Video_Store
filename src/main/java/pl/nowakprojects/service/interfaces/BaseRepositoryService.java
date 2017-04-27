package pl.nowakprojects.service.interfaces;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by Mateusz on 23.04.2017.
 */
public interface BaseRepositoryService<T,ID> {
    List<T> findAll();
    T save(T entity);
    Optional<T> findOne(ID id);
}
