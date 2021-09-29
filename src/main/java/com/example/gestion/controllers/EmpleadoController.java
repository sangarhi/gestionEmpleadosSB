package com.example.gestion.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.gestion.common.Sede;
import com.example.gestion.entities.Empleado;
import com.example.gestion.repositories.EmpleadoRepository;

@Controller
@RequestMapping("/empleado")
public class EmpleadoController {
	
	Logger log = LoggerFactory.getLogger(EmpleadoController.class);
	
	@Autowired
	EmpleadoRepository repository;
	
	@GetMapping
	public String getEmpleados(Model model) {
		log.debug("getEmpleados()");
		
		model.addAttribute("listado", repository.findAll());
		model.addAttribute("empleados", Sede.values());
		
		return "empleados";
	}
	
	@GetMapping("/{id}")
	public String getEmpleado(Model model, @PathVariable Long id) {
		log.debug("getEmpleado()");
		
		model.addAttribute("empleado", repository.getById(id));
		model.addAttribute("empleados", Sede.values());
		
		return "empleado";
	}
	
	@PostMapping
	public String createEmpleado(@ModelAttribute Empleado empleado) {
		log.debug("createEmpleado()");
		
		repository.save(empleado);
		
		return "redirect:/empleado";
	}
	
	@PostMapping("/{id}")
	public String updateEmpleado(@ModelAttribute Empleado empleado, @PathVariable Long id) {
		log.debug("updateEmpleado()");
		repository.save(empleado);
		
		return "redirect:/empleado/" + id;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteEmpleado(@PathVariable Long id) {
		log.debug("deleteEmpleado()");
		
		repository.deleteById(id);
		
		return "redirect:/empleado";
	}

}
