package model.DTO;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "Departamentos" database table.
 * 
 */
@Entity
@Table(name="\"Departamentos\"")
@NamedQuery(name="DepartamentoModel.findAll", query="SELECT d FROM DepartamentoModel d")
public class DepartamentoModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name="nombre_departamento")
	private String nombreDepartamento;

	//bi-directional many-to-one association to CiudadModel
	@OneToMany(mappedBy="departamento")
	private List<CiudadModel> ciudads;

	//bi-directional many-to-one association to PaisModel
	@ManyToOne
	@JoinColumn(name="pais_id")
	private PaisModel pai;

	public DepartamentoModel() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreDepartamento() {
		return this.nombreDepartamento;
	}

	public void setNombreDepartamento(String nombreDepartamento) {
		this.nombreDepartamento = nombreDepartamento;
	}

	public List<CiudadModel> getCiudads() {
		return this.ciudads;
	}

	public void setCiudads(List<CiudadModel> ciudads) {
		this.ciudads = ciudads;
	}

	public CiudadModel addCiudad(CiudadModel ciudad) {
		getCiudads().add(ciudad);
		ciudad.setDepartamento(this);

		return ciudad;
	}

	public CiudadModel removeCiudad(CiudadModel ciudad) {
		getCiudads().remove(ciudad);
		ciudad.setDepartamento(null);

		return ciudad;
	}

	public PaisModel getPai() {
		return this.pai;
	}

	public void setPai(PaisModel pai) {
		this.pai = pai;
	}

}