package pl.kuziow.notemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kuziow.notemanager.entity.NoteCrumbEntity;

@Repository
public interface NoteCrumbRepository extends JpaRepository<NoteCrumbEntity, Long> {
}
