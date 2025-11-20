/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog5121p3;

/**
 * Simple data-holder for registered user info.
 */
public class Registration {
    private String name;
    private String surname;
    private String username;
    private String password;
    private String cellNumber;

    public Registration() {}

    public void registerUser(String name, String surname, String username, String password, String cellNumber) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.cellNumber = cellNumber;
    }

    // Getters
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getCellNumber() { return cellNumber; }

    void collectInfo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
