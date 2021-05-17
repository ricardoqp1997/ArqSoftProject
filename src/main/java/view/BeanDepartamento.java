package view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import controller.ControladorDepartamento;
import model.DAO.DepartamentoDAO;
import model.DTO.CiudadModel;
import model.DTO.DepartamentoModel;

@ManagedBean
@ApplicationScoped
public class BeanDepartamento {
	private DepartamentoModel departamento;
	private String nombre;
	private List<SelectItem> listaDepartamentos;
	private List<CiudadModel> ciudades;
	private ControladorDepartamento controladorDepartamento;
	private static final String CODIGOERROR = "BED0";
	private static final String DESCRIPCIONERROR = " Error en bean Departamento ";
	private static final String MENSAJEEXITOSO = "Transaccion Exitosa ";
	private static String codError = CODIGOERROR;
	private static String descError = DESCRIPCIONERROR;
	private static String mensajeError = "";

	public BeanDepartamento() {
		departamento = new DepartamentoModel();
		ciudades = new ArrayList<CiudadModel>();
		controladorDepartamento = new ControladorDepartamento();
	}

	public DepartamentoModel getDepartamento() {
		return departamento;
	}

	public void setDepartamento(DepartamentoModel departamento) {
		this.departamento = departamento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<CiudadModel> getCiudades() {
		return ciudades;
	}

	public void setCiudades(List<CiudadModel> ciudades) {
		this.ciudades = ciudades;
	}

	public List<DepartamentoModel> obtenerDepartamento(){
		DepartamentoDAO departamentoDAO= new DepartamentoDAO();
		
		
		
		return departamentoDAO.buscarTodosDepartamentos();
	}
	
	public List<SelectItem> getListaDepartamentos() {
		try {
			this.listaDepartamentos = new ArrayList<SelectItem>();
			List<DepartamentoModel> departamentosLista = controladorDepartamento.seleccionarDepartamentos();
			this.listaDepartamentos.clear();
			for (DepartamentoModel departamentos : departamentosLista) {
				SelectItem departamentoOpcion = new SelectItem(departamentos.getId(),
						departamentos.getNombreDepartamento());
				this.listaDepartamentos.add(departamentoOpcion);
			}
			// Util.CreateLog(codError + "00", MENSAJEEXITOSO, "Consulta Exitosa en Bean Departamento");
		} catch (Exception e) {
			codError += "03";
			// Util.CreateLog(CODIGOERROR + codError, DESCRIPCIONERROR, e.getMessage());
		}
		return listaDepartamentos;
	}

	public String consultaCiudadesDepartamento() {
		try {

			departamento = controladorDepartamento.seleccionarDepartamentoId(departamento.getId());
			ciudades.clear();
			ciudades = departamento.getCiudads();
			codError = "0000";
			mensajeError = "Transacción Exitosa en Submit";
			descError = "Transacción exitosa";
		} catch (Exception e) {
			codError = "03";
			mensajeError = "Error en submit " + e.getMessage();
		} finally {
			// Util.CreateLog(CODIGOERROR + codError, descError, mensajeError);

		}
		return codError;

	}

}
