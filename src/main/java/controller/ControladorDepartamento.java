package controller;

import java.util.List;

import model.DAO.DepartamentoDAO;
import model.DAO.Util;
import model.DTO.Ciudad;
import model.DTO.Departamento;

public class ControladorDepartamento {
	private static DepartamentoDAO DAODepartamento;
	private static final String CODIGOERROR = "CD";
	private static final String DESCRIPCIONERROR = " Error en controlador de departamento ";
	private static final String tabla = "Departamento";
	private static final String llavetabla = "departamentoId";
	private static final String MENSAJEEXITOSO = "Transaccion Exitosa";
	private static String codError = CODIGOERROR;
	private static String descError = DESCRIPCIONERROR;
	private static String mensajeError = "";

	public ControladorDepartamento() {
		DAODepartamento = new DepartamentoDAO();
	}

	public String crearDepartamento(String nombreDepartamento) {

		try {
			Departamento departamento = new Departamento();
			departamento.setDepartamentoId(Util.getMaxIDd(tabla, llavetabla));
			departamento.setNombreDepartamento(nombreDepartamento.toUpperCase());
			codError = DAODepartamento.crearDepartamento(departamento);

			mensajeError = "Se ha creado correctamente el departamento " + nombreDepartamento;
			descError = (codError == "0000" ? MENSAJEEXITOSO : DESCRIPCIONERROR);
		} catch (NullPointerException e) {
			codError += "004";
			mensajeError += " " + e.getMessage();
		} catch (Exception e) {
			mensajeError += " " + e.getMessage();
			codError += "103";
		}
		Util.CreateLog(codError, descError, mensajeError);
		return codError;
	}

	public Departamento seleccionarDepartamentoId(int id) {
		try {
			Departamento d = DAODepartamento.buscarDepartamentoId(id);
			codError = "0000";
			descError = MENSAJEEXITOSO + "en Consulta Departamento";
			mensajeError = "Consulta exitosa " + d.getNombreDepartamento();
			return d;
		} catch (Exception e) {
			mensajeError += " " + e.getMessage();
			codError += "103";
			return null;
		} finally {
			Util.CreateLog(codError, descError, mensajeError);
		}

	}

	public Departamento seleccionarDepartamentoNombre(String nombre) {

		try {
			Departamento d = DAODepartamento.buscarDepartamentoId(nombre);
			codError = "0000";
			descError = MENSAJEEXITOSO + "en Consulta Departamento";
			mensajeError = "Consulta exitosa " + d.getNombreDepartamento();
			return d;
		} catch (Exception e) {
			mensajeError += " " + e.getMessage();
			codError += "103";
			return null;
		} finally {
			Util.CreateLog(codError, descError, mensajeError);
		}

	}

	public List<Departamento> seleccionarDepartamentos() {

		try {
			List<Departamento> d = DAODepartamento.buscarTodosDepartamentos();
			codError = "0000";
			descError = MENSAJEEXITOSO + "en Consulta Departamentos";
			mensajeError = "Consulta exitosa " + d.size() + " departamentos";
			return d;
		} catch (Exception e) {
			mensajeError += " " + e.getMessage();
			codError += "103";
			return null;
		} finally {
			Util.CreateLog(codError, descError, mensajeError);
		}

	}

	public List<Ciudad> consultarCiudadesDepartamento(Integer idDepartamento) {

		try {
			List<Ciudad> d = DAODepartamento.buscarDepartamentoId(idDepartamento).getCiudads();
			codError = "0000";
			descError = MENSAJEEXITOSO;
			mensajeError = "Consulta de ciudades del departamento"
					+ DAODepartamento.buscarDepartamentoId(idDepartamento).getNombreDepartamento();
			return d;
		} catch (Exception e) {
			mensajeError += " " + e.getMessage();
			codError += "103";
			return null;
		} finally {
			Util.CreateLog(codError, descError, mensajeError);
		}

	}

	public String EliminarDepartamento(int id) {
		try {
			String departamento = DAODepartamento.buscarDepartamentoId(id).getNombreDepartamento();
			codError = DAODepartamento.eliminarDepartamento(id);
			descError = MENSAJEEXITOSO + "en eliminar departamento " + departamento;
			mensajeError = codError == "0000" ? "Consulta exitosa " + departamento
					: "Fallo al eliminar el departamento " + departamento;

		} catch (Exception e) {
			codError = "103";
			mensajeError = e.getMessage();
		}
		Util.CreateLog(codError, descError, mensajeError);
		return codError;
	}
}
