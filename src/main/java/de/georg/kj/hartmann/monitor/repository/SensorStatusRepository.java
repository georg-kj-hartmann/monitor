package de.georg.kj.hartmann.monitor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.georg.kj.hartmann.monitor.model.SensorStatus;

@Repository
public interface SensorStatusRepository extends JpaRepository<SensorStatus, Long>{

	Optional<SensorStatus> findByName(String Name);
	Optional<SensorStatus> findBySensorId(Long sensorId);
}
