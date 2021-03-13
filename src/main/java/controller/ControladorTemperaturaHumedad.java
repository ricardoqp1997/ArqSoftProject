package controller;

import java.util.List;

import model.DAO.TemperaturaHumedadDAO;
// import model.DAO.CiudadDAO;
import model.DTO.TemperaturaHumedadModel;

public class ControladorTemperaturaHumedad {
	private static TemperaturaHumedadDAO DAOtemperatura;

	// private static final String CODIGOERROR = "CA";
	// private static final String DESCRIPCIONERROR = " Error en controlador de aspirante ";
	// private static final String MENSAJEEXITOSO = "Transaccion Exitosa";
	// private static String codError = CODIGOERROR;
	// private static String descError = DESCRIPCIONERROR;
	// private static String mensajeError = "";

	private List<TemperaturaHumedadModel> temperaturas;

	public ControladorTemperaturaHumedad() {
		DAOtemperatura = new TemperaturaHumedadDAO();
	}

	public TemperaturaHumedadModel consultarTemperatura(Integer id) {

		try {
			TemperaturaHumedadModel d = DAOtemperatura.buscarTemperaturaId(id);
			// codError = "0000";
			// descError = MENSAJEEXITOSO + "en Consulta de aspirante";
			// mensajeError = "Consulta exitosa " + d.getTemperatura();
			return d;
		} catch (Exception e) {
			// mensajeError += " " + e.getMessage();
			// ScodError += "103";
			return null;
		} finally {
			// Util.CreateLog(CODIGOERROR + codError, descError, mensajeError);
		}
	}
	
	public TemperaturaHumedadModel consultarHumedad(Integer id) {

		try {
			TemperaturaHumedadModel d = DAOtemperatura.buscarTemperaturaId(id);
			// codError = "0000";
			// descError = MENSAJEEXITOSO + "en Consulta de aspirante";
			// mensajeError = "Consulta exitosa " + d.getHumedad();
			return d;
		} catch (Exception e) {
			// mensajeError += " " + e.getMessage();
			// codError += "103";
			return null;
		} finally {
			// Util.CreateLog(CODIGOERROR + codError, descError, mensajeError);
		}
	}

	public List<TemperaturaHumedadModel> buscarTodasTemperaturas() {

		try {
			List<TemperaturaHumedadModel> d = DAOtemperatura.buscarTodasTemperaturas();
			// codError = "0000";
			// descError = MENSAJEEXITOSO;
			// mensajeError = "Consulta exitosa " + d.size() + " Aspirantes registrados";
			return d;
		} catch (Exception e) {
			// mensajeError += " " + e.getMessage();
			// codError += "103";
			return null;
		} finally {
			// Util.CreateLog(codError, descError, mensajeError);
		}
	}
	
	public TemperaturaHumedadModel consultarPorCiudad(String ciudad){
		
		try {
			TemperaturaHumedadModel d = DAOtemperatura.buscarTemperaturaId(ciudad);
			// codError = "0000";
			// descError = MENSAJEEXITOSO;
			// mensajeError = "Consulta exitosa " + d.size() + " Aspirantes registrados";
			return d;
		} catch (Exception e) {
			// mensajeError += " " + e.getMessage();
			// codError += "103";
			return null;
		} finally {
			// Util.CreateLog(codError, descError, mensajeError);
		}
		
	}
}
