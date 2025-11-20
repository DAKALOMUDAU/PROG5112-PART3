/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package prog5121p3;

import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author RC_Student_Lab
 */
public class RegistrationNGTest {
    
    public RegistrationNGTest() {
    }


    @Test
    public void testRegisterUser() {
        System.out.println("registerUser");
        String name = "";
        String surname = "";
        String username = "";
        String password = "";
        String cellNumber = "";
        Registration instance = new Registration();
        instance.registerUser(name, surname, username, password, cellNumber);

    }

    @Test
    public void testGetName() {
        System.out.println("getName");
        Registration instance = new Registration();
        String expResult = "";
        String result = instance.getName();


    }

    @Test
    public void testGetSurname() {
        System.out.println("getSurname");
        Registration instance = new Registration();
        String expResult = "";
        String result = instance.getSurname();
        
        
    }

    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        Registration instance = new Registration();
        String expResult = "";
        String result = instance.getUsername();
        
        
    }

    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        Registration instance = new Registration();
        String expResult = "";
        String result = instance.getPassword();
       

    }

    @Test
    public void testGetCellNumber() {
        System.out.println("getCellNumber");
        Registration instance = new Registration();
        String expResult = "";
        String result = instance.getCellNumber();
      

    }
    
}
