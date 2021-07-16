/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.JFrame;
import view.BookView;
import view.MainMenu;

/**
 *
 * @author mateusseger
 */
public class MainMenuCO {
    private final MainMenu view;
    
    public MainMenuCO(MainMenu view) {
        this.view = view;
    }
    
    public void registerBook() {
        System.out.println("Estou na tela de cadastro de livro");
        
        // criar um novo BookView
        BookView bookView = new BookView(new JFrame(), true);
        bookView.setVisible(true);
        
        // cria um controller para o BookView
        BookViewCO bookViewCO = new BookViewCO(bookView);
    }
    
    public void registerAuthor() {
        System.out.println("Estou na tela de cadastro de autor");
    }
    
    public void closeApp() {
        System.out.println("Fechando a aplicação");
        System.exit(0);
    }

    public MainMenu getView() {
        return view;
    }
}
