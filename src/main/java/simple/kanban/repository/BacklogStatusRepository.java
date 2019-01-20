package simple.kanban.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import simple.kanban.model.BacklogStatus;

public interface BacklogStatusRepository extends JpaRepository<BacklogStatus, Integer> {

}
