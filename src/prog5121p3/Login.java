/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog5121p3;

import java.util.regex.Pattern;


public class Login {

    private Registration registration;

    public Login(Registration registration) {
        this.registration = registration;
    }

    // Boolean: checkUserName()
    // username contains underscore and is no more than five characters long
    public boolean checkUserName() {
        String username = registration.getUsername();
        if (username == null) return false;
        return username.contains("_") && username.length() <= 5;
    }

    // Boolean: checkPasswordComplexity()
    // At least eight characters long, contain a capital, a number, and a special character
    public boolean checkPasswordComplexity() {
        String password = registration.getPassword();
        if (password == null) return false;
        boolean longEnough = password.length() >= 8;
        boolean hasUpper = password.matches(".*[A-Z].*");
        boolean hasNumber = password.matches(".*[0-9].*");
        boolean hasSpecial = password.matches(".*[^A-Za-z0-9].*");
        return longEnough && hasUpper && hasNumber && hasSpecial;
    }

    // Boolean: checkCellPhoneNumber()
    // Regex: start with +27 and followed by exactly 9 digits (South African international)
    // (If you need to allow 10 digits after +27 modify the regex accordingly)
    public boolean checkCellPhoneNumber() {
        String cell = registration.getCellNumber();
        if (cell == null) return false;
        String regex = "^\\+27\\d{9}$";
        return Pattern.matches(regex, cell);
    }

    // String: registerUser() returns appropriate registration messages
    public String registerUser() {
        if (!checkUserName()) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber()) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        return "Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.\nUser registered successfully.";
    }

    // Boolean: loginUser()
    public boolean loginUser(String username, String password) {
        if (registration == null) return false;
        return registration.getUsername().equals(username) && registration.getPassword().equals(password);
    }

    // String: returnLoginStatus()
    public String returnLoginStatus(String username, String password) {
        if (loginUser(username, password)) {
            return "Welcome " + registration.getName() + " " + registration.getSurname() + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}
