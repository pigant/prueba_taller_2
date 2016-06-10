/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.igncaio.beans;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author ignacio
 */
@Named(value = "resultadoBean")
@RequestScoped
public class ResultadoBean {

	private int buenas;
	private int malas;
	/**
	 * Creates a new instance of ResultadoBean
	 */
	public ResultadoBean() {
		String b = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("buenas");
		String m = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("malas");
		buenas = Integer.valueOf(b);
		malas = Integer.valueOf(m);
	}

	public int getBuenas() {
		return buenas;
	}

	public void setBuenas(int buenas) {
		this.buenas = buenas;
	}

	public int getMalas() {
		return malas;
	}

	public void setMalas(int malas) {
		this.malas = malas;
	}

}
