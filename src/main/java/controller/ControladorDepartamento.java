package controller;

import java.util.List;

import model.DAO.DepartamentoDAO;
import model.DTO.CiudadModel;
import model.DTO.DepartamentoModel;

public class ControladorDepartamento {
	private static DepartamentoDAO DAODepartamento;
	// private static final String CODIGOERROR = "CD";
	// private static final String DESCRIPCIONERROR = " Error en controlador de departamento ";
	// private static final String tabla = "Departamento";
	// private static final String llavetabla = "departamentoId";
	// private static final String MENSAJEEXITOSO = "Transaccion Exitosa";
	// private static String codError = CODIGOERROR;
	// private static String descError = DESCRIPCIONERROR;
	// private static String mensajeError = "";

	public ControladorDepartamento() {
		DAODepartamento = new DepartamentoDAO();
	}

	public DepartamentoModel seleccionarDepartamentoId(int id) {
		try {
			DepartamentoModel d = DAODepartamento.buscarDepartamentoId(id);
			// codError = "0000";
			// descError = MENSAJEEXITOSO + "en Consulta Departamento";
			// mensajeError = "Consulta exitosa " + d.getNombreDepartamento();

			return d;
		} catch (Exception e) {
			// mensajeError += " " + e.getMessage();
			// codError += "103";
			
			return null;
		} finally {
			// Util.CreateLog(codError, descError, mensajeError);
		}

	}

	public DepartamentoModel seleccionarDepartamentoNombre(String nombre) {

		try {
			DepartamentoModel d = DAODepartamento.buscarDepartamentoId(nombre);
			// codError = "0000";
			// descError = MENSAJEEXITOSO + "en Consulta Departamento";
			// mensajeError = "Consulta exitosa " + d.getNombreDepartamento();
			
			return d;
		} catch (Exception e) {
			// mensajeError += " " + e.getMessage();
			// codError += "103";
			
			return null;
		} finally {
			// Util.CreateLog(codError, descError, mensajeError);
		}

	}

	public List<DepartamentoModel> seleccionarDepartamentos() {

		try {
			List<DepartamentoModel> d = DAODepartamento.buscarTodosDepartamentos();
			// codError = "0000";
			// descError = MENSAJEEXITOSO + "en Consulta Departamentos";
			// mensajeError = "Consulta exitosa " + d.size() + " departamentos";
			
			return d;
		} catch (Exception e) {
			// mensajeError += " " + e.getMessage();
			// codError += "103";
			
			return null;
		} finally {
			// Util.CreateLog(codError, descError, mensajeError);
		}

	}

	public List<CiudadModel> consultarCiudadesDepartamento(Integer idDepartamento) {

		try {
			List<CiudadModel> d = DAODepartamento.buscarDepartamentoId(idDepartamento).getCiudads();
			// codError = "0000";
			// descError = MENSAJEEXITOSO;
			// mensajeError = "Consulta de ciudades del departamento" + DAODepartamento.buscarDepartamentoId(idDepartamento).getNombreDepartamento();
			
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
