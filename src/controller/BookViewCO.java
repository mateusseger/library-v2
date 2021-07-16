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
    
    public BookViewCO(BookView view) {
        this.view = view;
        this.bookVO = new BookVO();
        this.bookBO = new BookBO(bookVO);
    }
    
    public void consultCatalog() {
        System.out.println("Estou na consulta de catalogo");
        
        // obtem lista de livros
        try {
            ArrayList<BookVO> bookList = this.getBookBO().getCatalog();  
            // popula a tabela
            view.viewJTable(bookList);
        } catch(SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void backToTheMainMenu() {
        this.getView().setVisible(false);
    }

    public BookView getView() {
        return view;
    }

    public BookVO getBookVO() {
        return bookVO;
    }

    public BookBO getBookBO() {
        return bookBO;
    }
}
