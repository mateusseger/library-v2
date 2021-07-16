/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bo;

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
        this.dao = new AuthorDAO(vo);
    }
    
    public ArrayList<BookVO> fillWithAuthors (ArrayList<BookVO> bookList) {
        return this.getDao().fillWithAuthors(bookList);
    }

    public AuthorVO getVo() {
        return vo;
    }

    public AuthorDAO getDao() {
        return dao;
    }
}
