package view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.DTO.Aspirante;

@ManagedBean
@SessionScoped
public class BeanAspirante {
	private Aspirante aspirante;
	private static final String CODIGOERROR = "BED0";
	private static final String DESCRIPCIONERROR = " Error en bean Departamento ";
	private static final String MENSAJEEXITOSO = "Transaccion Exitosa ";
	private static String codError = CODIGOERROR;
	private static String descError = DESCRIPCIONERROR;
	private static String mensajeError = "";

}
