package application;

import java.util.Date;
import java.util.List;

import model.dao.FabricaDao;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

public class Program {

	public static void main(String[] args) {
		Departamento depto = new Departamento(1,"livros");
		Vendedor vendedor = new Vendedor(1,"pedro","pedro@gmail.com", new Date(),2000.0,depto);
		
		VendedorDao vendedordao = FabricaDao.createVendedorDao();
		Vendedor cvendedor = vendedordao.findById(1);
		
		
		//System.out.println(depto);
		//System.out.println(vendedor);
		System.out.println("***** teste1  findBYId *****");
		System.out.println("consulta vendedores: " + cvendedor);
		
		System.out.println("***** teste2  findBYId *****");
		Departamento deps = new Departamento(2, null);
		List<Vendedor> list =  vendedordao.findByDepartamento(deps);
		for(Vendedor obj: list)
		{
			System.out.println(obj);
		}


	}

}
