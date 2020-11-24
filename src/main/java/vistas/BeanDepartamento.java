package vistas;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import controller.ControladorDepartamento;
import model.DTO.Departamento;

@ManagedBean
@ApplicationScoped
public class BeanDepartamento {
	private Departamento departamento;
	private List<SelectItem> listaDepartamentos;
	private ControladorDepartamento controladorDepartamento;

	public BeanDepartamento() {
		departamento = new Departamento();
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public List<SelectItem> getlistaDepartamentos() {
		this.listaDepartamentos = new ArrayList<SelectItem>();
		controladorDepartamento = new ControladorDepartamento();
		List<Departamento> departamentosLista = controladorDepartamento.seleccionarDepartamentos();
		this.listaDepartamentos.clear();
		for (Departamento departamentos : departamentosLista) {
			SelectItem departamentoOpcion = new SelectItem(departamentos.getDepartamentoId(),
					departamentos.getNombreDepartamento());
			this.listaDepartamentos.add(departamentoOpcion);
		}
		return this.listaDepartamentos;
	}

}
