package application;

import java.util.Date;

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
		System.out.println("***** teste findBYId *****");
		System.out.println("consulta vendedores: " + cvendedor);


	}

}
