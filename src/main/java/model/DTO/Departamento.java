package model.DTO;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the departamento database table.
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = "Departamento.findId", query = "SELECT MAX(d.departamentoId) FROM Departamento d"),
		@NamedQuery(name = "Departamento.findAll", query = "SELECT d FROM Departamento d ORDER BY d.nombreDepartamento DESC") })
public class Departamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "departamento_id")
	private Integer departamentoId;

	@Column(name = "nombre_departamento")
	private String nombreDepartamento;

	// bi-directional many-to-one association to Ciudad
	@OneToMany(mappedBy = "departamento")
	private List<Ciudad> ciudads;

	public Departamento() {
	}

	public Integer getDepartamentoId() {
		return this.departamentoId;
	}

	public void setDepartamentoId(Integer departamentoId) {
		this.departamentoId = departamentoId;
	}

	public String getNombreDepartamento() {
		return this.nombreDepartamento;
	}

	public void setNombreDepartamento(String nombreDepartamento) {
		this.nombreDepartamento = nombreDepartamento;
	}

	public List<Ciudad> getCiudads() {
		return this.ciudads;
	}

	public void setCiudads(List<Ciudad> ciudads) {
		this.ciudads = ciudads;
	}

	public Ciudad addCiudad(Ciudad ciudad) {
		getCiudads().add(ciudad);
		ciudad.setDepartamento(this);

		return ciudad;
	}

	public Ciudad removeCiudad(Ciudad ciudad) {
		getCiudads().remove(ciudad);
		ciudad.setDepartamento(null);

		return ciudad;
	}

}