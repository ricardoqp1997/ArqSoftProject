package view;

import java.util.List;

import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import controller.ControladorAspirante;
import model.DTO.Aspirante;

@ManagedBean
@SessionScoped
public class BeanAspirante {
	private static final String CODIGOERROR = "BEA0";
	private static final String DESCRIPCIONERROR = " Error en bean Aspirante ";
	private static final String MENSAJEEXITOSO = "Transaccion Exitosa ";
	private static String codError = CODIGOERROR;
	private static String descError = DESCRIPCIONERROR;
	private static String mensajeError = "";

	private Aspirante aspirante;
	private List<SelectItem> aspirantes;
	private String documento;
	private String codigo;

	private ControladorAspirante controladorAspirante;

	public BeanAspirante() {
		controladorAspirante = new ControladorAspirante();
		aspirante = new Aspirante();
	}

	public Aspirante getAspirante() {
		return aspirante;
	}

	public void setAspirante(Aspirante aspirante) {
		this.aspirante = aspirante;
	}

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

	public List<SelectItem> getAspirantes() {
		return aspirantes;
	}

	public void setAspirantes(List<SelectItem> aspirantes) {
		this.aspirantes = aspirantes;
	}

	public void consultarAspirante() {
		aspirante = controladorAspirante.consultarAspirante(Integer.parseInt(this.documento));

	}

}
