package de.georg.kj.hartmann.monitor.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.georg.kj.hartmann.monitor.model.SensorStatus;
import de.georg.kj.hartmann.monitor.repository.SensorStatusRepository;

@RestController
public class SensorStatusController {

	@Autowired
	private SensorStatusRepository repository;

	@GetMapping("/sensorstatus")
	List<SensorStatus> all() {
		return repository.findAll();
	}

	@PostMapping("/sensorstatus")
	SensorStatus newEmployee(@RequestBody SensorStatus newEmployee) {
		return repository.save(newEmployee);
	}

	@GetMapping("/sensorstatus/update")
	SensorStatus updateByGet(
			@RequestParam(name = "sensorid", required = false) Long sensorId,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "value", required = false) Float value) {

		if (sensorId == null) {
			throw new IllegalArgumentException();
		}
				
		SensorStatus status = repository.findBySensorId(sensorId).orElseGet(() -> repository.save(new SensorStatus()));
		status.setSensorId(sensorId);
		if (value != null) {
			status.setValue(value);
		}
		
		if(StringUtils.isNotBlank(name)) {
			status.setName(name);
		}
		return repository.save(status);
	}

	@GetMapping("/sensorstatus/{id}")
	SensorStatus one(@PathVariable Long id) {

		return repository.findById(id).orElseThrow(() -> new IllegalArgumentException(id.toString()));

	}

	@PutMapping("/sensorstatus/{id}")
	SensorStatus updateStatus(@RequestBody SensorStatus newStatus, @PathVariable Long id) {

		return repository.findById(id).map(status -> {
			status.setName(newStatus.getName());
			status.setValue(newStatus.getValue());
			return repository.save(status);
		}).orElseGet(() -> {
			newStatus.setId(id);
			return repository.save(newStatus);
		});
	}

	@DeleteMapping("/sensorstatus/{id}")
	void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}

}
