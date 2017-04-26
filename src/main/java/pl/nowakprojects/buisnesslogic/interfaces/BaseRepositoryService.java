package pl.nowakprojects.buisnesslogic.interfaces;

import org.springframework.transaction.annotation.Transactional;
import pl.nowakprojects.database.entity.Customer;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * Created by Mateusz on 23.04.2017.
 */
public interface BaseRepositoryService<T,ID> {
    List<T> findAll();
    T save(T entity);
    //void delete(ID id);
    Optional<T> findOne(ID id);
}
