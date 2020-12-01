package view;

import java.util.ArrayList;
import java.util.LinkedList;
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
	private LinkedList<Ciudad> ciudades;
	private ControladorDepartamento controladorDepartamento;
	private static final String CODIGOERROR = "BED0";
	private static final String DESCRIPCIONERROR = " Error en bean Departamento ";
	private static final String MENSAJEEXITOSO = "Transaccion Exitosa ";
	String codError = CODIGOERROR;
	String descError = DESCRIPCIONERROR;
	String mensajeError = "";

	public BeanDepartamento() {
		departamento = new Departamento();
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public LinkedList<Ciudad> getCiudades() {
		return ciudades;
	}

	public void setCiudades(LinkedList<Ciudad> ciudades) {
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
			Util.CreateLog(codError + "00", MENSAJEEXITOSO, "Consulta Exitosa en Bean Departamento");
		} catch (Exception e) {
			codError += "03";
			Util.CreateLog(CODIGOERROR + codError, DESCRIPCIONERROR, e.getMessage());
		}
		return this.listaDepartamentos;
	}

	public String submit() {
		try {
			for (Ciudad c : this.getDepartamento().getCiudads()) {
				ciudades.add(c);
				System.out.println(c.getNombreCiudad());
			}
			
			codError = "0000";
			mensajeError = "Transacción Exitosa en Submit";
			descError = "Transacción exitosa";
		} catch (Exception e) {
			codError = "0000";
			mensajeError = "Error en submit " + e.getMessage();
		} finally {
			Util.CreateLog(CODIGOERROR + codError, descError, mensajeError);
		}
		return "hola";

	}

}
