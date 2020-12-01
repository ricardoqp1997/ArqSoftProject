package controller;

import java.util.List;

import model.DAO.SedeUniversidadDAO;
import model.DAO.Util;
import model.DTO.Aspirante;
import model.DTO.SedeUniversidad;

public class ControladorSedeUniversidad {
	private static SedeUniversidadDAO DAOSedeUniversidad;
	private static final String CODIGOERROR = "CP";
	private static final String DESCRIPCIONERROR = " Error en controlador de sede ";
	private static final String tabla = "SedeUniversidad";
	private static final String llavetabla = "sedeId";
	private static final String MENSAJEEXITOSO = "Transaccion Exitosa";
	private static String codError = CODIGOERROR;
	private static String descError = DESCRIPCIONERROR;
	private static String mensajeError = "";

	public ControladorSedeUniversidad() {
		DAOSedeUniversidad = new SedeUniversidadDAO();
	}

	public String crearSedeUniversidad(String nombreSedeUniversidad) {

		try {
			SedeUniversidad sede = new SedeUniversidad();
			sede.setSedeId(Util.getMaxIDd(tabla, llavetabla));
			sede.setNombreSede(nombreSedeUniversidad.toUpperCase());
			codError = DAOSedeUniversidad.Create(sede);

			mensajeError = "Se ha creado correctamente el sede " + nombreSedeUniversidad;
			descError = (codError == "0000" ? MENSAJEEXITOSO : DESCRIPCIONERROR);
		} catch (NullPointerException e) {
			codError += "004";
			mensajeError += " " + e.getMessage();
		} catch (Exception e) {
			mensajeError += " " + e.getMessage();
			codError += "103";
		}
		Util.CreateLog(CODIGOERROR + codError, descError, mensajeError);
		return codError;
	}

	public SedeUniversidad seleccionarSedeUniversidadId(int id) {
		try {
			SedeUniversidad d = DAOSedeUniversidad.buscarSedeUniversidadId(id);
			codError = "0000";
			descError = MENSAJEEXITOSO + "en Consulta SedeUniversidad";
			mensajeError = "Consulta exitosa " + d.getNombreSede();
			return d;
		} catch (Exception e) {
			mensajeError += " " + e.getMessage();
			codError += "103";
			return null;
		} finally {
			Util.CreateLog(CODIGOERROR + codError, descError, mensajeError);
		}

	}

	public SedeUniversidad seleccionarSedeUniversidadNombre(String nombre) {

		try {
			SedeUniversidad d = DAOSedeUniversidad.buscarSedeUniversidadId(nombre);
			codError = "0000";
			descError = MENSAJEEXITOSO + "en Consulta SedeUniversidad";
			mensajeError = "Consulta exitosa " + d.getNombreSede();
			return d;
		} catch (Exception e) {
			mensajeError += " " + e.getMessage();
			codError += "103";
			return null;
		} finally {
			Util.CreateLog(CODIGOERROR + codError, descError, mensajeError);
		}

	}

	public List<SedeUniversidad> seleccionarSedeUniversidads() {

		try {
			List<SedeUniversidad> d = DAOSedeUniversidad.buscarTodosSedeUniversidads();
			codError = "0000";
			descError = MENSAJEEXITOSO + "en Consulta SedeUniversidads";
			mensajeError = "Consulta exitosa " + d.size() + " sedes";
			return d;
		} catch (Exception e) {
			mensajeError += " " + e.getMessage();
			codError += "103";
			return null;
		} finally {
			Util.CreateLog(CODIGOERROR + codError, descError, mensajeError);
		}

	}

	public List<Aspirante> consultarAspirantesSedeUniversidad(String idSedeUniversidad) {

		try {
			List<Aspirante> d = DAOSedeUniversidad.buscarSedeUniversidadId(idSedeUniversidad).getAspirantes();
			codError = "0000";
			descError = MENSAJEEXITOSO;
			mensajeError = "Consulta de aspirantes de la sede"
					+ DAOSedeUniversidad.buscarSedeUniversidadId(idSedeUniversidad).getAspirantes();
			return d;
		} catch (Exception e) {
			mensajeError += " " + e.getMessage();
			codError += "103";
			return null;
		} finally {
			Util.CreateLog(CODIGOERROR + codError, descError, mensajeError);
		}

	}

	public String EliminarSedeUniversidad(int id) {
		try {
			String sede = DAOSedeUniversidad.buscarSedeUniversidadId(id).getNombreSede();
			codError = DAOSedeUniversidad.eliminarSedeUniversidad(id);
			descError = MENSAJEEXITOSO + "en eliminar sede " + sede;
			mensajeError = codError == "0000" ? "Consulta exitosa " + sede : "Fallo al eliminar la sede " + sede;

		} catch (Exception e) {
			codError = "103";
			mensajeError = e.getMessage();
		}
		Util.CreateLog(CODIGOERROR + codError, descError, mensajeError);
		return codError;
	}
}
