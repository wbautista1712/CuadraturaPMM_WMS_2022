package com.cuadratura.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cuadratura.app.mysql.entity.Usuario;
import com.cuadratura.app.mysql.entity.UsuarioRol;
import com.cuadratura.app.oracle.dto.projection.UsuarioDto;
import com.cuadratura.app.service.UsuarioRolService;
import com.cuadratura.app.service.UsuarioService;

@RestController
@RequestMapping("/api/seguridad")
@CrossOrigin(origins = "*")
public class UsuarioController {
	
	private static final Logger LOGGER = LogManager.getLogger(UsuarioController.class);
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRolService usuarioRolService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@GetMapping("/usuarios")
	public List<Usuario> index() {
		LOGGER.info("UsuarioController ");
		return usuarioService.findAll();
	}

	@GetMapping("/usuarios/page/{page}")
	public Page<Usuario> index(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 4);
		return usuarioService.findAll(pageable);
	}

	@GetMapping("/usuarios/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {

		Usuario usuario = null;
		Map<String, Object> response = new HashMap<>();

		try {
			usuario = usuarioService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (usuario == null) {
			response.put("mensaje", "El Usuario ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	

	@Secured("ROLE_ADMIN")
	@PostMapping("/usuarioCreate")
	public ResponseEntity<?> create(@Valid @RequestBody Usuario usuario, BindingResult result, @RequestParam Integer idRol) {
		passwordEncoder = new BCryptPasswordEncoder();
		Usuario usuarioNew = null;
		Map<String, Object> response = new HashMap<>();
        Integer idUser = null;
        UsuarioRol usuarioRol = new UsuarioRol();
		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
			//usuarioNew = usuarioService.save(usuario);// cambiar aqui
			
			idUser = this.usuarioService.saveUsuario(usuario).intValue();
			LOGGER.info("idRol ===>> "+idRol);
			LOGGER.info("idUser ===>> "+idUser);
			usuarioRol.setIdRol(idRol);
			usuarioRol.setIdUsuario(idUser);
			this.usuarioRolService.save(usuarioRol);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El usuario ha sido creado con éxito!");
		response.put("cliente", usuarioNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@Secured("ROLE_ADMIN")
	@PutMapping("/usuarioUpdate/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Usuario usuario, BindingResult result, @PathVariable Long id, @RequestParam Integer idRol ) {

		Usuario usuarioActual = usuarioService.findById(id);
	//	Usuario usuarioUpdated = null;
		UsuarioRol usuarioRolActual= new UsuarioRol();
		
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (usuarioActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el Usuario ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			usuarioActual.setAppaterno(usuario.getAppaterno().toUpperCase());
			usuarioActual.setApmaterno(usuario.getApmaterno().toUpperCase());
			usuarioActual.setNombres(usuario.getNombres().toUpperCase());
		//	usuarioActual.setEmail(usuario.getEmail());
		//	usuarioActual.setUsername(usuario.getUsername());
		//	usuarioActual.setPassword(passwordEncoder.encode(usuario.getPassword()));
			usuarioActual.setEstado(usuario.getEstado());
			usuarioActual.setIdusuario(id);
		//	usuarioUpdated = this.usuarioService.save(usuarioActual);
			this.usuarioService.updateUsuario(usuarioActual);
			
			//actualizacion rol
			
			usuarioRolActual =this.usuarioRolService.findUsuarioRol(usuarioActual.getIdusuario().intValue());
			
			this.usuarioRolService.updateUsuarioRol(usuarioRolActual.getIdUsuariorol().intValue(), idRol);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el cliente en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El usuario ha sido actualizado con éxito!");
		//response.put("cliente", usuarioActual);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/listUsuarioRol")
	public ResponseEntity<?> listarUsuarioRol() 
	{
		 List<UsuarioDto>  list =	this.usuarioService.getUsuarioRol();
		 return new ResponseEntity<List<UsuarioDto> >(list, HttpStatus.OK);
	
	}
}
