/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog5121p3;

import java.util.Random;


public class Message {

    private String messageText;
    private String recipient;
    private String messageID;   // 10-digit string
    private String messageHash; // created via createMessageHash()
    private int messageNumber;  // assigned by MessageManager when added
    private String action;      // "sent", "stored", "disregard"

    // Constructor
    public Message(String messageText, String recipient) {
        this.messageText = messageText == null ? "" : messageText;
        this.recipient = recipient == null ? "" : recipient;
        this.messageID = generateMessageID();
        this.messageNumber = -1;
        this.action = "";
        // messageHash will be generated after messageNumber set via setMessageNumber
        this.messageHash = "";
    }

    Message() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   

    private String generateMessageID() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    // Boolean: checkMessageID() ensures messageID length <= 10
    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    // Boolean: checkRecipientCell()
    // Valid if starts with + and has no more than 13 chars (flexible for country codes),
    // but main brief requires +27 with 9 digits (handled by Login check earlier).
    public boolean checkRecipientCell() {
        if (recipient == null || recipient.isEmpty()) return false;
        return recipient.startsWith("+") && recipient.length() <= 13;
    }

    // String: createMessageHash()
    // Format: firstTwoOfID : messageNumber : FIRSTWORD + LASTWORD (caps)
    public String createMessageHash() {
        String firstTwo = messageID.length() >= 2 ? messageID.substring(0, 2) : messageID;
        String firstWord = "";
        String lastWord = "";
        if (!messageText.trim().isEmpty()) {
            String[] words = messageText.trim().split("\\s+");
            firstWord = words[0].toUpperCase();
            lastWord = words[words.length - 1].toUpperCase();
        }
        return firstTwo + ":" + messageNumber + ":" + firstWord + lastWord;
    }

    // String: SentMessage(option) — sets action and returns message to user
    public String SentMessage(String option) {
        if (option == null) return "Invalid option.";
        String o = option.trim().toLowerCase();
        switch (o) {
            case "send":
            case "sent":
            case "send message":
                this.action = "sent";
                return "Message successfully sent.";
            case "store":
            case "stored":
            case "store message":
                this.action = "stored";
                return "Message successfully stored.";
            case "disregard":
            case "discard":
            case "disregard message":
                this.action = "disregard";
                return "Message disregarded.";
            default:
                return "Invalid option.";
        }
    }

    // String: printMessages() returns the full details requested
    public String printMessages() {
        return "Message ID: " + messageID
                + "\nMessage Hash: " + messageHash
                + "\nRecipient: " + recipient
                + "\nMessage: " + messageText;
    }

    // Int: returnTotalMessagess() placeholder inside Message (manager tracks totals)
    public int returnTotalMessagess() {
        return -1; // actual total tracked by MessageManager
    }

    // storeMessage() placeholder (actual file writing done in MessageStorage)
    public String storeMessage() {
        this.action = "stored";
        return "Message stored (see persistent store).";
    }

    // Setters used by manager
    public void setMessageNumber(int number) {
        this.messageNumber = number;
        this.messageHash = createMessageHash();
    }

    public void setAction(String action) {
        this.action = action;
    }

    // Getters
    public String getMessageText() { return messageText; }
    public String getRecipient() { return recipient; }
    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public int getMessageNumber() { return messageNumber; }
    public String getAction() { return action; }

    public String getMessageDetails() { return printMessages(); }
}
