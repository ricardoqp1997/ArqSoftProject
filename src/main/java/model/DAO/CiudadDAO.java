package model.DAO;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;

import model.DTO.Ciudad;

public class CiudadDAO {
	private static final String PERSISTENCEUNITNAME = "AdmisionesUN";
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private static String ERRORCREAR = "Error al crear ciudad";
	private static String ERRORACTUALIZAR = "Error al actualizar ciudad";
	private static String ERRORELIMINAR = "Error al eliminar ciudad";
	private static final String CODIGOERROR = "DAO20";
	private static final String MENSJAEEXITOSO = "Transaccion Exitosa";

	public CiudadDAO() {
		emf = Persistence.createEntityManagerFactory(PERSISTENCEUNITNAME);
		em = emf.createEntityManager();
	}

	public String Create(Ciudad ciudad) {
		String mensajeError = "";
		String codError = "";
		String descError = "";

		try {
			em.getTransaction().begin();
			em.persist(ciudad);
			em.getTransaction().commit();

			codError = "0000";
			descError = MENSJAEEXITOSO;
		} catch (EntityExistsException existEntity) {
			mensajeError += " " + existEntity.getLocalizedMessage() + " " + existEntity.getMessage();
			codError = CODIGOERROR + " 01";
			descError = ERRORCREAR;
			em.getTransaction().rollback();

		} catch (TransactionRequiredException TransactionException) {
			mensajeError += " " + TransactionException.getLocalizedMessage() + " " + TransactionException.getMessage();
			codError = CODIGOERROR + "02";
			descError = ERRORCREAR;
			em.getTransaction().rollback();

		} catch (Exception e) {
			descError = ERRORCREAR;
			mensajeError += " " + e.getLocalizedMessage() + " " + e.getMessage();
			codError = CODIGOERROR + "03";
			em.getTransaction().rollback();

		} finally {
			Util.CreateLog(codError, descError, mensajeError);

		}
		return codError;
	}

	public String actualizarCiudad(Ciudad ciudad) {
		String mensajeError = "";
		String codError = "";
		String descError = "";
		try {

			em.getTransaction().begin();
			em.merge(ciudad);
			codError = "0000";
			descError = MENSJAEEXITOSO;
		} catch (TransactionRequiredException TransactionException) {
			mensajeError += " " + TransactionException.getLocalizedMessage() + " " + TransactionException.getMessage();
			codError = CODIGOERROR + "02";
			em.getTransaction().rollback();
			descError = ERRORACTUALIZAR;
		} catch (Exception e) {
			mensajeError += " " + e.getLocalizedMessage() + " " + e.getMessage();
			codError = CODIGOERROR + "03";
			em.getTransaction().rollback();
			descError = ERRORACTUALIZAR;
		} finally {
			Util.CreateLog(codError, descError, mensajeError);

		}
		return codError;
	}

	public List<Ciudad> buscarTodosCiudads() {
		TypedQuery<Ciudad> seleccionarCiudad = em.createNamedQuery("Ciudad.findAll", Ciudad.class);
		List<Ciudad> ciudads = seleccionarCiudad.getResultList();
		return ciudads;
	}

	public Ciudad buscarCiudadId(int idCiudad) {
		Ciudad ciudad = em.find(Ciudad.class, idCiudad);
		return ciudad;
	}

	public Ciudad buscarCiudadId(String nombreCiudad) {
		Ciudad ciudad = em.find(Ciudad.class, nombreCiudad);
		return ciudad;
	}

	public String eliminarCiudad(int idCiudad) {

		Ciudad ciudad = em.find(Ciudad.class, idCiudad);
		String mensajeError = "";
		String codError = "";
		String descError = "";
		try {
			em.getTransaction().begin();
			em.remove(ciudad);
			em.getTransaction().commit();
			descError = MENSJAEEXITOSO;
			codError = "0000";
		} catch (TransactionRequiredException TransactionException) {
			mensajeError += TransactionException.getLocalizedMessage() + " " + TransactionException.getMessage();
			codError = CODIGOERROR + "02";
			descError = ERRORELIMINAR;
			em.getTransaction().rollback();

		} catch (Exception e) {
			mensajeError += e.getLocalizedMessage() + " " + e.getMessage();
			codError = CODIGOERROR + "03";
			descError = ERRORELIMINAR;
			em.getTransaction().rollback();

		} finally {
			Util.CreateLog(codError, descError, mensajeError);

		}
		return codError;
	}
}