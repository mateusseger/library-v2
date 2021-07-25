/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bo;

import java.util.ArrayList;
import java.sql.SQLException;
import model.dao.BookDAO;
import model.vo.BookVO;

/**
 *
 * @author mateusseger
 */
public class BookBO {

    private final BookVO vo;
    private final BookDAO dao;
    
    public BookBO(BookVO vo) {
        this.vo = vo;
        dao = new BookDAO(vo);
    }

    public ArrayList<BookVO> getCatalog() throws SQLException {       
        return dao.selectAll();       
    }
    
    public void deleteBook(int id, int edition) {
        dao.deleteEdition(id, edition);
        // valida se o livro se encontra sem edicao, caso verdadeiro realiza a exclusão do livro e do vinculo com autor(es)
        if (dao.invalidBook(id)) {
            dao.deleteBook(id);
        }
    }
}
