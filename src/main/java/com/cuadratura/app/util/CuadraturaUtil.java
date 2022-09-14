package com.cuadratura.app.util;

import java.security.NoSuchAlgorithmException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class CuadraturaUtil {
	protected static final Log logger = LogFactory.getLog(CuadraturaUtil.class);

	public static String returnTipoInventario(String codInventario) throws NoSuchAlgorithmException {
		String nombreHomlogado = "";
		if (codInventario.equalsIgnoreCase("1")) {
			nombreHomlogado = "";
		}
		else if (codInventario.equalsIgnoreCase("2")) {
			nombreHomlogado = "";
		}
		else if (codInventario.equalsIgnoreCase("3")) {
			nombreHomlogado = "";
		}
		else if (codInventario.equalsIgnoreCase("4")) {
			nombreHomlogado = "";

		}
		else if (codInventario.equalsIgnoreCase("5")) {
			nombreHomlogado = "";
		}
		else if (codInventario.equalsIgnoreCase("6")) {
			nombreHomlogado = "";
		}
		else if (codInventario.equalsIgnoreCase("7")) {
			nombreHomlogado = "";
		}
		else if (codInventario.equalsIgnoreCase("8")) {
			nombreHomlogado = "";

		}
		else if (codInventario.equalsIgnoreCase("9")) {
			nombreHomlogado = "";
		}
		else if (codInventario.equalsIgnoreCase("10")) {
			nombreHomlogado = "";

		}
		return nombreHomlogado;
	}
}
