/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bo;

import java.util.ArrayList;
import java.sql.SQLException;
import model.dao.BookDAO;
import model.vo.AuthorVO;
import model.vo.BookVO;

/**
 *
 * @author mateusseger
 */
public class BookBO {

    private final BookVO bookVO;
    private final BookDAO bookDAO;
    private final AuthorVO authorVO;
    private final AuthorBO authorBO;
    
    public BookBO(BookVO vo) {
        this.bookVO = vo;
        this.bookDAO = new BookDAO(vo);
        this.authorVO = new AuthorVO();
        this.authorBO = new AuthorBO(authorVO);
    }

    public ArrayList<BookVO> getCatalog() throws SQLException {       
        // obtem os livros
        ArrayList<BookVO> bookList = this.getBookDAO().selectAll();
        
        // obtem os autores dos livros e retorna a lista
        return this.getAuthorBO().fillWithAuthors(bookList);
    }
    
    public BookVO getBookVO() {
        return bookVO;
    }

    public BookDAO getBookDAO() {
        return bookDAO;
    }

    public AuthorVO getAuthorVO() {
        return authorVO;
    }

    public AuthorBO getAuthorBO() {
        return authorBO;
    }
}
