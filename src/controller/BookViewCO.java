/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.sql.SQLException;
import model.bo.AuthorBO;
import model.bo.BookBO;
import model.vo.AuthorVO;
import model.vo.BookVO;
import view.BookView;

/**
 *
 * @author mateusseger
 */
public class BookViewCO {

    private final BookView view;
    private final BookVO bookVO;
    private final BookBO bookBO;
    private final AuthorVO authorVO;
    private final AuthorBO authorBO;
    
    public BookViewCO(BookView view) {
        this.view = view;
        bookVO = new BookVO();
        bookBO = new BookBO(bookVO);
        
        authorVO = new AuthorVO();
        authorBO = new AuthorBO(authorVO);
    }
    
    public void getAuthors() { 
        try {
            // obtem os autores
            ArrayList<AuthorVO> authorList = authorBO.getAuthors(); 
            // popula o combobox
            view.fillAuthorComboBox(authorList);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void consultCatalog() {
        try {
            // obtem os livros
            ArrayList<BookVO> bookList = bookBO.getCatalog(); 
            // obtem os autores dos livros e retorna a lista
            bookList = authorBO.fillWithAuthors(bookList);
            // popula a tabela
            view.fillBookTable(bookList);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
  
    public void deleteBook(int id, int edition) {
        bookBO.deleteBook(id, edition);
    }
    
    public void backToTheMainMenu() {
        view.setVisible(false);
    }
}
