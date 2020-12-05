package model.DAO;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;

import model.DTO.Aspirante;

public class AspiranteDAO {
	private static final String PERSISTENCEUNITNAME = "AdmisionesUN";
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private static final String ERRORCREAR = "Error al crear aspirante";
	private static final String ERRORACTUALIZAR = "Error al actualizar aspirante";
	private static final String ERRORELIMINAR = "Error al eliminar aspirante";
	private static final String CODIGOERROR = "DAO30";

	public AspiranteDAO() {
		emf = Persistence.createEntityManagerFactory(PERSISTENCEUNITNAME);
		em = emf.createEntityManager();
		if (!em.isOpen()) {
			em = emf.createEntityManager();
		}
	}

	public String Create(Aspirante aspirante) {
		String mensajeError = "";
		String codError = "";

		try {
			em.getTransaction().begin();
			em.persist(aspirante);
			em.getTransaction().commit();

			codError = "0000";
		} catch (EntityExistsException existEntity) {
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

	public String actualizarAspirante(Aspirante aspirante) {
		String mensajeError = "";
		String codError = "";
		try {

			em.getTransaction().begin();
			em.merge(aspirante);
			em.getTransaction().commit();
			codError = "0000";
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
		String mensajeError = "";

		try {
			em.getTransaction().begin();
			em.remove(aspirante);
			em.getTransaction().commit();
		} catch (TransactionRequiredException TransactionException) {
			mensajeError += TransactionException.getLocalizedMessage() + " " + TransactionException.getMessage();
			codError = CODIGOERROR + "02";
			em.getTransaction().rollback();
			Util.CreateLog(codError, ERRORELIMINAR + "con Identificacion: " + idAspirante, mensajeError);
		} catch (Exception e) {
			mensajeError += e.getLocalizedMessage() + " " + e.getMessage();
			codError = CODIGOERROR + "03";
			em.getTransaction().rollback();
			Util.CreateLog(codError, ERRORELIMINAR + "con Identificacion: " + idAspirante, mensajeError);
		} finally {
			em.close();
		}
		return codError;
	}
}
