package controller;

import java.util.List;

import model.DAO.CiudadDAO;
import model.DTO.TemperaturaHumedadModel;
import model.DTO.CiudadModel;
import model.DTO.DepartamentoModel;

public class ControladorCiudad {
	private static CiudadDAO DAOCiudad;
	// private static final String CODIGOERROR = "CC";
	// private static final String DESCRIPCIONERROR = " Error en controlador de ciudad ";
	// private static final String MENSAJEEXITOSO = "Transaccion Exitosa";
	// private static final String tabla = "Ciudad";
	// private static final String llavetabla = "ciudadId";
	// private static String codError = CODIGOERROR;
	// private static String descError = DESCRIPCIONERROR;
	// private static String mensajeError = "";

	public ControladorCiudad() {
		DAOCiudad = new CiudadDAO();
	}
	
	public List<CiudadModel> buscarCiudades() {
		try {
			List<CiudadModel> ciudades = DAOCiudad.buscarTodosCiudads();
			// codError = "0000";
			// descError = MENSAJEEXITOSO;
			// mensajeError = "Consulta exitosa " + ciudades.size() + " Ciudades";
			return ciudades;
		} catch (Exception e) {
			// mensajeError += " " + e.getMessage();
			// codError += "103";
			return null;
		} finally {
			// Util.CreateLog(CODIGOERROR + codError, descError, mensajeError);
		}
	}

	public CiudadModel consultarCiudades(Integer id) {

		try {
			CiudadModel ciudad = DAOCiudad.buscarCiudadId(id);
			// codError = "0000";
			// descError = MENSAJEEXITOSO + "en Consulta de aspirante";
			// mensajeError = "Consulta exitosa " + ciudad.getNombreCiudad();
			
			return ciudad;
		} catch (Exception e) {
			// mensajeError += " " + e.getMessage();
			// codError += "103";
			
			return null;
		} finally {
			// Util.CreateLog(CODIGOERROR + codError, descError, mensajeError);
		}
	}

	public List<TemperaturaHumedadModel> consultarTemperaturaHumedadCiudad(Integer id) {
		try {
			List<TemperaturaHumedadModel> temperaturas = DAOCiudad.buscarCiudadId(id).getTemperaturaHumedads();
			// codError = "0000";
			// descError = MENSAJEEXITOSO + "en Consulta de temperaturas";
			// mensajeError = "Consulta exitosa " + temperaturas.size();
			
			return temperaturas;
		} catch (Exception e) {
			// mensajeError += " " + e.getMessage();
			// codError += "103";
			
			return null;
		} finally {
			// Util.CreateLog(codError, descError, mensajeError);
		}
	}
	
	public DepartamentoModel consultarDepartamentoCiudad(Integer id) {
		
		try {
			DepartamentoModel departamento = DAOCiudad.buscarCiudadId(id).getDepartamento();
			// codError = "0000";
			// descError = MENSAJEEXITOSO + "en Consulta del departamento";
			// mensajeError = "Consulta exitosa " + departamento.size();
			return departamento;
		} catch (Exception e) {
			// mensajeError += " " + e.getMessage();
			// codError += "103";
			
			return null;
		} finally {
			// Util.CreateLog(codError, descError, mensajeError);
		}
	}

}
