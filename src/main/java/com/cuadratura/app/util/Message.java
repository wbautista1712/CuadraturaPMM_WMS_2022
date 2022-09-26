package com.cuadratura.app.util;

import java.io.Serializable;

public class Message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer codError;
    private String msjError;
    private String dato01;
    private String dato02;
    private String dato03;

    public Integer getCodError() {
        return codError;
    }

    public void setCodError(Integer codError) {
        this.codError = codError;
    }

    public String getMsjError() {
        return msjError;
    }

    public void setMsjError(String msjError) {
        this.msjError = msjError;
    }

    public String getDato01() {
        return dato01;
    }

    public void setDato01(String dato01) {
        this.dato01 = dato01;
    }

    public String getDato02() {
        return dato02;
    }

    public void setDato02(String dato02) {
        this.dato02 = dato02;
    }

    public String getDato03() {
        return dato03;
    }

    public void setDato03(String dato03) {
        this.dato03 = dato03;
    }

	@Override
	public String toString() {
		return "Message [codError=" + codError + ", msjError=" + msjError + "]";
	}
}
