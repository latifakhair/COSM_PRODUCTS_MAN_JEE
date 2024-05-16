/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Model;


import java.util.Date;

public class Product {
    private int itCode;
    private String itName;
    private int quantity;
    private Date date;
    private int price;
    private int category;

    // Constructors
    public Product() {
    }

    public Product(int itCode, String itName, int quantity, Date date, int price, int category) {
        this.itCode = itCode;
        this.itName = itName;
        this.quantity = quantity;
        this.date = date;
        this.price = price;
        this.category = category;
    }

    // Getters and setters
    public int getItCode() {
        return itCode;
    }

    public void setItCode(int itCode) {
        this.itCode = itCode;
    }

    public String getItName() {
        return itName;
    }

    public void setItName(String itName) {
        this.itName = itName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "Product{" +
                "itCode=" + itCode +
                ", itName='" + itName + '\'' +
                ", quantity=" + quantity +
                ", date=" + date +
                ", price=" + price +
                ", category=" + category +
                '}';
    }
}