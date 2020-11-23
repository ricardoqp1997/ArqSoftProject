package model.DTO;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the aspirante database table.
 * 
 */
@Entity
@NamedQuery(name="Aspirante.findAll", query="SELECT a FROM Aspirante a")
public class Aspirante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cedula_aspirante")
	private Integer cedulaAspirante;

	private Boolean admitido;

	@Column(name="apellido_aspirante")
	private String apellidoAspirante;

	@Column(name="edad_aspirante")
	private Integer edadAspirante;

	@Column(name="examen_filename")
	private String examenFilename;

	@Column(name="nombre_aspirante")
	private String nombreAspirante;

	@Column(name="qrcode_aspirante")
	private Integer qrcodeAspirante;

	@Column(name="sexo_aspirante")
	private String sexoAspirante;

	//bi-directional many-to-one association to Ciudad
	@ManyToOne
	@JoinColumn(name="ciudad_id")
	private Ciudad ciudad;

	//bi-directional many-to-one association to Programa
	@ManyToOne
	@JoinColumn(name="programa_id")
	private Programa programa;

	//bi-directional many-to-one association to SedeUniversidad
	@ManyToOne
	@JoinColumn(name="sede_id")
	private SedeUniversidad sedeUniversidad;

	//bi-directional many-to-one association to Consulta
	@OneToMany(mappedBy="aspirante")
	private List<Consulta> consultas;

	public Aspirante() {
	}

	public Integer getCedulaAspirante() {
		return this.cedulaAspirante;
	}

	public void setCedulaAspirante(Integer cedulaAspirante) {
		this.cedulaAspirante = cedulaAspirante;
	}

	public Boolean getAdmitido() {
		return this.admitido;
	}

	public void setAdmitido(Boolean admitido) {
		this.admitido = admitido;
	}

	public String getApellidoAspirante() {
		return this.apellidoAspirante;
	}

	public void setApellidoAspirante(String apellidoAspirante) {
		this.apellidoAspirante = apellidoAspirante;
	}

	public Integer getEdadAspirante() {
		return this.edadAspirante;
	}

	public void setEdadAspirante(Integer edadAspirante) {
		this.edadAspirante = edadAspirante;
	}

	public String getExamenFilename() {
		return this.examenFilename;
	}

	public void setExamenFilename(String examenFilename) {
		this.examenFilename = examenFilename;
	}

	public String getNombreAspirante() {
		return this.nombreAspirante;
	}

	public void setNombreAspirante(String nombreAspirante) {
		this.nombreAspirante = nombreAspirante;
	}

	public Integer getQrcodeAspirante() {
		return this.qrcodeAspirante;
	}

	public void setQrcodeAspirante(Integer qrcodeAspirante) {
		this.qrcodeAspirante = qrcodeAspirante;
	}

	public String getSexoAspirante() {
		return this.sexoAspirante;
	}

	public void setSexoAspirante(String sexoAspirante) {
		this.sexoAspirante = sexoAspirante;
	}

	public Ciudad getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public Programa getPrograma() {
		return this.programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public SedeUniversidad getSedeUniversidad() {
		return this.sedeUniversidad;
	}

	public void setSedeUniversidad(SedeUniversidad sedeUniversidad) {
		this.sedeUniversidad = sedeUniversidad;
	}

	public List<Consulta> getConsultas() {
		return this.consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}

	public Consulta addConsulta(Consulta consulta) {
		getConsultas().add(consulta);
		consulta.setAspirante(this);

		return consulta;
	}

	public Consulta removeConsulta(Consulta consulta) {
		getConsultas().remove(consulta);
		consulta.setAspirante(null);

		return consulta;
	}

}