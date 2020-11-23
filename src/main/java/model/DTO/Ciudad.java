package model.DTO;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ciudad database table.
 * 
 */
@Entity
@NamedQuery(name="Ciudad.findAll", query="SELECT c FROM Ciudad c")
public class Ciudad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ciudad_id")
	private Integer ciudadId;

	@Column(name="nombre_ciudad")
	private String nombreCiudad;

	//bi-directional many-to-one association to Aspirante
	@OneToMany(mappedBy="ciudad")
	private List<Aspirante> aspirantes;

	//bi-directional many-to-one association to Departamento
	@ManyToOne
	@JoinColumn(name="departamento_id")
	private Departamento departamento;

	//bi-directional many-to-one association to SedeUniversidad
	@OneToMany(mappedBy="ciudad")
	private List<SedeUniversidad> sedeUniversidads;

	public Ciudad() {
	}

	public Integer getCiudadId() {
		return this.ciudadId;
	}

	public void setCiudadId(Integer ciudadId) {
		this.ciudadId = ciudadId;
	}

	public String getNombreCiudad() {
		return this.nombreCiudad;
	}

	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}

	public List<Aspirante> getAspirantes() {
		return this.aspirantes;
	}

	public void setAspirantes(List<Aspirante> aspirantes) {
		this.aspirantes = aspirantes;
	}

	public Aspirante addAspirante(Aspirante aspirante) {
		getAspirantes().add(aspirante);
		aspirante.setCiudad(this);

		return aspirante;
	}

	public Aspirante removeAspirante(Aspirante aspirante) {
		getAspirantes().remove(aspirante);
		aspirante.setCiudad(null);

		return aspirante;
	}

	public Departamento getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public List<SedeUniversidad> getSedeUniversidads() {
		return this.sedeUniversidads;
	}

	public void setSedeUniversidads(List<SedeUniversidad> sedeUniversidads) {
		this.sedeUniversidads = sedeUniversidads;
	}

	public SedeUniversidad addSedeUniversidad(SedeUniversidad sedeUniversidad) {
		getSedeUniversidads().add(sedeUniversidad);
		sedeUniversidad.setCiudad(this);

		return sedeUniversidad;
	}

	public SedeUniversidad removeSedeUniversidad(SedeUniversidad sedeUniversidad) {
		getSedeUniversidads().remove(sedeUniversidad);
		sedeUniversidad.setCiudad(null);

		return sedeUniversidad;
	}

}