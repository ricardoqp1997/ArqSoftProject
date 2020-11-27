package controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import model.DAO.ConsultaDAO;
import model.DAO.Util;
import model.DTO.Aspirante;
import model.DTO.Consulta;

public class ControladorConsulta {
	private static ConsultaDAO DAOConsulta;
	private static final String CODIGOERROR = "CCON";
	private static final String DESCRIPCIONERROR = " Error en controlador de departamento ";
	private static final String MENSAJEEXITOSO = "Transaccion Exitosa";
	private static String codError = CODIGOERROR;
	private static String descError = DESCRIPCIONERROR;
	private static String mensajeError = "";

	public ControladorConsulta() {
		DAOConsulta = new ConsultaDAO();
	}

	public String crearConsulta(Aspirante aspirante, Timestamp fechaConsulta, String ipConsulta, String navegador) {
		try {

			Consulta consulta = new Consulta();
			consulta.setIdConsulta(String.valueOf(UUID.randomUUID()));
			consulta.setAspirante(aspirante);
			consulta.setFechaConsulta(fechaConsulta);
			consulta.setIpConsulta(ipConsulta);
			consulta.setNavegador(navegador);
			codError = DAOConsulta.Create(consulta);
			descError = (codError == "0000" ? MENSAJEEXITOSO : DESCRIPCIONERROR);
		} catch (Exception e) {
			codError = "03";
			mensajeError += " " + e.getMessage();
		}
		Util.CreateLog(codError, descError, mensajeError);
		return codError;
	}

	public Consulta consultarConsulta(String idConsulta) {

		try {
			Consulta d = DAOConsulta.buscarConsultaId(idConsulta);
			codError = "0000";
			descError = MENSAJEEXITOSO + "en Consulta de consulta";
			mensajeError = "Consulta exitosa " + d.getIdConsulta();
			return d;
		} catch (Exception e) {
			mensajeError += " " + e.getMessage();
			codError += "103";
			return null;
		} finally {
			Util.CreateLog(codError, descError, mensajeError);
		}
	}

	public List<Consulta> buscarTodosConsultas() {

		try {
			List<Consulta> d = DAOConsulta.buscarTodosConsultas();
			codError = "0000";
			descError = MENSAJEEXITOSO;
			mensajeError = "Consulta exitosa " + d.size() + " Consultas registrados";
			return d;
		} catch (Exception e) {
			mensajeError += " " + e.getMessage();
			codError += "103";
			return null;
		} finally {
			Util.CreateLog(codError, descError, mensajeError);
		}
	}
}
