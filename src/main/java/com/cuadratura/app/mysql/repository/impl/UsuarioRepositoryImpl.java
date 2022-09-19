package com.cuadratura.app.mysql.repository.impl;

import java.sql.PreparedStatement;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.cuadratura.app.mysql.entity.Usuario;
import com.cuadratura.app.mysql.repository.UsuarioRepositoryCustom;
import com.cuadratura.app.oracle.dto.UsuarioDto;

@Repository
@Transactional
public class UsuarioRepositoryImpl implements UsuarioRepositoryCustom{
private static final Logger LOGGER = LogManager.getLogger(UsuarioRepositoryImpl.class);
	
	@Autowired
	@Qualifier("jdbctemplateTwo")
	private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public Long saveUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		String INSERT_MESSAGE_SQL = "INSERT INTO cuadratura.usuario (apPaterno,apMaterno,nombres,email,password,estado,username) VALUES (?,?,?,?,?,?,?) ";

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(INSERT_MESSAGE_SQL, new String[] { "idUsuario" });
			
			ps.setString(1, usuario.getAppaterno().toUpperCase());
			ps.setString(2, usuario.getApmaterno().toUpperCase());
			ps.setString(3, usuario.getNombres().toUpperCase());
			ps.setString(4, usuario.getEmail());
			ps.setString(5, usuario.getPassword());
			ps.setBoolean(6, usuario.getEstado());
			ps.setString(7, usuario.getUsername());
			
			return ps;
			
		}, keyHolder);
		
		LOGGER.info("id recupera usuario");

		return keyHolder.getKey().longValue();
	}
	
	public void updateUsuario(Usuario usuario) {
		LOGGER.info("getAppaterno "+   usuario.getAppaterno());
		LOGGER.info("getApmaterno "+   usuario.getApmaterno());
		/*  this.jdbcTemplate.update(
	                " UPDATE  cuadratura.usuario x  set x.apPaterno  = ? , "
	                + " x.apMaterno  =  ? , x.nombres  =  ? "
	                + " WHERE  x.idUsuario =  ? ", 
	                usuario.getAppaterno(), usuario.getApmaterno(),  usuario.getNombres(), usuario.getIdusuario());
		  */
		  String sql = " UPDATE  pmm.usuario x  set x.apPaterno  = ? , "
	                + " x.apMaterno  =  ? , x.nombres  =  ? "
	                + " WHERE  x.idUsuario =  ? ";
		
		    jdbcTemplate.update(sql,
				   usuario.getAppaterno(),
				   usuario.getApmaterno(),
				   usuario.getNombres(),  usuario.getIdusuario());
	}

	public   List<UsuarioDto> getUsuarioRol(){
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT U.idUsuario,U.apPaterno, U.apMaterno, U.nombres, U.email, U.username, R.idRol, R.nombreRol "
				+ "FROM pmm.usuario U "
				+ "INNER JOIN pmm.usuario_rol UR ON U.idUsuario=UR.idUsuario "
				+ "INNER JOIN pmm.rol R ON UR.idRol=R.idRol ");
		LOGGER.info(sb.toString());

	        List<UsuarioDto> customers = jdbcTemplate.query(
	        		sb.toString(),
	                new BeanPropertyRowMapper(UsuarioDto.class));

	        return customers;
	}
}
