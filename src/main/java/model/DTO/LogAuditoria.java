package model.DTO;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * The persistent class for the log_auditoria database table.
 * 
 */
@Entity
@Table(name = "log_auditoria")
@NamedQueries({ @NamedQuery(name = "LogAuditoria.findAll", query = "SELECT l FROM LogAuditoria l"),
		@NamedQuery(name = "LogAuditoria.maxId", query = "SELECT max(l.idLog) FROM LogAuditoria l") })
public class LogAuditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_log")
	private Integer idLog;

	@Column(name = "cod_error")
	private String codError;

	@Column(name = "hora_error")
	private Timestamp horaError;

	@Column(name = "mensaje_error")
	private String mensajeError;

	public LogAuditoria() {
	}

	public Integer getIdLog() {
		return this.idLog;
	}

	public void setIdLog(Integer idLog) {
		this.idLog = idLog;
	}

	public String getCodError() {
		return this.codError;
	}

	public void setCodError(String codError) {
		this.codError = codError;
	}

	public Timestamp getHoraError() {
		return this.horaError;
	}

	public void setHoraError(Timestamp horaError) {
		this.horaError = horaError;
	}

	public String getMensajeError() {
		return this.mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

}