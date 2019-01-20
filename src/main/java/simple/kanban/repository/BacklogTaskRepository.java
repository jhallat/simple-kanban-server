package simple.kanban.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import simple.kanban.model.BacklogTask;

public interface BacklogTaskRepository extends JpaRepository<BacklogTask, Integer>{

}
