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
public class MessageManagerNGTest {
    
    public MessageManagerNGTest() {
    }

    @Test
    public void testAddMessage() {
        System.out.println("addMessage");
        
        String flag = "";
        MessageManager instance = new MessageManager();
        Message msg = null;
        

       
    }

    @Test
    public void testDisplaySendersAndRecipients() {
        System.out.println("displaySendersAndRecipients");
        MessageManager instance = new MessageManager();
        String expResult = "";
        String result = instance.displaySendersAndRecipients();
        

    }

    @Test
    public void testGetLongestSentMessage() {
        System.out.println("getLongestSentMessage");
        MessageManager instance = new MessageManager();
        String expResult = "";
        String result = instance.getLongestSentMessage();
       

    }

    @Test
    public void testSearchByMessageID() {
        System.out.println("searchByMessageID");
        String id = "";
        MessageManager instance = new MessageManager();
        String expResult = "";
        String result = instance.searchByMessageID(id);


    }

    @Test
    public void testSearchByRecipient() {
        System.out.println("searchByRecipient");
        String recipient = "";
        MessageManager instance = new MessageManager();
        String expResult = "";
        String result = instance.searchByRecipient(recipient);
        

    }

    @Test
    public void testDeleteMessageByHash() {
        System.out.println("deleteMessageByHash");
        String hash = "";
        MessageManager instance = new MessageManager();
        String expResult = "";
        String result = instance.deleteMessageByHash(hash);

        
    }

    @Test
    public void testDisplayReport() {
        System.out.println("displayReport");
        MessageManager instance = new MessageManager();
        String expResult = "";
        String result = instance.displayReport();
        

    }

    @Test
    public void testSaveStoredMessagesToJson() {
        System.out.println("saveStoredMessagesToJson");
        MessageManager instance = new MessageManager();
        String expResult = "";
        String result = instance.saveStoredMessagesToJson();
       

    }

    @Test
    public void testLoadStoredMessagesFromJson() {
        System.out.println("loadStoredMessagesFromJson");
        MessageManager instance = new MessageManager();
        String expResult = "";
        String result = instance.loadStoredMessagesFromJson();
        

    }

    @Test
    public void testGetSentMessages() {
        System.out.println("getSentMessages");
        MessageManager instance = new MessageManager();
       
        Message[] result = instance.getSentMessages();
        Object expResult = null;
       

    }

    @Test
    public void testGetSentCount() {
        System.out.println("getSentCount");
        MessageManager instance = new MessageManager();
        int expResult = 0;
        int result = instance.getSentCount();


    }

    @Test
    public void testGetStoredMessages() {
        System.out.println("getStoredMessages");
        MessageManager instance = new MessageManager();
        
        Message[] result = instance.getStoredMessages();
        Object expResult = null;
       

    }

    @Test
    public void testGetStoredCount() {
        System.out.println("getStoredCount");
        MessageManager instance = new MessageManager();
        int expResult = 0;
        int result = instance.getStoredCount();
   
        
    }

    @Test
    public void testGetDisregardedMessages() {
        System.out.println("getDisregardedMessages");
        MessageManager instance = new MessageManager();
        
        Message[] result = instance.getDisregardedMessages();
        Object expResult = new Object();
       
    
    }

    @Test
    public void testGetDisregardCount() {
        System.out.println("getDisregardCount");
        MessageManager instance = new MessageManager();
        int expResult = 0;
        int result = instance.getDisregardCount();
      

    }

    @Test
    public void testGetMessageHashesArray() {
        System.out.println("getMessageHashesArray");
        MessageManager instance = new MessageManager();
        String expResult = new String();
        String[] result = instance.getMessageHashesArray();
      

    }

    @Test
    public void testGetMessageIDsArray() {
        System.out.println("getMessageIDsArray");
        MessageManager instance = new MessageManager();
        String expResult = new String();
        String[] result = instance.getMessageIDsArray();


    }

    @Test
    public void testGetTotalMessagesSent() {
        System.out.println("getTotalMessagesSent");
        MessageManager instance = new MessageManager();
        int expResult = 0;
        int result = instance.getTotalMessagesSent();
        

    }
    
}
