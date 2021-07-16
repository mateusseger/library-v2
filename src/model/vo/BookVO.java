/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.vo;

import java.util.ArrayList;

/**
 *
 * @author mateusseger
 */
public class BookVO {

    private int id;
    private String title;
    private ArrayList<AuthorVO> authors;
    private int edition;
    private int year;
    
    public BookVO() {
    }
    
    public BookVO(int id, String title, int edition, int year) {
        this.id = id;
        this.title = title;
        this.edition = edition;
        this.year = year;
    }
    
    public BookVO(int id, String title, ArrayList<AuthorVO> authors, int edition, int year) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.edition = edition;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<AuthorVO> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<AuthorVO> authors) {
        this.authors = authors;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
