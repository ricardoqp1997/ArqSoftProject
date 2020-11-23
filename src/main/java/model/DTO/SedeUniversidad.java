package model.DTO;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the sede_universidad database table.
 * 
 */
@Entity
@Table(name="sede_universidad")
@NamedQuery(name="SedeUniversidad.findAll", query="SELECT s FROM SedeUniversidad s")
public class SedeUniversidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="sede_id")
	private Integer sedeId;

	@Column(name="direccion_sede")
	private String direccionSede;

	@Column(name="nombre_sede")
	private String nombreSede;

	//bi-directional many-to-one association to Aspirante
	@OneToMany(mappedBy="sedeUniversidad")
	private List<Aspirante> aspirantes;

	//bi-directional many-to-one association to Ciudad
	@ManyToOne
	@JoinColumn(name="ciudad_id")
	private Ciudad ciudad;

	public SedeUniversidad() {
	}

	public Integer getSedeId() {
		return this.sedeId;
	}

	public void setSedeId(Integer sedeId) {
		this.sedeId = sedeId;
	}

	public String getDireccionSede() {
		return this.direccionSede;
	}

	public void setDireccionSede(String direccionSede) {
		this.direccionSede = direccionSede;
	}

	public String getNombreSede() {
		return this.nombreSede;
	}

	public void setNombreSede(String nombreSede) {
		this.nombreSede = nombreSede;
	}

	public List<Aspirante> getAspirantes() {
		return this.aspirantes;
	}

	public void setAspirantes(List<Aspirante> aspirantes) {
		this.aspirantes = aspirantes;
	}

	public Aspirante addAspirante(Aspirante aspirante) {
		getAspirantes().add(aspirante);
		aspirante.setSedeUniversidad(this);

		return aspirante;
	}

	public Aspirante removeAspirante(Aspirante aspirante) {
		getAspirantes().remove(aspirante);
		aspirante.setSedeUniversidad(null);

		return aspirante;
	}

	public Ciudad getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

}