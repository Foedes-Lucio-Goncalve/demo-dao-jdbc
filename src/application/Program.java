package application;

import java.util.Date;

import model.entities.Departamento;
import model.entities.Vendedor;

public class Program {

	public static void main(String[] args) {
		Departamento depto = new Departamento(1,"livros");
		Vendedor vendedor = new Vendedor(1,"pedro","pedro@gmail.com", new Date(),2000.0,depto);
		
		
		System.out.println(depto);
		System.out.println(vendedor);


	}

}
