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
import model.DTO.SedeUniversidad;

public class SedeUniversidadDAO {
	private static final String PERSISTENCEUNITNAME = "SoftwareEngineeringProj";
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private static String ERRORCREAR = "Error al crear sede";
	private static String ERRORACTUALIZAR = "Error al actualizar sede";
	private static String ERRORELIMINAR = "Error al eliminar sede";
	private static final String CODIGOERROR = "DAO50";

	public SedeUniversidadDAO() {
		emf = Persistence.createEntityManagerFactory(PERSISTENCEUNITNAME);
		em = emf.createEntityManager();
	}

	public String Create(SedeUniversidad ciudad) {
		String mensajeError = ERRORCREAR;
		String codError = "";

		try {
			em.getTransaction().begin();
			em.persist(ciudad);
			em.getTransaction().commit();

			codError = "0000";
		}  catch (EntityExistsException existEntity) {
			mensajeError += " " + existEntity.getLocalizedMessage() + " " + existEntity.getMessage();
			codError = CODIGOERROR + " 01";
			em.getTransaction().rollback();
			Util.CreateLog(codError, ERRORCREAR, mensajeError);
		} catch (TransactionRequiredException TransactionException) {
			mensajeError += " " + TransactionException.getLocalizedMessage() + " " + TransactionException.getMessage();
			codError = CODIGOERROR + "02";
			em.getTransaction().rollback();
			Util.CreateLog(codError, ERRORCREAR, mensajeError);
		} catch (Exception e) {
			mensajeError += " " + e.getLocalizedMessage() + " " + e.getMessage();
			codError = CODIGOERROR + "03";
			em.getTransaction().rollback();
			Util.CreateLog(codError, ERRORCREAR, mensajeError);
		} finally {

			em.close();
		}
		return codError;
	}

	public String actualizarSedeUniversidadNombre(Integer idSedeUniversidad, List<Aspirante> aspirantes,
			String nombreSedeUniversidad, String direccionSede, Ciudad ciudad) {
		String mensajeError = ERRORACTUALIZAR;
		String codError = "";
		try {

			em.getTransaction().begin();
			SedeUniversidad sedemodificar = em.find(SedeUniversidad.class, idSedeUniversidad);
			sedemodificar.setAspirantes(aspirantes == null ? sedemodificar.getAspirantes() : aspirantes);
			sedemodificar.setCiudad(ciudad == null ? sedemodificar.getCiudad() : ciudad);
			sedemodificar.setDireccionSede(direccionSede == null ? sedemodificar.getDireccionSede() : direccionSede);
			sedemodificar.setNombreSede(
					nombreSedeUniversidad == null ? sedemodificar.getNombreSede() : nombreSedeUniversidad);

		} catch (TransactionRequiredException TransactionException) {
			mensajeError += " " + TransactionException.getLocalizedMessage() + " " + TransactionException.getMessage();
			codError = CODIGOERROR + "02";
			em.getTransaction().rollback();
			Util.CreateLog(codError, ERRORACTUALIZAR, mensajeError);
		} catch (Exception e) {
			mensajeError += " " + e.getLocalizedMessage() + " " + e.getMessage();
			codError = CODIGOERROR + "03";
			em.getTransaction().rollback();
			Util.CreateLog(codError, ERRORACTUALIZAR, mensajeError);
		} finally {

			em.close();
		}
		return codError;
	}

	public List<SedeUniversidad> buscarTodosSedeUniversidads() {
		TypedQuery<SedeUniversidad> seleccionarSedeUniversidad = em.createNamedQuery("SedeUniversidad.findAll",
				SedeUniversidad.class);
		List<SedeUniversidad> sedes = seleccionarSedeUniversidad.getResultList();
		return sedes;
	}

	public SedeUniversidad buscarSedeUniversidadId(int idSedeUniversidad) {
		SedeUniversidad sede = em.find(SedeUniversidad.class, idSedeUniversidad);
		return sede;
	}

	public SedeUniversidad buscarSedeUniversidadId(String nombreSedeUniversidad) {
		SedeUniversidad sede = em.find(SedeUniversidad.class, nombreSedeUniversidad);
		return sede;
	}

	public String eliminarSedeUniversidad(int idSedeUniversidad) {

		SedeUniversidad sede = em.find(SedeUniversidad.class, idSedeUniversidad);
		String codError = "";
		String mensajeError = "";
		try {
			em.getTransaction().begin();
			em.remove(sede);
			em.getTransaction().commit();
		} catch (TransactionRequiredException TransactionException) {
			mensajeError += TransactionException.getLocalizedMessage() + " " + TransactionException.getMessage();
			codError = CODIGOERROR + "02";
			em.getTransaction().rollback();
			Util.CreateLog(codError, ERRORELIMINAR + "con id: " + idSedeUniversidad, mensajeError);
		} catch (Exception e) {
			mensajeError += e.getLocalizedMessage() + " " + e.getMessage();
			codError = CODIGOERROR + "03";
			em.getTransaction().rollback();
			Util.CreateLog(codError, ERRORELIMINAR + "con id: " + idSedeUniversidad, mensajeError);
		} finally {
			em.close();
		}
		return codError;
	}
}
