package controller;

import java.util.List;

import model.DAO.AspiranteDAO;
import model.DAO.Util;
import model.DTO.Aspirante;
import model.DTO.Ciudad;
import model.DTO.Consulta;
import model.DTO.Programa;
import model.DTO.SedeUniversidad;

public class ControladorAspirante {
	private static AspiranteDAO DAOAspirante;

	private static final String CODIGOERROR = "CA";
	private static final String DESCRIPCIONERROR = " Error en controlador de aspirante ";
	private static final String MENSAJEEXITOSO = "Transaccion Exitosa";
	private static String codError = CODIGOERROR;
	private static String descError = DESCRIPCIONERROR;
	private static String mensajeError = "";

	private List<Aspirante> admitidos;
	private List<Aspirante> noAdmitidos;

	public ControladorAspirante() {
		DAOAspirante = new AspiranteDAO();
	}

	public String crearAspirante(Integer idAspirante, String nombreAspirante, String apellidoAspirante,
			Boolean admitido, Integer cedulaAspirante, Programa programa, String examenFilename,
			SedeUniversidad sedeUniversidad, Integer qrcodeAspirante, List<Consulta> consultas, Ciudad ciudad,
			Integer edadAspirante, String sexoAspirante) {
		try {

			Aspirante aspirante = new Aspirante();
			aspirante.setNombreAspirante(nombreAspirante);
			aspirante.setAdmitido(admitido);
			aspirante.setApellidoAspirante(apellidoAspirante);
			aspirante.setCedulaAspirante(cedulaAspirante);
			aspirante.setCiudad(ciudad);
			aspirante.setConsultas(consultas);
			aspirante.setEdadAspirante(edadAspirante);
			aspirante.setExamenFilename(examenFilename);
			aspirante.setPrograma(programa);
			aspirante.setSexoAspirante(sexoAspirante);
			aspirante.setQrcodeAspirante(qrcodeAspirante);
			aspirante.setSedeUniversidad(sedeUniversidad);
			codError = DAOAspirante.Create(aspirante);
			descError = (codError == "0000" ? MENSAJEEXITOSO : DESCRIPCIONERROR);
		} catch (Exception e) {
			codError = "03";
			mensajeError += " " + e.getMessage();
		}
		Util.CreateLog(codError, descError, mensajeError);
		return codError;
	}

	public String actualizarAspirante(Integer idAspirante, String nombreAspirante, String apellidoAspirante,
			Boolean admitido, Integer cedulaAspirante, Programa programa, String examenFilename,
			SedeUniversidad sedeUniversidad, Integer qrcodeAspirante, List<Consulta> consultas, Ciudad ciudad,
			Integer edadAspirante, String sexoAspirante) {
		try {
			Aspirante aspirantemodificar = DAOAspirante.buscarAspiranteId(cedulaAspirante);
			aspirantemodificar.setNombreAspirante(
					nombreAspirante == null ? aspirantemodificar.getNombreAspirante() : nombreAspirante);
			aspirantemodificar.setAdmitido(admitido == null ? aspirantemodificar.getAdmitido() : admitido);
			aspirantemodificar.setApellidoAspirante(
					apellidoAspirante == null ? aspirantemodificar.getApellidoAspirante() : apellidoAspirante);
			aspirantemodificar.setCedulaAspirante(
					cedulaAspirante == null ? aspirantemodificar.getCedulaAspirante() : cedulaAspirante);
			aspirantemodificar.setCiudad(ciudad == null ? aspirantemodificar.getCiudad() : ciudad);
			aspirantemodificar.setConsultas(consultas == null ? aspirantemodificar.getConsultas() : consultas);
			aspirantemodificar
					.setEdadAspirante(edadAspirante == null ? aspirantemodificar.getEdadAspirante() : edadAspirante);
			aspirantemodificar.setExamenFilename(
					examenFilename == null ? aspirantemodificar.getExamenFilename() : examenFilename);
			aspirantemodificar.setPrograma(programa == null ? aspirantemodificar.getPrograma() : programa);
			aspirantemodificar
					.setSexoAspirante(sexoAspirante == null ? aspirantemodificar.getSexoAspirante() : sexoAspirante);
			aspirantemodificar.setQrcodeAspirante(
					qrcodeAspirante == null ? aspirantemodificar.getQrcodeAspirante() : qrcodeAspirante);
			aspirantemodificar.setSedeUniversidad(
					sedeUniversidad == null ? aspirantemodificar.getSedeUniversidad() : sedeUniversidad);
			codError = DAOAspirante.actualizarAspirante(aspirantemodificar);

			descError = (codError == "0000" ? MENSAJEEXITOSO : DESCRIPCIONERROR);
		} catch (Exception e) {
			codError = "03";
			mensajeError += " " + e.getMessage();
		}
		Util.CreateLog(codError, descError, mensajeError);
		return codError;
	}

