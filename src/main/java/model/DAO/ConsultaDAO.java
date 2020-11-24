package model.DAO;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;

import model.DTO.Consulta;

public class ConsultaDAO {
	private static final String PERSISTENCEUNITNAME = "AdmisionesUN";
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private static String ERRORCREAR = "Error al crear consulta";
	private static final String CODIGOERROR = "DAO40";

	public ConsultaDAO() {
		emf = Persistence.createEntityManagerFactory(PERSISTENCEUNITNAME);
		em = emf.createEntityManager();
	}

	public String Create(Consulta consulta) {
		String mensajeError = "";
		String codError = "";

		try {
			em.getTransaction().begin();
			em.persist(consulta);
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

	public List<Consulta> buscarTodosConsultas() {
		TypedQuery<Consulta> seleccionarConsulta = em.createNamedQuery("Consulta.findAll", Consulta.class);
		List<Consulta> consultas = seleccionarConsulta.getResultList();
		return consultas;
	}

	public Consulta buscarConsultaId(int idConsulta) {
		Consulta consulta = em.find(Consulta.class, idConsulta);
		return consulta;
	}

	public Consulta buscarConsultaId(Timestamp fechaConsulta) {
		Consulta consulta = em.find(Consulta.class, fechaConsulta);
		return consulta;
	}
}
