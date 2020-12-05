package model.DAO;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;

import model.DTO.Departamento;

public class DepartamentoDAO {
	private static final String PERSISTENCEUNITNAME = "AdmisionesUN";
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private static String ERRORCREAR = " Error al crear departamento";
	private static String ERRORACTUALIZAR = " Error al actualizar departamento";
	private static String ERRORELIMINAR = " Error al eliminar departamento";
	private static final String CODIGOERROR = "DAO10";

	public DepartamentoDAO() {
		emf = Persistence.createEntityManagerFactory(PERSISTENCEUNITNAME);
		em = emf.createEntityManager();
	}

	public String crearDepartamento(Departamento departamento) {
		String mensajeError = "";
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
			codError = CODIGOERROR + "103";
			em.getTransaction().rollback();
			Util.CreateLog(codError, ERRORCREAR, mensajeError);
		}
		return codError;
	}

	public String actualizarDepartamento(int idDepartamento, String nombreDepartamento) {
		String mensajeError = "";
		String codError = "";

		try {
			if (!em.isOpen()) {
				em = emf.createEntityManager();
			}
			em.getTransaction().begin();
			Departamento departamentomodificar = em.find(Departamento.class, idDepartamento);
			departamentomodificar.setNombreDepartamento(nombreDepartamento);
			codError = "0000";
		} catch (TransactionRequiredException TransactionException) {
			mensajeError += " " + TransactionException.getLocalizedMessage() + " " + TransactionException.getMessage();
			codError = CODIGOERROR + "02";
			em.getTransaction().rollback();
			Util.CreateLog(codError, ERRORACTUALIZAR, mensajeError);
		} catch (Exception e) {
			mensajeError += " " + e.getLocalizedMessage() + " " + e.getMessage();
			codError = CODIGOERROR + "103";
			em.getTransaction().rollback();
			Util.CreateLog(codError, ERRORACTUALIZAR, mensajeError);
		}
		return codError;
	}

	public List<Departamento> buscarTodosDepartamentos() {
		String mensajeError = "";
		String codError = "";

		List<Departamento> departamentos = null;
		try {
			if (!em.isOpen()) {
				em = emf.createEntityManager();
			}
			TypedQuery<Departamento> seleccionarDepartamentos = em.createNamedQuery("Departamento.findAll",
					Departamento.class);
			departamentos = seleccionarDepartamentos.getResultList();
		} catch (Exception e) {
			mensajeError += " " + e.getLocalizedMessage() + " " + e.getMessage();
			codError = CODIGOERROR + "103";
			Util.CreateLog(codError, "Error en la consulta", mensajeError);
		}
		return departamentos;
	}

	public Departamento buscarDepartamentoId(int idDepartamento) {

		Departamento departamento = em.find(Departamento.class, idDepartamento);
		return departamento;
	}

	public Departamento buscarDepartamentoId(String nombreDepartamento) {

		Departamento departamento = em.find(Departamento.class, nombreDepartamento);
		return departamento;
	}

	public String eliminarDepartamento(int idDepartamento) {

		Departamento departamento = em.find(Departamento.class, idDepartamento);
		String codError = "";
		String mensajeError = "";

		try {
			if (!em.isOpen()) {
				em = emf.createEntityManager();
			}
			em.getTransaction().begin();
			em.remove(departamento);
			em.getTransaction().commit();
			codError = "0000";
		} catch (TransactionRequiredException TransactionException) {
			mensajeError += TransactionException.getLocalizedMessage() + " " + TransactionException.getMessage();
			codError = CODIGOERROR + "02";
			em.getTransaction().rollback();
			Util.CreateLog(codError, ERRORELIMINAR + "con id: " + idDepartamento, mensajeError);
		} catch (Exception e) {
			mensajeError += e.getLocalizedMessage() + " " + e.getMessage();
			codError = CODIGOERROR + "103";
			em.getTransaction().rollback();
			Util.CreateLog(codError, ERRORELIMINAR + "con id: " + idDepartamento, mensajeError);
		}
		return codError;
	}

}