	public Aspirante consultarAspirante(Integer cedula) {

		try {
			Aspirante d = DAOAspirante.buscarAspiranteId(cedula);
			codError = "0000";
			descError = MENSAJEEXITOSO + "en Consulta de aspirante";
			mensajeError = "Consulta exitosa " + d.getCedulaAspirante();
			return d;
		} catch (Exception e) {
			mensajeError += " " + e.getMessage();
			codError += "103";
			return null;
		} finally {
			Util.CreateLog(codError, descError, mensajeError);
		}
	}

	public List<Aspirante> buscarTodosAspirantes() {

		try {
			List<Aspirante> d = DAOAspirante.buscarTodosAspirantes();
			codError = "0000";
			descError = MENSAJEEXITOSO;
			mensajeError = "Consulta exitosa " + d.size() + " Aspirantes registrados";
			return d;
		} catch (Exception e) {
			mensajeError += " " + e.getMessage();
			codError += "103";
			return null;
		} finally {
			Util.CreateLog(codError, descError, mensajeError);
		}
	}

	public String EliminarAspirante(int id) {
		try {
			codError = DAOAspirante.eliminarAspirante(id);

			descError = MENSAJEEXITOSO + "en eliminar aspirante " + id;
			mensajeError = codError == "0000" ? "Consulta exitosa " + id : "Fallo al eliminar el aspirante " + id;
		} catch (Exception e) {
			codError = "103";
			mensajeError = e.getMessage();
		}
		Util.CreateLog(codError, descError, mensajeError);
		return codError;
	}

	public List<Aspirante> estudiantesAdmitidos() {

		try {
			List<Aspirante> aspirantes = DAOAspirante.buscarTodosAspirantes();

			for (Aspirante a : aspirantes) {
				if (a.getAdmitido() == true) {
					admitidos.add(a);
				}
			}
			codError = "0000";
			descError = MENSAJEEXITOSO;
			mensajeError = "Consulta exitosa " + admitidos.size() + " Aspirantes admitidos";
			return admitidos;
		} catch (Exception e) {
			mensajeError += " " + e.getMessage();
			codError += "103";
			return null;
		} finally {
			Util.CreateLog(codError, descError, mensajeError);
		}
	}

	public List<Aspirante> estudiantesNoAdmitidos() {

		try {
			List<Aspirante> aspirantes = DAOAspirante.buscarTodosAspirantes();

			for (Aspirante a : aspirantes) {
				if (a.getAdmitido() == false) {
					noAdmitidos.add(a);
				}
			}
			codError = "0000";
			descError = MENSAJEEXITOSO;
			mensajeError = "Consulta exitosa " + noAdmitidos.size() + " Aspirantes no admitidos";
			return noAdmitidos;
		} catch (Exception e) {
			mensajeError += " " + e.getMessage();
			codError += "103";
			return null;
		} finally {
			Util.CreateLog(codError, descError, mensajeError);
		}
	}
}
