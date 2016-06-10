/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.igncaio.beans;

import cl.ignacio.entidades.Pregunta;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author ignacio
 */
//@Named(value = "randomQuestionBean")
@ManagedBean(name = "randomQuestionBean")
@ViewScoped
public class RandomQuestionBean {

	List<Pregunta> listaPreguntas;

	int indexRandom = 0;

	double avance = 0.0;

	int correcta = 0;

	int respuestasCorrectas = 0;

	/**
	 * Creates a new instance of RandomQuestionBean
	 */
	public RandomQuestionBean() {
	}

	@ManagedProperty(value = "#{preguntaBean}")
	PreguntaBean p;

	@PostConstruct
	public void init() {
		listaPreguntas = new ArrayList<>();
		List<Pregunta> preguntas = p.getListaPreguntas();
		ArrayList<Integer> salidosLista = new ArrayList<>(preguntas.size());
		//Por cada pregunta registrada
		for (Pregunta pregunta : preguntas) {
			boolean continuar = true;
			while (continuar) {
				//Genero un numero random
				int numero = new Random().nextInt(preguntas.size());
				// Reviso que antes no haya salido, de lo contrario genero otro
				if (!salidosLista.contains(numero)) {
					//Obtengo la pregunta random y la clono, para no modificar la original
					Pregunta pp;
					try {
						pp = (Pregunta) preguntas.get(numero).clone();
					} catch (CloneNotSupportedException ex) {
						Logger.getLogger(RandomQuestionBean.class.getName()).log(Level.SEVERE, null, ex);
						break;
					}
					// La guardo en mi nueva lista
					listaPreguntas.add(pp);
					// El numero se guarda en una lista, para que no se vuelva repetir
					salidosLista.add(numero);
					// Respuestas ordenadas de forma random
					//respuestas
					String[] respuestas = pp.getRespuestas();
					//Creo un nuevo array que contendra las preguntas con orden random
					String[] tempRespuesta = new String[4];
					//Guardo numeros para que no se repitan
					ArrayList<Integer> respuestasRandom = new ArrayList(4);
					// Populo respuestaRandom, para que quede con numeros ordenamdos random
					for (int i = 0; i < 4; i++) {
						boolean continuar2 = true;
						while (continuar2) {
							int rr = new Random().nextInt(4);
							if (!respuestasRandom.contains(rr)) {
								respuestasRandom.add(rr);
								continuar2 = false;
							}
						}
					}
					// Con todos los numeros random reordeno las listas
					for (int i = 0; i < respuestas.length; i++) {
						int indice = respuestasRandom.get(i);
						// Dado que siempre la primera repuesta es la correcta setea la nueva ubicacion de la respuesta correcta
						if (indice == 0) {
							pp.setCorrecta(i);
						}
						tempRespuesta[i] = respuestas[indice];
					}
					// Guarda los cambios
					pp.setRespuestas(tempRespuesta);
					continuar = false;
				}
			}
		}

	}

	public List<Pregunta> getListaPreguntas() {
		return listaPreguntas;
	}

	public void setListaPreguntas(List<Pregunta> listaPreguntas) {
		this.listaPreguntas = listaPreguntas;
	}

	public PreguntaBean getP() {
		return p;
	}

	public void setP(PreguntaBean p) {
		this.p = p;
	}

	public double getAvance() {
		return avance;
	}

	public void setAvance(double avance) {
		this.avance = avance;
	}

	public String getPreguntaRandomPregunta() {
		return listaPreguntas.get(indexRandom).getPregunta();
	}

	public boolean getCorrecta(int index) {
		return correcta == index;
	}

	public String[] getPreguntaRandomRespuestas() {
		return listaPreguntas.get(indexRandom).getRespuestas();
	}

	public void setRespuesta(String respuesta) {
		respuestasCorrectas += Integer.valueOf(respuesta) == listaPreguntas.get(indexRandom).getCorrecta() ? 1 : 0;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public int getRespuestasCorrectas() {
		return respuestasCorrectas;
	}

	public void setRespuestasCorrectas(int respuestasCorrectas) {
		this.respuestasCorrectas = respuestasCorrectas;
	}

	String respuesta = "";

	public void newPregunta() {
		indexRandom++;
		avance += 100.0 / listaPreguntas.size();
		if (avance >= 100) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
			} catch (IOException ex) {
				Logger.getLogger(RandomQuestionBean.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

}
