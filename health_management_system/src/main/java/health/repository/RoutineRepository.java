package health.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import health.beans.Routines;

public interface RoutineRepository extends JpaRepository<Routines, Long>  {
    List<Routines> findByRoutineNameContainingIgnoreCase(String routineName);

}
