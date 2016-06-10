/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.igncaio.beans;

import cl.ignacio.entidades.Pregunta;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author ignacio
 */
//@Named(value = "preguntaBean")
@ManagedBean(name="preguntaBean")
@ApplicationScoped
public class PreguntaBean implements Serializable {

	private String pregunta="";
	private String[] respuestas = new String[4];
	private int correcta=-1;
	private static List<Pregunta> listaPreguntas = new ArrayList<>();

	/**
	 * Creates a new instance of PreguntaBean
	 */
	public PreguntaBean() {
		String[] s = {"correcta", "mala", "mala", "mala"};
		listaPreguntas.add(new Pregunta("pregunta 1", s, 0));
		listaPreguntas.add(new Pregunta("pregunta 2", s.clone(), 0));
		listaPreguntas.add(new Pregunta("pregunta 3", s.clone(), 0));
		listaPreguntas.add(new Pregunta("pregunta 4", s.clone(), 0));
		listaPreguntas.add(new Pregunta("pregunta 5", s.clone(), 0));
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public String[] getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(String[] respuestas) {
		this.respuestas = respuestas;
	}

	public int getCorrecta() {
		return correcta;
	}

	public void setCorrecta(int correcta) {
		this.correcta = correcta;
	}

	public List<Pregunta> getListaPreguntas() {
		return listaPreguntas;
	}

	public void setListaPreguntas(List<Pregunta> listaPreguntas) {
		PreguntaBean.listaPreguntas = listaPreguntas;
	}

	public void agregarPregunta(){
		Pregunta p = new Pregunta(pregunta, respuestas, correcta);
		listaPreguntas.add(p);
		pregunta = "";
		respuestas = new String[4];
	}


	
}
