package model.DAO;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;

import model.DTO.DepartamentoModel;

public class DepartamentoDAO {
	private static final String PERSISTENCEUNITNAME = "TemperaturaSoftware";
	private static EntityManagerFactory emf;
	private static EntityManager em;
	// private static String ERRORCREAR = " Error al crear departamento";
	// private static String ERRORACTUALIZAR = " Error al actualizar departamento";
	// private static String ERRORELIMINAR = " Error al eliminar departamento";
	private static final String CODIGOERROR = "DAO10";

	public DepartamentoDAO() {
		emf = Persistence.createEntityManagerFactory(PERSISTENCEUNITNAME);
		em = emf.createEntityManager();
	}

	public String crearDepartamento(DepartamentoModel departamento) {
		// String mensajeError = "";
		String codError = "";

		try {
			if (!em.isOpen()) {
				em = emf.createEntityManager();
			}
			em.getTransaction().begin();
			em.persist(departamento);
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
			codError = CODIGOERROR + "103";
			em.getTransaction().rollback();
			// Util.CreateLog(codError, ERRORCREAR, mensajeError);
		}
		return codError;
	}

	public String actualizarDepartamento(int id, String nombreDepartamento) {
		// String mensajeError = "";
		String codError = "";

		try {
			if (!em.isOpen()) {
				em = emf.createEntityManager();
			}
			em.getTransaction().begin();
			DepartamentoModel departamentomodificar = em.find(DepartamentoModel.class, id);
			departamentomodificar.setNombreDepartamento(nombreDepartamento);
			codError = "0000";
		} catch (TransactionRequiredException TransactionException) {
			// mensajeError += " " + TransactionException.getLocalizedMessage() + " " + TransactionException.getMessage();
			codError = CODIGOERROR + "02";
			em.getTransaction().rollback();
			// Util.CreateLog(codError, ERRORACTUALIZAR, mensajeError);
		} catch (Exception e) {
			// mensajeError += " " + e.getLocalizedMessage() + " " + e.getMessage();
			codError = CODIGOERROR + "103";
			em.getTransaction().rollback();
			// Util.CreateLog(codError, ERRORACTUALIZAR, mensajeError);
		}
		return codError;
	}

	public List<DepartamentoModel> buscarTodosDepartamentos() {
		// String mensajeError = "";
		// String codError = "";

		List<DepartamentoModel> departamentos = null;
		try {
			if (!em.isOpen()) {
				em = emf.createEntityManager();
			}
			TypedQuery<DepartamentoModel> seleccionarDepartamentos = em.createNamedQuery("DepartamentoModel.findAll",
					DepartamentoModel.class);
			departamentos = seleccionarDepartamentos.getResultList();
		} catch (Exception e) {
			// mensajeError += " " + e.getLocalizedMessage() + " " + e.getMessage();
			// codError = CODIGOERROR + "103";
			// Util.CreateLog(codError, "Error en la consulta", mensajeError);
		}
		return departamentos;
	}

	public DepartamentoModel buscarDepartamentoId(int id) {

		DepartamentoModel departamento = em.find(DepartamentoModel.class, id);
		return departamento;
	}

	public DepartamentoModel buscarDepartamentoId(String nombreDepartamento) {

		DepartamentoModel departamento = em.find(DepartamentoModel.class, nombreDepartamento);
		return departamento;
	}

	public String eliminarDepartamento(int id) {

		DepartamentoModel departamento = em.find(DepartamentoModel.class, id);
		String codError = "";
		// String mensajeError = "";

		try {
			if (!em.isOpen()) {
				em = emf.createEntityManager();
			}
			em.getTransaction().begin();
			em.remove(departamento);
			em.getTransaction().commit();
			codError = "0000";
		} catch (TransactionRequiredException TransactionException) {
			// mensajeError += TransactionException.getLocalizedMessage() + " " + TransactionException.getMessage();
			codError = CODIGOERROR + "02";
			em.getTransaction().rollback();
			// Util.CreateLog(codError, ERRORELIMINAR + "con id: " + id, mensajeError);
		} catch (Exception e) {
			// mensajeError += e.getLocalizedMessage() + " " + e.getMessage();
			codError = CODIGOERROR + "103";
			em.getTransaction().rollback();
			// Util.CreateLog(codError, ERRORELIMINAR + "con id: " + id, mensajeError);
		}
		return codError;
	}

}
