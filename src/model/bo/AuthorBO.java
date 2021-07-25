/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import model.dao.AuthorDAO;
import model.vo.AuthorVO;
import model.vo.BookVO;

/**
 *
 * @author mateusseger
 */
public class AuthorBO {

    private final AuthorVO vo;
    private final AuthorDAO dao;
    
    public AuthorBO(AuthorVO vo) {
        this.vo = vo;
        dao = new AuthorDAO(vo);
    }
    
    public ArrayList<AuthorVO> getAuthors() throws SQLException {
        return dao.selectAll();
    }
    
    public ArrayList<BookVO> fillWithAuthors (ArrayList<BookVO> bookList) {
        return dao.fillWithAuthors(bookList);
    }   
}
