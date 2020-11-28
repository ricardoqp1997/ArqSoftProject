package view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import controller.ControladorDepartamento;
import model.DAO.Util;
import model.DTO.Ciudad;
import model.DTO.Departamento;

@ManagedBean
@ApplicationScoped
public class BeanDepartamento {
	private Departamento departamento;
	private List<SelectItem> listaDepartamentos;
	private List<Ciudad> ciudades;
	private ControladorDepartamento controladorDepartamento;
	private static final String CODIGOERROR = "BED0";
	private static final String DESCRIPCIONERROR = " Error en bean Departamento ";
	private static final String MENSAJEEXITOSO = "Transaccion Exitosa";
	String codError = CODIGOERROR;
	String descError = DESCRIPCIONERROR;
	String mensajeError = "";

	public BeanDepartamento() {
		departamento = new Departamento();
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public List<Ciudad> getCiudades() {
		return ciudades;
	}

	public void setCiudades(List<Ciudad> ciudades) {
		this.ciudades = ciudades;
	}

	public List<SelectItem> getlistaDepartamentos() {
		try {
			this.listaDepartamentos = new ArrayList<SelectItem>();
			controladorDepartamento = new ControladorDepartamento();
			List<Departamento> departamentosLista = controladorDepartamento.seleccionarDepartamentos();
			this.listaDepartamentos.clear();
			for (Departamento departamentos : departamentosLista) {
				SelectItem departamentoOpcion = new SelectItem(departamentos.getDepartamentoId(),
						departamentos.getNombreDepartamento());
				this.listaDepartamentos.add(departamentoOpcion);
			}
			Util.CreateLog(codError, MENSAJEEXITOSO, descError);
		} catch (Exception e) {
			codError += "03";
			Util.CreateLog(codError, DESCRIPCIONERROR, e.getMessage());
			System.err.println(e.getMessage());
		}
		return this.listaDepartamentos;
	}

	public void submit() {
		this.setCiudades(this.getDepartamento().getCiudads());

	}

}
