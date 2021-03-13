package model.DAO;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;

import model.DTO.TemperaturaHumedadModel;

public class TemperaturaHumedadDAO {
	private static final String PERSISTENCEUNITNAME = "TemperaturaSoftware";
	private static EntityManagerFactory emf;
	private static EntityManager em;
	// private static String ERRORCREAR = "Error al crear ciudad";
	// private static String ERRORACTUALIZAR = "Error al actualizar ciudad";
	// private static String ERRORELIMINAR = "Error al eliminar ciudad";
	private static final String CODIGOERROR = "DAO20";
	// private static final String MENSJAEEXITOSO = "Transaccion Exitosa";
	
	public TemperaturaHumedadDAO() {
		emf = Persistence.createEntityManagerFactory(PERSISTENCEUNITNAME);
		em = emf.createEntityManager();
	}
	
	public String Create(TemperaturaHumedadDAO temperatura) {
		// String mensajeError = "";
		String codError = "";
		// String descError = "";

		try {
			em.getTransaction().begin();
			em.persist(temperatura);
			em.getTransaction().commit();

			codError = "0000";
			// descError = MENSJAEEXITOSO;
		} catch (EntityExistsException existEntity) {
			// mensajeError += " " + existEntity.getLocalizedMessage() + " " + existEntity.getMessage();
			codError = CODIGOERROR + " 01";
			// descError = ERRORCREAR;
			em.getTransaction().rollback();

		} catch (TransactionRequiredException TransactionException) {
			// mensajeError += " " + TransactionException.getLocalizedMessage() + " " + TransactionException.getMessage();
			codError = CODIGOERROR + "02";
			// descError = ERRORCREAR;
			em.getTransaction().rollback();

		} catch (Exception e) {
			// descError = ERRORCREAR;
			// mensajeError += " " + e.getLocalizedMessage() + " " + e.getMessage();
			codError = CODIGOERROR + "03";
			em.getTransaction().rollback();

		} finally {
			// Util.CreateLog(codError, descError, mensajeError);

		}
		return codError;
	}

	public String actualizarTemperatura(TemperaturaHumedadModel temperatura) {
		// String mensajeError = "";
		String codError = "";
		// String descError = "";
		try {

			em.getTransaction().begin();
			em.merge(temperatura);
			codError = "0000";
			// descError = MENSJAEEXITOSO;
		} catch (TransactionRequiredException TransactionException) {
			// mensajeError += " " + TransactionException.getLocalizedMessage() + " " + TransactionException.getMessage();
			codError = CODIGOERROR + "02";
			em.getTransaction().rollback();
			// descError = ERRORACTUALIZAR;
		} catch (Exception e) {
			// mensajeError += " " + e.getLocalizedMessage() + " " + e.getMessage();
			codError = CODIGOERROR + "03";
			em.getTransaction().rollback();
			// descError = ERRORACTUALIZAR;
		} finally {
			// Util.CreateLog(codError, descError, mensajeError);

		}
		return codError;
	}

	public List<TemperaturaHumedadModel> buscarTodasTemperaturas() {
		TypedQuery<TemperaturaHumedadModel> seleccionarTemperatura = em.createNamedQuery("TemperaturaHumedadModel.findAll", TemperaturaHumedadModel.class);
		List<TemperaturaHumedadModel> temperaturas = seleccionarTemperatura.getResultList();
		return temperaturas;
	}

	public TemperaturaHumedadModel buscarTemperaturaId(int id) {
		TemperaturaHumedadModel temperatura = em.find(TemperaturaHumedadModel.class, id);
		return temperatura;
	}

	public TemperaturaHumedadModel buscarTemperaturaId(String ciudadId) {
		TemperaturaHumedadModel temperatura = em.find(TemperaturaHumedadModel.class, ciudadId);
		return temperatura;
	}

	public String eliminarTemperatura(int id) {

		TemperaturaHumedadModel ciudad = em.find(TemperaturaHumedadModel.class, id);
		// String mensajeError = "";
		String codError = "";
		// String descError = "";
		try {
			em.getTransaction().begin();
			em.remove(ciudad);
			em.getTransaction().commit();
			// descError = MENSJAEEXITOSO;
			codError = "0000";
		} catch (TransactionRequiredException TransactionException) {
			// mensajeError += TransactionException.getLocalizedMessage() + " " + TransactionException.getMessage();
			codError = CODIGOERROR + "02";
			// descError = ERRORELIMINAR;
			em.getTransaction().rollback();

		} catch (Exception e) {
			// mensajeError += e.getLocalizedMessage() + " " + e.getMessage();
			codError = CODIGOERROR + "03";
			// descError = ERRORELIMINAR;
			em.getTransaction().rollback();

		} finally {

		}
		return codError;
	}
}