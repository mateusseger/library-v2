/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import view.MainMenu;

/**
 *
 * @author mateusseger
 */
public class Library {

    private final MainMenu mainMenu;
    
    private Library() {
        mainMenu = new MainMenu();
    }
    
    private void showMainMenu() {
        this.getMainMenu().setVisible(true);
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Library library = new Library();
        library.showMainMenu();  
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }
}
