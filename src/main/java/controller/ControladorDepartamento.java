package controller;

import java.util.List;

import model.DAO.DepartamentoDAO;
import model.DAO.Util;
import model.DTO.Ciudad;
import model.DTO.Departamento;

public class ControladorDepartamento {
	private static DepartamentoDAO DAODepartamento;
	private static final String CODIGOERROR = "DE0";
	private static final String DESCRIPCIONERROR = " Error en controlador de departamento ";
	private static final String tabla = "Departamento";
	private static final String llavetabla = "departamentoId";
	private static final String MENSAJEEXITOSO = "Transaccion Exitosa";
	String codError = CODIGOERROR;
	String descError = DESCRIPCIONERROR;
	String mensajeError = "";

	public ControladorDepartamento() {
		DAODepartamento = new DepartamentoDAO();
	}

	public String crearDepartamento(String nombreDepartamento) {

		try {
			Departamento departamento = new Departamento();
			departamento.setDepartamentoId(Util.getMaxIDd(tabla, llavetabla));
			departamento.setNombreDepartamento(nombreDepartamento.toUpperCase());
			DAODepartamento.crearDepartamento(departamento);
			codError = "00";
			mensajeError = "Se ha creado correctamente el departamento " + nombreDepartamento;
			descError = MENSAJEEXITOSO;
		} catch (NullPointerException e) {
			codError += "01";
			mensajeError += " " + e.getMessage();
		} catch (Exception e) {
			mensajeError += " " + e.getMessage();
			codError += "02";
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
		return DAODepartamento.buscarTodosDepartamentos();
	}

	public List<Ciudad> consultarCiudadesDepartamento(String idDepartamento) {
		Departamento departamento = DAODepartamento.buscarDepartamentoId(idDepartamento);
		return departamento.getCiudads();

	}

	public String EliminarDepartamento(int id) {
		try {
			DAODepartamento.eliminarDepartamento(id);
			codError = "00";
		} catch (Exception e) {
			codError = "04";
			mensajeError = e.getMessage();
		}
		Util.CreateLog(codError, descError, mensajeError);
		return codError;
	}
}
