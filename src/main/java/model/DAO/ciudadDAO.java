package model.DAO;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;

import model.DTO.Ciudad;

public class ciudadDAO {
	private static final String PERSISTENCEUNITNAME = "SoftwareEngineeringProj";
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private static String ERRORCREAR = "Error al crear ciudad";
	private static String ERRORACTUALIZAR = "Error al actualizar ciudad";
	private static String ERRORELIMINAR = "Error al eliminar ciudad";
	private static final String CODIGOERROR = "10";

	public ciudadDAO() {
		emf = Persistence.createEntityManagerFactory(PERSISTENCEUNITNAME);
		em = emf.createEntityManager();
	}

	public String Create(Ciudad ciudad) {
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

	public String actualizarCiudad(int idCiudad, String nombreCiudad) {
		String mensajeError = ERRORACTUALIZAR;
		String codError = "";
		try {

			em.getTransaction().begin();
			Ciudad ciudadmodificar = em.find(Ciudad.class, idCiudad);
			ciudadmodificar.setNombreCiudad(nombreCiudad);
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

	public List<Ciudad> buscarTodosCiudads() {
		TypedQuery<Ciudad> seleccionarCiudad = em.createNamedQuery("Ciudad.findAll", Ciudad.class);
		List<Ciudad> departamentos = seleccionarCiudad.getResultList();
		return departamentos;
	}

	public Ciudad buscarCiudadId(int idCiudad) {
		Ciudad departamento = em.find(Ciudad.class, idCiudad);
		return departamento;
	}

	public Ciudad buscarCiudadId(String nombreCiudad) {
		Ciudad departamento = em.find(Ciudad.class, nombreCiudad);
		return departamento;
	}

	public String eliminarCiudad(int idCiudad) {

		Ciudad departamento = em.find(Ciudad.class, idCiudad);
		String codError = "";
		String mensajeError = ERRORELIMINAR + "con Id: " + idCiudad + " ";
		try {
			em.getTransaction().begin();
			em.remove(departamento);
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