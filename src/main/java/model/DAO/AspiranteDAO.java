package model.DAO;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;

import model.DTO.Aspirante;
import model.DTO.Ciudad;
import model.DTO.Consulta;
import model.DTO.Programa;
import model.DTO.SedeUniversidad;

public class AspiranteDAO {
	private static final String PERSISTENCEUNITNAME = "SoftwareEngineeringProj";
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private static String ERRORCREAR = "Error al crear aspirante";
	private static String ERRORACTUALIZAR = "Error al actualizar aspirante";
	private static String ERRORELIMINAR = "Error al eliminar aspirante";
	private static final String CODIGOERROR = "10";

	public AspiranteDAO() {
		emf = Persistence.createEntityManagerFactory(PERSISTENCEUNITNAME);
		em = emf.createEntityManager();
	}

	public String Create(Aspirante ciudad) {
		String mensajeError = ERRORCREAR;
		String codError = "";

		try {
			em.getTransaction().begin();
			em.persist(ciudad);
			em.getTransaction().commit();

			codError = "0000";
		} catch (EntityExistsException existEntity) {
			mensajeError += " " + existEntity.getLocalizedMessage() + " " + existEntity.getMessage();
			codError = CODIGOERROR + " 01";
			em.getTransaction().rollback();
			LogAuditoriaDAO.RegistroLog(codError, mensajeError);
		} catch (TransactionRequiredException TransactionException) {
			mensajeError += " " + TransactionException.getLocalizedMessage() + " " + TransactionException.getMessage();
			codError = CODIGOERROR + "02";
			em.getTransaction().rollback();
			LogAuditoriaDAO.RegistroLog(codError, mensajeError);
		} catch (Exception e) {
			mensajeError += " " + e.getLocalizedMessage() + " " + e.getMessage();
			codError = CODIGOERROR + "03";
			em.getTransaction().rollback();
			LogAuditoriaDAO.RegistroLog(codError, mensajeError);
		} finally {

			em.close();
		}
		return codError;
	}

	public String actualizarAspiranteNombre(Integer idAspirante, String nombreAspirante, String apellidoAspirante,
			Boolean admitido, Integer cedulaAspirante, Programa programa, String examenFilename,
			SedeUniversidad sedeUniversidad, Integer qrcodeAspirante, List<Consulta> consultas, Ciudad ciudad,
			Integer edadAspirante, String sexoAspirante) {
		String mensajeError = ERRORACTUALIZAR;
		String codError = "";
		try {

			em.getTransaction().begin();
			Aspirante aspirantemodificar = em.find(Aspirante.class, idAspirante);
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
		} catch (TransactionRequiredException TransactionException) {
			mensajeError += " " + TransactionException.getLocalizedMessage() + " " + TransactionException.getMessage();
			codError = CODIGOERROR + "02";
			em.getTransaction().rollback();
			LogAuditoriaDAO.RegistroLog(codError, mensajeError);
		} catch (Exception e) {
			mensajeError += " " + e.getLocalizedMessage() + " " + e.getMessage();
			codError = CODIGOERROR + "03";
			em.getTransaction().rollback();
			LogAuditoriaDAO.RegistroLog(codError, mensajeError);
		} finally {

			em.close();
		}
		return codError;
	}

	public List<Aspirante> buscarTodosAspirantes() {
		TypedQuery<Aspirante> seleccionarAspirante = em.createNamedQuery("Aspirante.findAll", Aspirante.class);
		List<Aspirante> aspirantes = seleccionarAspirante.getResultList();
		return aspirantes;
	}

	public Aspirante buscarAspiranteId(int idAspirante) {
		Aspirante aspirante = em.find(Aspirante.class, idAspirante);
		return aspirante;
	}

	public Aspirante buscarAspiranteId(String nombreAspirante) {
		Aspirante aspirante = em.find(Aspirante.class, nombreAspirante);
		return aspirante;
	}

	public String eliminarAspirante(int idAspirante) {

		Aspirante aspirante = em.find(Aspirante.class, idAspirante);
		String codError = "";
		String mensajeError = ERRORELIMINAR + "con Identificacion: " + idAspirante + " ";
		try {
			em.getTransaction().begin();
			em.remove(aspirante);
			em.getTransaction().commit();
		} catch (TransactionRequiredException TransactionException) {
			mensajeError += TransactionException.getLocalizedMessage() + " " + TransactionException.getMessage();
			codError = CODIGOERROR + "02";
			em.getTransaction().rollback();
			LogAuditoriaDAO.RegistroLog(codError, mensajeError);
		} catch (Exception e) {
			mensajeError += e.getLocalizedMessage() + " " + e.getMessage();
			codError = CODIGOERROR + "03";
			em.getTransaction().rollback();
			LogAuditoriaDAO.RegistroLog(codError, mensajeError);
		} finally {
			em.close();
		}
		return codError;
	}
}
