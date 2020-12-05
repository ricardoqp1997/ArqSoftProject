package view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import controller.ControladorAspirante;
import model.DAO.Util;
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
	private List<Aspirante> listaAspirantes;
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

	public List<Aspirante> getListaAspirantes() {
		return listaAspirantes;
	}

	public void setListaAspirantes(List<Aspirante> listaAspirantes) {
		this.listaAspirantes = listaAspirantes;
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
		try {
			this.aspirantes = new ArrayList<SelectItem>();
			List<Aspirante> listaAspirantes = controladorAspirante.buscarTodosAspirantes();
			this.aspirantes.clear();
			for (Aspirante aspirante : listaAspirantes) {
				SelectItem aspiranteOpcion = new SelectItem(aspirante.getCedulaAspirante(),
						aspirante.getNombreAspirante() + " " + aspirante.getApellidoAspirante());
				this.aspirantes.add(aspiranteOpcion);
			}
			Util.CreateLog(codError + "00", MENSAJEEXITOSO, "Consulta Exitosa en Bean Aspirantes");
		} catch (Exception e) {
			codError += "03";
			Util.CreateLog(CODIGOERROR + codError, DESCRIPCIONERROR, e.getMessage());
		}
		return aspirantes;
	}

	public void setAspirantes(List<SelectItem> aspirantes) {
		this.aspirantes = aspirantes;
	}

	public String consultarAspirante() {
		
		if (controladorAspirante.consultarAspirante(Integer.parseInt(this.documento)).getQrcodeAspirante().toString() == codigo) {
			aspirante = controladorAspirante.consultarAspirante(Integer.parseInt(this.documento));
		}
		return "exitoso";

	}

}
