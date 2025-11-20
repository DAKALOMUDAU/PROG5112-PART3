/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package prog5121p3;

import java.util.ArrayList;
import java.util.List;
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
public class MessageStorageNGTest {
    
    public MessageStorageNGTest() {
    }



    @Test
    public void testSaveStoredMessages() throws Exception {
        System.out.println("saveStoredMessages");
        List<Message> messages = new ArrayList<>();
        MessageStorage.saveStoredMessages(messages);

    }

    @Test
    public void testLoadStoredMessages() throws Exception {
        System.out.println("loadStoredMessages");
        List expResult = new ArrayList<>();
        List result = MessageStorage.loadStoredMessages();
        assertEquals(result, expResult);
        
    }
    
}
