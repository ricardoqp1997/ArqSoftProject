package model.DTO;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the programa database table.
 * 
 */
@Entity
@NamedQuery(name="Programa.findAll", query="SELECT p FROM Programa p")
public class Programa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="programa_id")
	private Integer programaId;

	@Column(name="nombre_programa")
	private String nombrePrograma;

	//bi-directional many-to-one association to Aspirante
	@OneToMany(mappedBy="programa")
	private List<Aspirante> aspirantes;

	public Programa() {
	}

	public Integer getProgramaId() {
		return this.programaId;
	}

	public void setProgramaId(Integer programaId) {
		this.programaId = programaId;
	}

	public String getNombrePrograma() {
		return this.nombrePrograma;
	}

	public void setNombrePrograma(String nombrePrograma) {
		this.nombrePrograma = nombrePrograma;
	}

	public List<Aspirante> getAspirantes() {
		return this.aspirantes;
	}

	public void setAspirantes(List<Aspirante> aspirantes) {
		this.aspirantes = aspirantes;
	}

	public Aspirante addAspirante(Aspirante aspirante) {
		getAspirantes().add(aspirante);
		aspirante.setPrograma(this);

		return aspirante;
	}

	public Aspirante removeAspirante(Aspirante aspirante) {
		getAspirantes().remove(aspirante);
		aspirante.setPrograma(null);

		return aspirante;
	}

}