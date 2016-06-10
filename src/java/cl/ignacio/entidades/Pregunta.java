/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.ignacio.entidades;

/**
 *
 * @author ignacio
 */
public class Pregunta {
	private String pregunta;
	private String[] respuestas;
	private int correcta;

	public Pregunta(String pregunta, String[] respuestas, int correcta) {
		this.pregunta = pregunta;
		this.respuestas = respuestas;
		this.correcta = correcta;
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


	
}
