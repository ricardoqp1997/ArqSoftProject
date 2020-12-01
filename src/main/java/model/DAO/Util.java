package model.DAO;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;

import model.DTO.LogAuditoria;

public class Util {

	private static final String PERSISTENCEUNITNAME = "AdmisionesUN";
	private static final String CODIGOERROR = "UT9";
	private static final String DESCRIPCIONERROR = " Error en componente ";
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCEUNITNAME);;
	private static EntityManager em = emf.createEntityManager();
	private static final Timestamp Timestamp = new Timestamp(System.currentTimeMillis());

	public static String CreateLog(String Error, String Mensaje, String descError) {
		String mensajeError = "";
		String codError = CODIGOERROR;
		if (!em.isOpen()) {
			em = emf.createEntityManager();
		}
		try {
			LogAuditoria log = new LogAuditoria();
			log.setIdLog(Util.getMaxIDd("LogAuditoria", "idLog"));
			log.setCodError(Error);
			log.setMensajeError(Mensaje);
			log.setDescripcionMensajeError(descError);
			log.setHoraError(Timestamp);
			em.getTransaction().begin();
			em.persist(log);
			em.getTransaction().commit();
			em.close();
			codError = Error;
			mensajeError = Mensaje;
		} catch (EntityExistsException existEntity) {
			StringWriter esw = new StringWriter();
			existEntity.printStackTrace(new PrintWriter(esw));
			mensajeError = " " + existEntity.getMessage() + " " + esw.toString();
			codError += "001";

		} catch (TransactionRequiredException TransactionException) {
			mensajeError = " " + TransactionException.getMessage();
			codError += "002";

		} catch (Exception e) {
			mensajeError = " " + e.getMessage();
			codError += "003";

		}
		RegistroLog(CODIGOERROR + codError, mensajeError, descError);
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
	public static String RegistroLog(String codError, String mensajeError, String descError) {
		int maxId = 0;
		try {
			if (!em.isOpen()) {
				em = emf.createEntityManager();
			}
			try {
				Query query = em.createQuery("SELECT max(l.idLog) from LogAuditoria l");
				// Query query = em.createNamedQuery("LogAuditoria.maxId");
				maxId = (int) query.getSingleResult() + 1;
			} catch (NullPointerException e) {
				maxId++;
			}
			em.getTransaction().begin();
			em.createNativeQuery("INSERT INTO log_auditoria VALUES ( ?,?,?,?,current_timestamp)").setParameter(1, maxId)
					.setParameter(2, codError)
					.setParameter(3, mensajeError.substring(0, Math.min(mensajeError.length(), 255)))
					.setParameter(4, descError.substring(0, Math.min(descError.length(), 255))).executeUpdate();
			em.getTransaction().commit();
			em.close();
			return "RL0000";
		} catch (Exception e) {
			codError = CODIGOERROR + "999";
			StringWriter esw = new StringWriter();
			e.printStackTrace(new PrintWriter(esw));
			mensajeError = DESCRIPCIONERROR + "Auditoria: " + esw.toString();
			return codError + mensajeError;
		}
	}

	public static int getMaxIDd(String nombreTabla, String llave) {
		int maxId = 0;

		try {
			String consultaId = "SELECT max(l." + llave + ") from " + nombreTabla + " l";
			Query query = em.createQuery(consultaId);
			maxId = (int) query.getSingleResult() + 1;
		} catch (NullPointerException e) {
			maxId++;
		}
		return maxId;
	}
}
