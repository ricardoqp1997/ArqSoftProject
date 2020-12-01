package controller;

import java.util.List;

import model.DAO.ProgramaDAO;
import model.DAO.Util;
import model.DTO.Aspirante;
import model.DTO.Programa;

public class ControladorPrograma {
	private static ProgramaDAO DAOPrograma;
	private static final String CODIGOERROR = "CP";
	private static final String DESCRIPCIONERROR = " Error en controlador de programa ";
	private static final String tabla = "Programa";
	private static final String llavetabla = "programaId";
	private static final String MENSAJEEXITOSO = "Transaccion Exitosa";
	private static String codError = CODIGOERROR;
	private static String descError = DESCRIPCIONERROR;
	private static String mensajeError = "";

	public ControladorPrograma() {
		DAOPrograma = new ProgramaDAO();
	}

	public String crearPrograma(String nombrePrograma) {

		try {
			Programa programa = new Programa();
			programa.setProgramaId(Util.getMaxIDd(tabla, llavetabla));
			programa.setNombrePrograma(nombrePrograma.toUpperCase());
			codError = DAOPrograma.Create(programa);

			mensajeError = "Se ha creado correctamente el programa " + nombrePrograma;
			descError = (codError == "0000" ? MENSAJEEXITOSO : DESCRIPCIONERROR);
		} catch (NullPointerException e) {
			codError += "004";
			mensajeError += " " + e.getMessage();
		} catch (Exception e) {
			mensajeError += " " + e.getMessage();
			codError += "103";
		}
		Util.CreateLog(CODIGOERROR + CODIGOERROR + codError, descError, mensajeError);
		return codError;
	}

	public Programa seleccionarProgramaId(int id) {
		try {
			Programa d = DAOPrograma.buscarProgramaId(id);
			codError = "0000";
			descError = MENSAJEEXITOSO + "en Consulta Programa";
			mensajeError = "Consulta exitosa " + d.getNombrePrograma();
			return d;
		} catch (Exception e) {
			mensajeError += " " + e.getMessage();
			codError += "103";
			return null;
		} finally {
			Util.CreateLog(CODIGOERROR + codError, descError, mensajeError);
		}

	}

	public Programa seleccionarProgramaNombre(String nombre) {

		try {
			Programa d = DAOPrograma.buscarProgramaId(nombre);
			codError = "0000";
			descError = MENSAJEEXITOSO + "en Consulta Programa";
			mensajeError = "Consulta exitosa " + d.getNombrePrograma();
			return d;
		} catch (Exception e) {
			mensajeError += " " + e.getMessage();
			codError += "103";
			return null;
		} finally {
			Util.CreateLog(CODIGOERROR + codError, descError, mensajeError);
		}

	}

	public List<Programa> seleccionarProgramas() {

		try {
			List<Programa> d = DAOPrograma.buscarTodosProgramas();
			codError = "0000";
			descError = MENSAJEEXITOSO + "en Consulta Programas";
			mensajeError = "Consulta exitosa " + d.size() + " programas";
			return d;
		} catch (Exception e) {
			mensajeError += " " + e.getMessage();
			codError += "103";
			return null;
		} finally {
			Util.CreateLog(CODIGOERROR + codError, descError, mensajeError);
		}

	}

	public List<Aspirante> consultarAspirantesPrograma(String idPrograma) {

		try {
			List<Aspirante> d = DAOPrograma.buscarProgramaId(idPrograma).getAspirantes();
			codError = "0000";
			descError = MENSAJEEXITOSO;
			mensajeError = "Consulta de ciudades del programa"
					+ DAOPrograma.buscarProgramaId(idPrograma).getNombrePrograma();
			return d;
		} catch (Exception e) {
			mensajeError += " " + e.getMessage();
			codError += "103";
			return null;
		} finally {
			Util.CreateLog(CODIGOERROR + codError, descError, mensajeError);
		}

	}

	public String EliminarPrograma(int id) {
		try {
			String programa = DAOPrograma.buscarProgramaId(id).getNombrePrograma();
			codError = DAOPrograma.eliminarPrograma(id);
			descError = MENSAJEEXITOSO + "en eliminar programa " + programa;
			mensajeError = codError == "0000" ? "Consulta exitosa " + programa
					: "Fallo al eliminar el programa " + programa;

		} catch (Exception e) {
			codError = "103";
			mensajeError = e.getMessage();
		}
		Util.CreateLog(CODIGOERROR + codError, descError, mensajeError);
		return codError;
	}
}
