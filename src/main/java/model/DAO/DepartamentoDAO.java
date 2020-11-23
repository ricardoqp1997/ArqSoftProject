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
	private static final String PERSISTENCEUNITNAME = "SoftwareEngineeringProj";
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private static String ERRORCREAR 				= "Error al crear departamento";
	private static String ERRORACTUALIZAR 			= "Error al actualizar departamento";
	private static String ERRORELIMINAR 			= "Error al eliminar departamento";
	private static final String CODIGOERROR 		= "10";

	public DepartamentoDAO() {
		emf = Persistence.createEntityManagerFactory(PERSISTENCEUNITNAME);
		em = emf.createEntityManager();
	}

	public String Create(Departamento departamento) {
		String mensajeError = ERRORCREAR;
		String codError 	= "";

		try {
			em.getTransaction().begin();
			em.persist(departamento);
			em.getTransaction().commit();

			codError = "0000";
		} catch (EntityExistsException existEntity) {
			mensajeError += " " + existEntity.getLocalizedMessage() + " " + existEntity.getMessage();
			codError = CODIGOERROR +" 01";
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

	public String actualizarDepartamento(int idDepartamento, String nombreDepartamento) {
		String mensajeError = ERRORACTUALIZAR;
		String codError 	= "";
		try {

			em.getTransaction().begin();
			Departamento departamentomodificar = em.find(Departamento.class, idDepartamento);
			departamentomodificar.setNombreDepartamento(nombreDepartamento);
		} catch (TransactionRequiredException TransactionException) {
			mensajeError += " " +TransactionException.getLocalizedMessage() + " " + TransactionException.getMessage();
			codError = CODIGOERROR + "02";
			em.getTransaction().rollback();
			LogAuditoriaDAO.RegistroLog(codError, mensajeError);
		} catch (Exception e) {
			mensajeError 	+= " " +e.getLocalizedMessage() + " " + e.getMessage();
			codError 		= CODIGOERROR + "03";
			em.getTransaction().rollback();
			LogAuditoriaDAO.RegistroLog(codError, mensajeError);
		} finally {

			em.close();
		}
		return codError;
	}

	public List<Departamento> buscarTodosDepartamentos() {
		TypedQuery<Departamento> seleccionarDepartamentos 	= em.createNamedQuery("Departamento.findAll",
				Departamento.class);
		List<Departamento> departamentos 					= seleccionarDepartamentos.getResultList();
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
		String mensajeError = ERRORELIMINAR + "con Id: "+ idDepartamento +" ";
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
