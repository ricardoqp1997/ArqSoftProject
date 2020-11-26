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
			descError = MENSAJEEXITOSO;
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
		return DAODepartamento.buscarDepartamentoId(id);
	}

	public Departamento seleccionarDepartamentoNombre(String nombre) {
		return DAODepartamento.buscarDepartamentoId(nombre);
	}

	public List<Departamento> seleccionarDepartamentos() {
		Util.CreateLog("0000", "Consulta de departamentos",
				MENSAJEEXITOSO + "\n" + DAODepartamento.buscarTodosDepartamentos());
		return DAODepartamento.buscarTodosDepartamentos();
	}

	public List<Ciudad> consultarCiudadesDepartamento(String idDepartamento) {
		Departamento departamento = DAODepartamento.buscarDepartamentoId(idDepartamento);
		Util.CreateLog("0000", "Consulta de ciudades exitosa", MENSAJEEXITOSO + "\n " + departamento.getCiudads());
		return departamento.getCiudads();

	}

	public String EliminarDepartamento(int id) {
		try {
			codError = DAODepartamento.eliminarDepartamento(id);

		} catch (Exception e) {
			codError = "103";
			mensajeError = e.getMessage();
		}
		Util.CreateLog(codError, descError, mensajeError);
		return codError;
	}
}
