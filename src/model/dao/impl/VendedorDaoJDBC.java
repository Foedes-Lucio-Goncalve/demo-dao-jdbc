package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

public class VendedorDaoJDBC implements VendedorDao {

	private Connection conn;
	
	public VendedorDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Vendedor obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Vendedor obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vendedor findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"select seller.*,dp.Name as depnome from seller " 
					+ " inner join department dp " 
					+ " on seller.departmentId = dp.Id "
					+ " where seller.id = ? "			
           );
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next())
			{
				Departamento dep = instanciaDepartamento(rs);			
				
				Vendedor obj =  instanciaVendedor(rs,dep);
				
				return obj;
			}
			else
			{
				return null;
			}
		}
			catch(SQLException e )
			{ 
				throw new DbException(e.getMessage());
			}
			finally {
				DB.closeStatement(st);
				DB.closeResultSet(rs);
			}
		}
	


	private Departamento instanciaDepartamento(ResultSet rs) throws SQLException {
		Departamento dep = new Departamento();
		dep.setId(rs.getInt("id"));
		dep.setName(rs.getString("depnome"));
		return dep;
	}
	private Vendedor instanciaVendedor(ResultSet rs,Departamento dep) throws SQLException {
	Vendedor obj = new Vendedor();
	obj.setId(rs.getInt("id"));
	obj.setNome(rs.getString("Name"));
    obj.setEmail(rs.getString("Email"));
    obj.setSalarioBase(rs.getDouble("BaseSalary"));
    obj.setDataAniversario(rs.getDate("BirthDate"));
    obj.setDepartamento(dep);
    return obj;
	}

	@Override
	public List<Vendedor> findall() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
