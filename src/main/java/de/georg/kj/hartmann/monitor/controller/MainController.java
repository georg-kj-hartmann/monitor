package de.georg.kj.hartmann.monitor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import de.georg.kj.hartmann.monitor.repository.SensorStatusRepository;

@Controller
public class MainController {

	@Autowired
	private SensorStatusRepository repository;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("sensorStatusList", repository.findAll());
		model.addAttribute("message","Test");
		return "home";

	}
}
