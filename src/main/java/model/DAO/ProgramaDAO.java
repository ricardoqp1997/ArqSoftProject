package model.DAO;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;

import model.DTO.Aspirante;
import model.DTO.Programa;

public class ProgramaDAO {
	private static final String PERSISTENCEUNITNAME = "SoftwareEngineeringProj";
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private static String ERRORCREAR = "Error al crear programa";
	private static String ERRORACTUALIZAR = "Error al actualizar programa";
	private static String ERRORELIMINAR = "Error al eliminar programa";
	private static final String CODIGOERROR = "50";

	public ProgramaDAO() {
		emf = Persistence.createEntityManagerFactory(PERSISTENCEUNITNAME);
		em = emf.createEntityManager();
	}

	public String Create(Programa ciudad) {
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

	public String actualizarProgramaNombre(Integer idPrograma, List<Aspirante> aspirantes, String nombrePrograma) {
		String mensajeError = ERRORACTUALIZAR;
		String codError = "";
		try {

			em.getTransaction().begin();
			Programa programamodificar = em.find(Programa.class, idPrograma);
			programamodificar.setAspirantes(aspirantes == null ? programamodificar.getAspirantes() : aspirantes);
			programamodificar.setNombrePrograma(nombrePrograma == null ? programamodificar.getNombrePrograma() : nombrePrograma);

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

	public List<Programa> buscarTodosProgramas() {
		TypedQuery<Programa> seleccionarPrograma = em.createNamedQuery("Programa.findAll", Programa.class);
		List<Programa> programas = seleccionarPrograma.getResultList();
		return programas;
	}

	public Programa buscarProgramaId(int idPrograma) {
		Programa programa = em.find(Programa.class, idPrograma);
		return programa;
	}

	public Programa buscarProgramaId(String nombrePrograma) {
		Programa programa = em.find(Programa.class, nombrePrograma);
		return programa;
	}

	public String eliminarPrograma(int idPrograma) {

		Programa programa = em.find(Programa.class, idPrograma);
		String codError = "";
		String mensajeError = ERRORELIMINAR + "con id: " + idPrograma + " ";
		try {
			em.getTransaction().begin();
			em.remove(programa);
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
