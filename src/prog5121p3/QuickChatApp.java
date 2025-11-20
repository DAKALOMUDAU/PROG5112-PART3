/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package prog5121p3;

import javax.swing.JOptionPane;



public class QuickChatApp {
    public static void main(String[] args) {

        // --- Registration Phase (Part 1) ---
        Registration reg = new Registration();
        JOptionPane.showMessageDialog(null, "=== USER REGISTRATION ===");

        String name = JOptionPane.showInputDialog("Enter your first name:");
        if (name == null) return;
        String surname = JOptionPane.showInputDialog("Enter your surname:");
        if (surname == null) return;
        String username = JOptionPane.showInputDialog("Create a username (must contain underscore and be <= 5 chars):");
        if (username == null) return;
        String password = JOptionPane.showInputDialog("Create a password (at least 8 chars, 1 capital, 1 number, 1 special):");
        if (password == null) return;
        String cell = JOptionPane.showInputDialog("Enter your cellphone number (must start with +27 and have 9 digits after):");
        if (cell == null) return;

        reg.registerUser(name, surname, username, password, cell);
        Login loginHelper = new Login(reg);

        String registerMsg = loginHelper.registerUser();
        JOptionPane.showMessageDialog(null, registerMsg);

        // --- Login Phase ---
        JOptionPane.showMessageDialog(null, "=== LOGIN ===");
        String loginUser = JOptionPane.showInputDialog("Enter username:");
        if (loginUser == null) return;
        String loginPass = JOptionPane.showInputDialog("Enter password:");
        if (loginPass == null) return;

        if (!loginHelper.loginUser(loginUser, loginPass)) {
            JOptionPane.showMessageDialog(null, "Username or password incorrect, please try again.");
            return;
        }

        JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");

        // --- Main Menu (Part 2 & Part 3) ---
        MessageManager manager = new MessageManager();
        // Load any previously stored messages (optional)
        manager.loadStoredMessagesFromJson();

        // Ask user how many messages they want to enter this session
        int maxMessages = 0;
        try {
            String numStr = JOptionPane.showInputDialog("How many messages do you want to enter this session?");
            if (numStr == null) return;
            maxMessages = Integer.parseInt(numStr);
            if (maxMessages < 0) maxMessages = 0;
        } catch (NumberFormatException ex) {
            maxMessages = 0;
        }
        int entered = 0;

        boolean running = true;
        while (running) {
            String menu = "Please choose an option:\n"
                        + "1) Send Messages\n"
                        + "2) Show recently sent messages (Coming Soon)\n"
                        + "3) Part 3 features\n"
                        + "4) Quit";
            String choice = JOptionPane.showInputDialog(menu);
            if (choice == null) break;

            switch (choice) {
                case "1":
                    if (entered >= maxMessages) {
                        JOptionPane.showMessageDialog(null, "Message limit reached.");
                        break;
                    }

                    String text = JOptionPane.showInputDialog("Enter message content (max 250 chars):");
                    if (text == null) break;

                    if (text.length() > 250) {
                        int over = text.length() - 250;
                        JOptionPane.showMessageDialog(null, "Message exceeds 250 characters by " + over + ", please reduce size.");
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "Message ready to send.");
                    }

                    String recipient = JOptionPane.showInputDialog("Enter recipient number (must start with +):");
                    if (recipient == null) break;

                    // Recipient validation: must start with + and reasonable length
                    Message tmp = new Message(text, recipient);
                    if (!tmp.checkRecipientCell()) {
                        JOptionPane.showMessageDialog(null, "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "Cell phone number successfully captured.");
                    }

                    // Create message object
                    Message msg = new Message(text, recipient);

                    // Ask action: Send / Store / Disregard
                    String[] options = {"Send Message", "Store Message", "Disregard Message"};
                    String action = (String) JOptionPane.showInputDialog(null, "Select action for this message:",
                            "Action", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                    if (action == null) break;

                    String result;
                    if (action.equals("Send Message")) {
                        manager.addMessage(msg, "sent");
                        result = "Message successfully sent.";
                        JOptionPane.showMessageDialog(null, result + "\n\n" + msg.getMessageDetails());
                    } else if (action.equals("Store Message")) {
                        manager.addMessage(msg, "stored");
                        manager.saveStoredMessagesToJson();
                        result = "Message successfully stored.";
                        JOptionPane.showMessageDialog(null, result);
                    } else {
                        manager.addMessage(msg, "disregard");
                        result = "Message disregarded.";
                        JOptionPane.showMessageDialog(null, result);
                    }

                    entered++;
                    break;

                case "2":
                    JOptionPane.showMessageDialog(null, "Coming Soon.");
                    break;

                case "3":
                    String submenu = "Part 3 features:\n"
                            + "1) Display sender & recipient for all sent messages\n"
                            + "2) Display longest sent message\n"
                            + "3) Search by message ID\n"
                            + "4) Search by recipient\n"
                            + "5) Delete a message using the message hash\n"
                            + "6) Display sent messages report\n"
                            + "7) Back";
                    String sub = JOptionPane.showInputDialog(submenu);
                    if (sub == null) break;
                    switch (sub) {
                        case "1":
                            JOptionPane.showMessageDialog(null, manager.displaySendersAndRecipients());
                            break;
                        case "2":
                            JOptionPane.showMessageDialog(null, manager.getLongestSentMessage());
                            break;
                        case "3":
                            String id = JOptionPane.showInputDialog("Enter message ID:");
                            if (id != null) JOptionPane.showMessageDialog(null, manager.searchByMessageID(id));
                            break;
                        case "4":
                            String rec = JOptionPane.showInputDialog("Enter recipient number:");
                            if (rec != null) JOptionPane.showMessageDialog(null, manager.searchByRecipient(rec));
                            break;
                        case "5":
                            String hash = JOptionPane.showInputDialog("Enter message hash to delete:");
                            if (hash != null) JOptionPane.showMessageDialog(null, manager.deleteMessageByHash(hash));
                            break;
                        case "6":
                            JOptionPane.showMessageDialog(null, manager.displayReport());
                            break;
                        default:
                            break;
                    }
                    break;

                case "4":
                    JOptionPane.showMessageDialog(null, "Total messages sent: " + manager.getTotalMessagesSent());
                    manager.saveStoredMessagesToJson();
                    running = false;
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Invalid option. Please choose 1, 2, 3 or 4.");
                    break;
            }
        }

        JOptionPane.showMessageDialog(null, "Thank you for using QuickChat. Goodbye!");
        System.exit(0);
    }
}
