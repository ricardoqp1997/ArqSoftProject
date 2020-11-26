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
	AspiranteDAO DAOAspirante;

	private static final String CODIGOERROR = "CA";
	private static final String DESCRIPCIONERROR = " Error en controlador de departamento ";
	private static final String MENSAJEEXITOSO = "Transaccion Exitosa";
	private static String codError = CODIGOERROR;
	private static String descError = DESCRIPCIONERROR;
	private static String mensajeError = "";

	public ControladorAspirante() {
		DAOAspirante = new AspiranteDAO();
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
			mensajeError = MENSAJEEXITOSO;
		} catch (Exception e) {
			codError = "03";
			mensajeError += " " + e.getMessage();
		}
		Util.CreateLog(codError, descError, mensajeError);
		return codError;
	}
}
