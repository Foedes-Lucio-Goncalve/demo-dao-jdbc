package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"insert into seller"
				+ "(Name,Email,BirthDate,BaseSalary,DepartmentId) "
				+ " values " 
				+ "(?,?,?,?,?)",
				Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getNome());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getDataAniversario().getTime()));
			st.setDouble(4, obj.getSalarioBase());
			st.setInt(5, obj.getDepartamento().getId());	
			
			int linhasAfetadas = st.executeUpdate();
		
			 if (linhasAfetadas > 0) {
				 ResultSet rs = st.getGeneratedKeys();
				 if(rs.next()) {
					 int id = rs.getInt(1);
					 obj.setId(id);
					 DB.closeResultSet(rs);
				 }					 
			 }
			 else {
				 throw new DbException("erro inesprerado. nenhuma linha incluida ... " );
			 }
		}
		catch(SQLException e )
		{ 
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			
		}
		
		
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
		dep.setId(rs.getInt("departmentId"));
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
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"select seller.*,dp.Name as depnome from seller " 
					+ " inner join department dp " 
					+ " on seller.departmentId = dp.Id "
					+ "ORDER BY Name"
           );
								
			rs = st.executeQuery();
			
			List<Vendedor> lista = new ArrayList<>();
			Map<Integer,Departamento> map = new HashMap<>();
			
			while(rs.next())
			{
				Departamento dep = map.get(rs.getInt("id"));
				
				if(dep == null)
				{
				 dep = instanciaDepartamento(rs);
					map.put(rs.getInt("id"), dep);
				}
											
				Vendedor obj =  instanciaVendedor(rs,dep);
				lista.add(obj);				
			}
			return lista;
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
	

	@Override
	public List<Vendedor> findByDepartamento(Departamento departamento) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"select seller.*,dp.Name as depnome from seller " 
					+ " inner join department dp " 
					+ " on seller.departmentId = dp.Id "
					+ " where departmentId = ? "	
					+ "ORDER BY Name"
           );
			st.setInt(1, departamento.getId());
			//st.setInt(1, 2 );
			
			rs = st.executeQuery();
			
			List<Vendedor> lista = new ArrayList<>();
			Map<Integer,Departamento> map = new HashMap<>();
			
			while(rs.next())
			{
				Departamento dep = map.get(rs.getInt("id"));
				
				if(dep == null)
				{
				 dep = instanciaDepartamento(rs);
					map.put(rs.getInt("id"), dep);
				}
											
				Vendedor obj =  instanciaVendedor(rs,dep);
				lista.add(obj);				
			}
			return lista;
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
	
}
