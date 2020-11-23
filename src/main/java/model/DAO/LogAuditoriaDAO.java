package model.DAO;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;

import model.DTO.LogAuditoria;

public class LogAuditoriaDAO {

	private static final String PERSISTENCEUNITNAME = "SoftwareEngineeringProj";
	private static EntityManagerFactory emf;
	private static EntityManager em;

	public LogAuditoriaDAO() {
		emf = Persistence.createEntityManagerFactory(PERSISTENCEUNITNAME);
		em = emf.createEntityManager();
	}

	public static String Create(LogAuditoria auditoria) {
		String mensajeError = "";
		String codError = "";
		try {
			em.getTransaction().begin();
			em.persist(auditoria);
			em.getTransaction().commit();
			em.close();
			codError = "00";
		} catch (EntityExistsException existEntity) {
			StringWriter esw = new StringWriter();
			existEntity.printStackTrace(new PrintWriter(esw));
			mensajeError = existEntity.getLocalizedMessage() + " " + existEntity.getMessage() + " " + esw.toString();
			codError = "01";
			RegistroLog(codError, mensajeError);
		} catch (TransactionRequiredException TransactionException) {
			mensajeError = TransactionException.getLocalizedMessage() + " " + TransactionException.getMessage();
			codError = "02";
			RegistroLog(codError, mensajeError);
		} catch (Exception e) {
			mensajeError = e.getLocalizedMessage() + " " + e.getMessage();
			codError = "03";
			RegistroLog(codError, mensajeError);
		}
		return codError;
	}

	/*
	 * private Properties leerPropiedades() { FileReader fr; try { String
	 * currenteRelativePath = Paths.get("").toAbsolutePath().toString() +
	 * "\\src\\main\\java\\model\\DAO\\"; fr = new FileReader(currenteRelativePath +
	 * "db.properties"); Properties properties = new Properties();
	 * properties.load(fr); return properties; } catch (FileNotFoundException e) {
	 * System.out.println("No se puede encontrar el archivo"); return null; } catch
	 * (IOException e) { // TODO Auto-generated catch block
	 * 
	 * return null; } }
	 */
	public static String RegistroLog(String codError, String mensajeError) {
		int maxId = 0;
		try {
			Query query = em.createQuery("SELECT max(l.idLog) from LogAuditoria l");
			try {
				maxId = (int) query.getSingleResult() + 1;
			} catch (NullPointerException e) {
				maxId++;
			}
			LogAuditoria lg = new LogAuditoria();
			lg.setIdLog(maxId);
			lg.setCodError(codError);
			lg.setMensajeError(mensajeError);
			em.getTransaction().begin();
			em.createNativeQuery("INSERT INTO log_auditoria VALUES ( ?,?,?,current_timestamp)")
					.setParameter(1, lg.getIdLog()).setParameter(2, lg.getCodError())
					.setParameter(3, lg.getMensajeError()).executeUpdate();
			em.getTransaction().commit();
			em.close();
			return "00";
		} catch (Exception e) {
			StringWriter esw = new StringWriter();
			e.printStackTrace(new PrintWriter(esw));
			return "999 " + esw.toString();
		}
	}
}
