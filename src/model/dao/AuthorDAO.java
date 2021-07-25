/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.vo.AuthorVO;
import model.vo.BookVO;

/**
 *
 * @author mateusseger
 */
public class AuthorDAO {
    private final AuthorVO vo;
    
    public AuthorDAO(AuthorVO vo) {
        this.vo = vo;
    }
    
    public ArrayList<AuthorVO>  selectAll() {
        String sql = "select * from Author order by name";
        
        ArrayList<AuthorVO> authorList = new ArrayList<AuthorVO>();
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.execute();
            rs = stmt.getResultSet();
            
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                
                AuthorVO author = new AuthorVO(id, name);
                authorList.add(author);
            }
        } catch(SQLException ex) {
            throw new RuntimeException("Erro na conexão: ", ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return authorList;
    }

    public ArrayList<BookVO> fillWithAuthors (ArrayList<BookVO> bookList) {
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        for(BookVO book : bookList) {
            ArrayList<AuthorVO> authorList = new ArrayList<AuthorVO>();
            
            
            String sql = "select a.id, a.name"
                       + "  from Author a inner join book_author ba"
                       + "    on a.id = ba.fk_author_id"
                       + " where ba.fk_book_id= ?";
            try {
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, book.getId());
                stmt.execute();
                rs = stmt.getResultSet();

                while(rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");

                    AuthorVO author = new AuthorVO(id, name);
                    authorList.add(author);
                }
                
                book.setAuthors(authorList);
            } catch(SQLException ex) {
                throw new RuntimeException("Erro na conexão: ", ex);
            }
        }
        ConnectionFactory.closeConnection(con, stmt, rs);
        return bookList;
    }
}
