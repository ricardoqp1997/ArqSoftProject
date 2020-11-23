package model.DTO;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the consulta database table.
 * 
 */
@Entity
@NamedQuery(name="Consulta.findAll", query="SELECT c FROM Consulta c")
public class Consulta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_consulta")
	private String idConsulta;

	@Column(name="fecha_consulta")
	private Timestamp fechaConsulta;

	@Column(name="ip_consulta")
	private String ipConsulta;

	private String navegador;

	//bi-directional many-to-one association to Aspirante
	@ManyToOne
	@JoinColumn(name="cedula_aspirante")
	private Aspirante aspirante;

	public Consulta() {
	}

	public String getIdConsulta() {
		return this.idConsulta;
	}

	public void setIdConsulta(String idConsulta) {
		this.idConsulta = idConsulta;
	}

	public Timestamp getFechaConsulta() {
		return this.fechaConsulta;
	}

	public void setFechaConsulta(Timestamp fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
	}

	public String getIpConsulta() {
		return this.ipConsulta;
	}

	public void setIpConsulta(String ipConsulta) {
		this.ipConsulta = ipConsulta;
	}

	public String getNavegador() {
		return this.navegador;
	}

	public void setNavegador(String navegador) {
		this.navegador = navegador;
	}

	public Aspirante getAspirante() {
		return this.aspirante;
	}

	public void setAspirante(Aspirante aspirante) {
		this.aspirante = aspirante;
	}

}