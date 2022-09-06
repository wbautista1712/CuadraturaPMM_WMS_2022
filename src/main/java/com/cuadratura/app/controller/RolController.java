package com.cuadratura.app.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cuadratura.app.mysql.entity.Rol;
import com.cuadratura.app.service.RolService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class RolController{

	private static final Logger LOGGER = LogManager.getLogger(RolController.class);

	@Autowired
	private RolService rolService;
	
	@GetMapping("/roles")
	public List<Rol> index() {
		LOGGER.info("RolController ");
		return rolService.findAll();
	}
	
	@PostMapping(value = "/crearRol")
	public ResponseEntity<?> create(@Valid @RequestBody Rol rol)
	{
		rolService.save(rol);
		return new ResponseEntity<>("Insertado correctamente", HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/rol/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Rol rol, @PathVariable Integer id)
	{
		Rol rolActual=rolService.findById(id);
		
		rolActual.setNombrerol(rol.getNombrerol());
		if(rol.isEstado())
		{
			rolActual.setEstado(true);
		}
		else
		{
			rolActual.setEstado(false);
		}
		
		rolService.save(rolActual);
		
		return new ResponseEntity<>("Modificado correctamente", HttpStatus.OK);
	}
	
	@Transactional
	@DeleteMapping(value = "/rol/{id}")
	public ResponseEntity<?> delete(@Valid @PathVariable Integer id)
	{
		rolService.delete(id);
		
		return new ResponseEntity<>("Eliminado correctamente", HttpStatus.OK);
	}
}
