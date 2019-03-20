package ro.ubb.bookstore.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ro.ubb.bookstore.core.domain.BaseEntity;

import java.io.Serializable;

@NoRepositoryBean
public interface Repository<T extends BaseEntity<ID>,
        ID extends Serializable>
        extends JpaRepository<T, ID> {
}
