package model.DTO;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the "TemperaturaHumedad" database table.
 * 
 */
@Entity
@Table(name="\"TemperaturaHumedad\"")
@NamedQuery(name="TemperaturaHumedadModel.findAll", query="SELECT t FROM TemperaturaHumedadModel t")
public class TemperaturaHumedadModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private double humedad;
	
	private double temperatura;
	
	private Timestamp timestamp;
	
	//bi-directional many-to-one association to CiudadModel
	@ManyToOne
	private CiudadModel ciudad;
	
	public TemperaturaHumedadModel() {
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public double getHumedad() {
		return this.humedad;
	}
	
	public void setHumedad(double humedad) {
		this.humedad = humedad;
	}
	
	public double getTemperatura() {
		return this.temperatura;
	}
	
	public void setTemperatura(double temperatura) {
		this.temperatura = temperatura;
	}
	
	public Timestamp getTimestamp() {
		return this.timestamp;
	}
	
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	public CiudadModel getCiudad() {
		return this.ciudad;
	}
	
	public void setCiudad(CiudadModel ciudad) {
		this.ciudad = ciudad;
	}
	
}