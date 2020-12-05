package view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.DTO.Aspirante;

@ManagedBean
@SessionScoped
public class BeanAspirante {
	private Aspirante aspirante;
	private String documento;
	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}



	private String codigo;
	public Aspirante getAspirante() {
		return aspirante;
	}

	public void setAspirante(Aspirante aspirante) {
		this.aspirante = aspirante;
	}
	
	

	private static final String CODIGOERROR = "BED0";
	private static final String DESCRIPCIONERROR = " Error en bean Departamento ";
	private static final String MENSAJEEXITOSO = "Transaccion Exitosa ";
	private static String codError = CODIGOERROR;
	private static String descError = DESCRIPCIONERROR;
	private static String mensajeError = "";
	
	public BeanAspirante(){
		aspirante = new Aspirante();
	}
	
	

}

