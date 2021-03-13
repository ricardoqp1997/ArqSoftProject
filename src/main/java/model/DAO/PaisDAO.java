package model.DAO;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TransactionRequiredException;

import model.DTO.PaisModel;

public class PaisDAO {
	private static final String PERSISTENCEUNITNAME = "TemperaturaSoftware";
	private static EntityManagerFactory emf;
	private static EntityManager em;
	// private static final String ERRORCREAR = "Error al crear aspirante";
	// private static final String ERRORACTUALIZAR = "Error al actualizar aspirante";
	// private static final String ERRORELIMINAR = "Error al eliminar aspirante";
	private static final String CODIGOERROR = "DAO30";

	public PaisDAO() {
		emf = Persistence.createEntityManagerFactory(PERSISTENCEUNITNAME);
		em = emf.createEntityManager();
		if (!em.isOpen()) {
			em = emf.createEntityManager();
		}
	}
	
	public String Create(PaisModel pais) {
		// String mensajeError = "";
		String codError = "";

		try {
			em.getTransaction().begin();
			em.persist(pais);
			em.getTransaction().commit();

			codError = "0000";
		} catch (EntityExistsException existEntity) {
			// mensajeError += " " + existEntity.getLocalizedMessage() + " " + existEntity.getMessage();
			codError = CODIGOERROR + " 01";
			em.getTransaction().rollback();
			// Util.CreateLog(codError, ERRORCREAR, mensajeError);
		} catch (TransactionRequiredException TransactionException) {
			// mensajeError += " " + TransactionException.getLocalizedMessage() + " " + TransactionException.getMessage();
			codError = CODIGOERROR + "02";
			em.getTransaction().rollback();
			// Util.CreateLog(codError, ERRORCREAR, mensajeError);
		} catch (Exception e) {
			// mensajeError += " " + e.getLocalizedMessage() + " " + e.getMessage();
			codError = CODIGOERROR + "03";
			em.getTransaction().rollback();
			// Util.CreateLog(codError, ERRORCREAR, mensajeError);
		} 
		return codError;
	}
	
	public String actualizarPais(PaisModel pais) {
		// String mensajeError = "";
		String codError = "";
		// String descError = "";
		try {

			em.getTransaction().begin();
			em.merge(pais);
			// codError = "0000";
			// descError = MENSJAEEXITOSO;
		} catch (TransactionRequiredException TransactionException) {
			// mensajeError += " " + TransactionException.getLocalizedMessage() + " " + TransactionException.getMessage();
			// codError = CODIGOERROR + "02";
			em.getTransaction().rollback();
			// descError = ERRORACTUALIZAR;
		} catch (Exception e) {
			// mensajeError += " " + e.getLocalizedMessage() + " " + e.getMessage();
			codError = CODIGOERROR + "03";
			em.getTransaction().rollback();
			// descError = ERRORACTUALIZAR;
		} finally {

		}
		return codError;
	}
	
	public PaisModel buscarTodosPaiss(int id) {
		
		PaisModel paiss = em.find(PaisModel.class, id);
		return paiss;
	}

}
