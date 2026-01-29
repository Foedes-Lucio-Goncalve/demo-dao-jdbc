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

		System.out.println("***** teste3  findall *****");
		
		List<Vendedor> lista =  vendedordao.findall();
		for(Vendedor obj: lista)
		{
			System.out.println(obj);
		}

		System.out.println("***** teste4  insertl *****");
		Vendedor novoVendedor = new Vendedor(null,"jose","jose@hotmail.com",new Date(),3000.0,deps);
		vendedordao.insert(novoVendedor);
		System.out.println("registro incluido" + novoVendedor + " novo id.gerado.. "+ novoVendedor.getId());
		System.out.println("***** teste5  update *****");
		 novoVendedor = vendedordao.findById(1);
		 System.out.println(novoVendedor);
		 novoVendedor.setNome("maria jose");
		 System.out.println("nome alterado" + novoVendedor);
		 vendedordao.update(novoVendedor);
		 System.out.println("update completado");
	}

}
