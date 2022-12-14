package com.cuadratura.app.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cuadratura.app.mysql.entity.AjusteComentario;
import com.cuadratura.app.service.AjusteComentarioService;

@RestController
@RequestMapping(path = "/api/wmsmysql")
@CrossOrigin(origins = "*")
public class AjusteComentarioController {
	private static final Logger LOGGER = LogManager.getLogger(AjusteComentarioController.class);

	@Autowired
	private AjusteComentarioService ajusteComentarioService;

	@PostMapping(value = "/registroAjusteComentario")
	public ResponseEntity<String> registroAjusteComentario(@Valid @RequestBody AjusteComentario ajusteComentario) {
		LOGGER.info("registroAjusteComentario");
		try {
			this.ajusteComentarioService.saveAjusteComentario(ajusteComentario);
			return ResponseEntity.status(HttpStatus.OK).body("Registro Correcto");
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
		}
	}

	@GetMapping(value = "/getAjusteComentario")
	public ResponseEntity<AjusteComentario> getAjusteComentario(@RequestParam Integer idAjusteComentario) {

		LOGGER.info("getAjusteComentario  idAjusteComentario " + idAjusteComentario);

		// List<AjustePmmWmsDto> result =
		// ajusteComentarioService.findByIdAjusteComentario(idAjusteComentario);
		/*
		 * return Optional.ofNullable(ajusteComentarioService.findByIdAjusteComentario(
		 * idAjusteComentario)) .map(result -> new
		 * ResponseEntity<AjusteComentario>(HttpStatus.FOUND)) .orElseGet(() -> new
		 * ResponseEntity<AjusteComentario>(HttpStatus.NOT_FOUND));
		 */


		Optional<AjusteComentario> tutorialData = this.ajusteComentarioService.findByIdAjusteComentario(idAjusteComentario);

		if (tutorialData.isPresent()) {
			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
}
