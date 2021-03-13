package model.DTO;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "Ciudad" database table.
 * 
 */
@Entity
@Table(name="\"Ciudad\"")
@NamedQuery(name="CiudadModel.findAll", query="SELECT c FROM CiudadModel c")
public class CiudadModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name="nombre_ciudad")
	private String nombreCiudad;

	//bi-directional many-to-one association to DepartamentoModel
	@ManyToOne
	private DepartamentoModel departamento;

	//bi-directional many-to-one association to TemperaturaHumedadModel
	@OneToMany(mappedBy="ciudad")
	private List<TemperaturaHumedadModel> temperaturaHumedads;

	public CiudadModel() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreCiudad() {
		return this.nombreCiudad;
	}

	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}

	public DepartamentoModel getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(DepartamentoModel departamento) {
		this.departamento = departamento;
	}

	public List<TemperaturaHumedadModel> getTemperaturaHumedads() {
		return this.temperaturaHumedads;
	}

	public void setTemperaturaHumedads(List<TemperaturaHumedadModel> temperaturaHumedads) {
		this.temperaturaHumedads = temperaturaHumedads;
	}

	public TemperaturaHumedadModel addTemperaturaHumedad(TemperaturaHumedadModel temperaturaHumedad) {
		getTemperaturaHumedads().add(temperaturaHumedad);
		temperaturaHumedad.setCiudad(this);

		return temperaturaHumedad;
	}

	public TemperaturaHumedadModel removeTemperaturaHumedad(TemperaturaHumedadModel temperaturaHumedad) {
		getTemperaturaHumedads().remove(temperaturaHumedad);
		temperaturaHumedad.setCiudad(null);

		return temperaturaHumedad;
	}

}