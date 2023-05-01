package health.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import health.beans.Vitals;

public interface VitalRepository extends JpaRepository<Vitals, Long>  {
	
}
