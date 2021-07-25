/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.vo.BookVO;

/**
 *
 * @author mateusseger
 */
public class BookDAO {

    private BookVO vo;
    
    public BookDAO(BookVO vo) {
        this.vo = vo;
    }
    
    public ArrayList<BookVO> selectAll() {
        String sql = "select b.id, b.title, e.number, e.year"
                   + "  from Book b inner join Edition e"
                   + "    on b.id = e.fk_book_id"
                   + "    order by b.title, e.number desc";
        
        ArrayList<BookVO> bookList = new ArrayList<BookVO>();
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.execute();
            rs = stmt.getResultSet();
            
            while(rs.next()) {
                int id = rs.getInt("id");
        	String title = rs.getString("title");
        	int edition = rs.getInt("number");
                int year = rs.getInt("year");
                
        	BookVO book = new BookVO(id, title, edition, year);
        	bookList.add(book);
            }
        } catch(SQLException ex) {
            throw new RuntimeException("Erro na conexão: ", ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return bookList;
    }
    
    public void deleteEdition(int id, int edition) {
        String sql = "delete from Edition where fk_book_id = ? and number = ?";
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setInt(2, edition);
            
            stmt.execute();
        } catch(SQLException ex) {
            throw new RuntimeException("Erro na conexão: ", ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public boolean invalidBook(int id) {
        String sql = "select * from Book b inner join Edition e on b.id = e.fk_book_id where b.id = ?";
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean invalidBook = false;
                
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            
            stmt.execute();
            rs = stmt.getResultSet();
            
            invalidBook = !rs.next();
            
        } catch(SQLException ex) {
            throw new RuntimeException("Erro na conexão: ", ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
            return invalidBook;
        }
    }
    
    public void deleteBook(int id) {
        // deleta relacionamento com autor
        deleteBookAuthor(id);
        
        // deleta livro
        String sql = "delete from Book b where b.id = ?";
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
                
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            
            stmt.execute();
            
        } catch(SQLException ex) {
            throw new RuntimeException("Erro na conexão: ", ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void deleteBookAuthor(int id) {
        String sql = "delete from Book_Author ba where ba.fk_book_id = ?";
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
                
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            
            stmt.execute();
            
        } catch(SQLException ex) {
            throw new RuntimeException("Erro na conexão: ", ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
