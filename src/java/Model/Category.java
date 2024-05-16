/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author utilisateur
 */
public class Category {
    private int catCode;
    private String catName;

    public Category() {
        // Initialize any default values if needed
    }
    public Category(String catName) {
        this.catName = catName;
    }
    

    public int getCatCode() {
        return catCode;
    }

    public void setCatCode(int catCode) {
        this.catCode = catCode;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}
