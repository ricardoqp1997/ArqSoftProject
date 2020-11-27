package controller;

import java.util.List;

import model.DAO.CiudadDAO;
import model.DAO.Util;
import model.DTO.Aspirante;
import model.DTO.Ciudad;
import model.DTO.Departamento;
import model.DTO.SedeUniversidad;

public class ControladorCiudad {
	private static CiudadDAO DAOCiudad;
	private static final String CODIGOERROR = "CC";
	private static final String DESCRIPCIONERROR = " Error en controlador de ciudad ";
	private static final String MENSAJEEXITOSO = "Transaccion Exitosa";
	private static final String tabla = "Ciudad";
	private static final String llavetabla = "ciudadId";
	private static String codError = CODIGOERROR;
	private static String descError = DESCRIPCIONERROR;
	private static String mensajeError = "";

	public ControladorCiudad() {
		DAOCiudad = new CiudadDAO();
	}

	public String crearCiudad(String nombreCiudad, Departamento departamento) {
		try {
			Ciudad ciudad = new Ciudad();
			ciudad.setCiudadId(Util.getMaxIDd(tabla, llavetabla));
			ciudad.setNombreCiudad(nombreCiudad);
			ciudad.setDepartamento(departamento);
			codError = DAOCiudad.Create(ciudad);
			descError = (codError == "0000" ? MENSAJEEXITOSO : DESCRIPCIONERROR);
		} catch (Exception e) {
			codError = "03";
			mensajeError += " " + e.getMessage();
		}
		Util.CreateLog(codError, descError, mensajeError);
		return codError;
	}

	public String actualizarCiudad(Integer idCiudad, String nombreCiudad, Departamento departamento,
			List<Aspirante> aspirantes, List<SedeUniversidad> sedeUniversidads) {
		try {
			Ciudad ciudad = new Ciudad();
			ciudad = DAOCiudad.buscarCiudadId(idCiudad);
			ciudad.setNombreCiudad(nombreCiudad == null ? nombreCiudad : ciudad.getNombreCiudad());
			ciudad.setDepartamento(departamento == null ? departamento : ciudad.getDepartamento());
			if (aspirantes == null) {
				ciudad.setAspirantes(aspirantes);
			} else {
				for (Aspirante a : aspirantes) {
					ciudad.addAspirante(a);
				}
			}
			if (aspirantes == null) {
				ciudad.setSedeUniversidads(sedeUniversidads);
				;
			} else {
				for (SedeUniversidad su : sedeUniversidads) {
					ciudad.addSedeUniversidad(su);
				}
			}

			codError = DAOCiudad.actualizarCiudad(ciudad);
			descError = (codError == "0000" ? MENSAJEEXITOSO : DESCRIPCIONERROR);
		} catch (Exception e) {
			codError = "03";
			mensajeError += " " + e.getMessage();
		}
		Util.CreateLog(codError, descError, mensajeError);
		return codError;
	}

	public List<Ciudad> buscarCiudades() {
		try {
			List<Ciudad> ciudades = DAOCiudad.buscarTodosCiudads();
			codError = "0000";
			descError = MENSAJEEXITOSO;
			mensajeError = "Consulta exitosa " + ciudades.size() + " Ciudades";
			return ciudades;
		} catch (Exception e) {
			mensajeError += " " + e.getMessage();
			codError += "103";
			return null;
		} finally {
			Util.CreateLog(codError, descError, mensajeError);
		}
	}

	public Ciudad consultarAspirante(Integer idCiudad) {

		try {
			Ciudad ciudad = DAOCiudad.buscarCiudadId(idCiudad);
			codError = "0000";
			descError = MENSAJEEXITOSO + "en Consulta de aspirante";
			mensajeError = "Consulta exitosa " + ciudad.getNombreCiudad();
			return ciudad;
		} catch (Exception e) {
			mensajeError += " " + e.getMessage();
			codError += "103";
			return null;
		} finally {
			Util.CreateLog(codError, descError, mensajeError);
		}
	}

	public List<Aspirante> consultarAspirantesCiudad(Integer idCiudad) {
		try {
			List<Aspirante> aspirantes = DAOCiudad.buscarCiudadId(idCiudad).getAspirantes();
			codError = "0000";
			descError = MENSAJEEXITOSO + "en Consulta de aspirante";
			mensajeError = "Consulta exitosa " + aspirantes.size();
			return aspirantes;
		} catch (Exception e) {
			mensajeError += " " + e.getMessage();
			codError += "103";
			return null;
		} finally {
			Util.CreateLog(codError, descError, mensajeError);
		}
	}

	public List<SedeUniversidad> consultarSedesUniversidadCiudad(Integer idCiudad) {
		try {
			List<SedeUniversidad> sedes = DAOCiudad.buscarCiudadId(idCiudad).getSedeUniversidads();
			codError = "0000";
			descError = MENSAJEEXITOSO + "en Consulta de aspirante";
			mensajeError = "Consulta exitosa " + sedes.size();
			return sedes;
		} catch (Exception e) {
			mensajeError += " " + e.getMessage();
			codError += "103";
			return null;
		} finally {
			Util.CreateLog(codError, descError, mensajeError);
		}
	}

	public String EliminarCiudad(int id) {
		try {
			codError = DAOCiudad.eliminarCiudad(id);

			descError = MENSAJEEXITOSO + "en eliminar ciudad " + id;
			mensajeError = codError == "0000" ? "Consulta exitosa " + id : "Fallo al eliminar la ciudad" + id;
		} catch (Exception e) {
			codError = "103";
			mensajeError = e.getMessage();
		}
		Util.CreateLog(codError, descError, mensajeError);
		return codError;
	}

}
