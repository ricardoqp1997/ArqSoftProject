package model.DTO;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "Pais" database table.
 * 
 */
@Entity
@Table(name="\"Pais\"")
@NamedQuery(name="PaisModel.findAll", query="SELECT p FROM PaisModel p")
public class PaisModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name="nombre_pais")
	private String nombrePais;

	//bi-directional many-to-one association to DepartamentoModel
	@OneToMany(mappedBy="pai")
	private List<DepartamentoModel> departamentos;

	public PaisModel() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombrePais() {
		return this.nombrePais;
	}

	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}

	public List<DepartamentoModel> getDepartamentos() {
		return this.departamentos;
	}

	public void setDepartamentos(List<DepartamentoModel> departamentos) {
		this.departamentos = departamentos;
	}

	public DepartamentoModel addDepartamento(DepartamentoModel departamento) {
		getDepartamentos().add(departamento);
		departamento.setPai(this);

		return departamento;
	}

	public DepartamentoModel removeDepartamento(DepartamentoModel departamento) {
		getDepartamentos().remove(departamento);
		departamento.setPai(null);

		return departamento;
	}

}