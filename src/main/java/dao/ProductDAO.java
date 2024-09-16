package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.Connector;
import jdbc.Queries;
import table.Product;

public class ProductDAO implements Dao<Product>{
	
	private Connection conn;
	
	public ProductDAO() throws SQLException {
		super();
		this.conn = Connector.getInstance().initConn();
	}

	@Override
	public Product add(Product prod) throws IOException {
		
		String insertQuery = Queries.getFromFile("PREPARE_INSERT_PRODUCT");
		
		PreparedStatement pstmt = null;
		
		try {
			
			pstmt = this.conn.prepareStatement(insertQuery);
			
		    pstmt.setString(1, prod.getName());
		    pstmt.setDate(2, prod.getExpdate());
		    pstmt.setString(3, prod.getDescription());
			
			pstmt.execute();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			Connector.closeStatement(pstmt);
		}
		
		return prod;
	}

	@Override
	public List<Product> findAll() throws IOException {
		
		List<Product> prodList = new ArrayList<>();
		
        String query = Queries.getFromFile("SELECT_ALL_PRODUCTS");
        
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = this.conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                Product prod = new Product(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getDate("expdate"),
                        rs.getString("description")
       
                );
                prodList.add(prod);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	Connector.closeResultSet(rs);
            Connector.closeStatement(stmt);
        }

        return prodList;
	}

	@Override
	public Product findById(int id) throws IOException {
		
		String query = Queries.getFromFile("SELECT_BY_ID");
		
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            pstmt = this.conn.prepareStatement(query);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
            	Product prod = new Product(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getDate("expdate"),
                        rs.getString("description")
       
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	Connector.closeResultSet(rs);
            Connector.closeStatement(pstmt);
        }
        return null;
	}

	@Override
	public void update(int id, Product prod) throws IOException {
		
		String updateQuery = Queries.getFromFile("UPDATE_PRODUCT");
		
		PreparedStatement pstmt = null;
		try {
			pstmt = this.conn.prepareStatement(updateQuery);
			
			pstmt.setLong(1, id);
			pstmt.setString(2, prod.getName());
		    pstmt.setDate(3, prod.getExpdate());
		    pstmt.setString(4, prod.getDescription());
		
			pstmt.execute();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connector.closeStatement(pstmt);
		}
		
	}

	@Override
	public void delete(int id) throws IOException {
		
		String deleteQuery = Queries.getFromFile("DELETE_BY_ID");
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = this.conn.prepareStatement(deleteQuery);
			pstmt.executeQuery();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connector.closeStatement(pstmt);
		}
		
	}

	@Override
	public void deleteAll() throws IOException {
		
		String deleteQuery = Queries.getFromFile("DELETE_ALL");
		
		Statement stmt = null;
		try {
			stmt = this.conn.createStatement();
			stmt.execute(deleteQuery);
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			Connector.closeStatement(stmt);
		}
	}
}
