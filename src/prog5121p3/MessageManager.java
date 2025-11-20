/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package prog5121p3;

import java.util.ArrayList;
import java.util.List;

public class MessageManager {

    private final int MAX = 200;

    // arrays
    private Message[] sentMessages = new Message[MAX];
    private Message[] storedMessages = new Message[MAX];
    private Message[] disregardedMessages = new Message[MAX];

    private String[] messageHashes = new String[MAX];
    private String[] messageIDs = new String[MAX];

    // counters
    private int sentCount = 0;
    private int storedCount = 0;
    private int disregardCount = 0;
    private int hashCount = 0;
    private int idCount = 0;

    // message number counter (starts at 0)
    private int messageNumberCounter = 0;

    public MessageManager() {}

    // add message (flag: "sent", "stored", "disregard")
    public void addMessage(Message msg, String flag) {
        // assign message number
        msg.setMessageNumber(messageNumberCounter);
        // save IDs & hashes arrays
        messageIDs[idCount++] = msg.getMessageID();
        messageHashes[hashCount++] = msg.getMessageHash();

        if (flag == null) flag = "";
        switch (flag.trim().toLowerCase()) {
            case "sent":
            case "send":
                if (sentCount < MAX) {
                    sentMessages[sentCount++] = msg;
                    messageNumberCounter++; // increment only when actually sent
                }
                break;
            case "stored":
            case "store":
                if (storedCount < MAX) {
                    storedMessages[storedCount++] = msg;
                }
                break;
            case "disregard":
            case "discard":
                if (disregardCount < MAX) {
                    disregardedMessages[disregardCount++] = msg;
                }
                break;
            default:
                // default to disregard
                if (disregardCount < MAX) {
                    disregardedMessages[disregardCount++] = msg;
                }
                break;
        }
    }

    // Part 3: a) Display sender and recipient of all sent messages
    public String displaySendersAndRecipients() {
        if (sentCount == 0) return "No sent messages.";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sentCount; i++) {
            sb.append("Sender: YOU -> Recipient: ").append(sentMessages[i].getRecipient()).append("\n");
        }
        return sb.toString();
    }

    // b) Display the longest sent message
    public String getLongestSentMessage() {
        if (sentCount == 0) return "No sent messages.";
        Message longest = sentMessages[0];
        for (int i = 1; i < sentCount; i++) {
            if (sentMessages[i].getMessageText().length() > longest.getMessageText().length()) {
                longest = sentMessages[i];
            }
        }
        return longest.getMessageText();
    }

    // c) Search for a message ID and display recipient & message
    public String searchByMessageID(String id) {
        for (int i = 0; i < sentCount; i++) {
            if (sentMessages[i].getMessageID().equals(id)) {
                return "Recipient: " + sentMessages[i].getRecipient() + "\nMessage: " + sentMessages[i].getMessageText();
            }
        }
        for (int i = 0; i < storedCount; i++) {
            if (storedMessages[i].getMessageID().equals(id)) {
                return "Recipient: " + storedMessages[i].getRecipient() + "\nMessage: " + storedMessages[i].getMessageText();
            }
        }
        return "Message ID not found.";
    }

    // d) Search for all messages sent to a particular recipient (sent + stored)
    public String searchByRecipient(String recipient) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sentCount; i++) {
            if (sentMessages[i].getRecipient().equals(recipient)) sb.append(sentMessages[i].getMessageText()).append("\n");
        }
        for (int i = 0; i < storedCount; i++) {
            if (storedMessages[i].getRecipient().equals(recipient)) sb.append(storedMessages[i].getMessageText()).append("\n");
        }
        if (sb.length() == 0) return "No messages for this recipient.";
        return sb.toString();
    }

    // e) Delete a message using message hash (only in sentMessages)
    public String deleteMessageByHash(String hash) {
        for (int i = 0; i < sentCount; i++) {
            if (sentMessages[i].getMessageHash().equals(hash)) {
                String deleted = sentMessages[i].getMessageText();
                // shift left
                for (int j = i; j < sentCount - 1; j++) sentMessages[j] = sentMessages[j + 1];
                sentMessages[sentCount - 1] = null;
                sentCount--;
                return "Message \"" + deleted + "\" successfully deleted.";
            }
        }
        return "Message hash not found.";
    }

    // f) Display report listing full details of all sent messages
    public String displayReport() {
        if (sentCount == 0) return "No sent messages to report.";
        StringBuilder sb = new StringBuilder("=== SENT MESSAGE REPORT ===\n\n");
        for (int i = 0; i < sentCount; i++) {
            sb.append("Message ").append(i + 1).append(":\n").append(sentMessages[i].getMessageDetails()).append("\n\n");
        }
        return sb.toString();
    }

    // Save storedMessages array to JSON (calls MessageStorage)
    public String saveStoredMessagesToJson() {
        List<Message> list = new ArrayList<>();
        for (int i = 0; i < storedCount; i++) list.add(storedMessages[i]);
        try {
            MessageStorage.saveStoredMessages(list);
            return "Stored messages saved to JSON.";
        } catch (Exception ex) {
            return "Error saving stored messages: " + ex.getMessage();
        }
    }

    // Load messages from JSON into storedMessages array (appends)
    public String loadStoredMessagesFromJson() {
        try {
            List<Message> loaded = MessageStorage.loadStoredMessages();
            for (Message m : loaded) {
                if (storedCount < MAX) {
                    storedMessages[storedCount++] = m;
                    // also record IDs & hashes in arrays
                    messageIDs[idCount++] = m.getMessageID();
                    messageHashes[hashCount++] = m.getMessageHash();
                }
            }
            return "Loaded " + loaded.size() + " stored messages from JSON.";
        } catch (Exception ex) {
            return "Error loading stored messages: " + ex.getMessage();
        }
    }

    // getters for unit tests and UI
    public Message[] getSentMessages() { return sentMessages; }
    public int getSentCount() { return sentCount; }
    public Message[] getStoredMessages() { return storedMessages; }
    public int getStoredCount() { return storedCount; }
    public Message[] getDisregardedMessages() { return disregardedMessages; }
    public int getDisregardCount() { return disregardCount; }
    public String[] getMessageHashesArray() { return messageHashes; }
    public String[] getMessageIDsArray() { return messageIDs; }
    public int getTotalMessagesSent() { return messageNumberCounter; }
}
