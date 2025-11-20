/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prog5121p3;

import prog5121p3.Registration;

public class PROG5121P3 {
    public static void main(String[] args) {
        // Start registration
        Registration obj = new Registration();
        obj.collectInfo();

        // After successful registration, the login process is called inside Registration
        // because you already have this line in collectInfo():
        // login logged = new login(this);
        // logged.log();
        
        // So you don’t actually need to call login again here.
        // The program flow is:
        // Registration → Login
    }
}
