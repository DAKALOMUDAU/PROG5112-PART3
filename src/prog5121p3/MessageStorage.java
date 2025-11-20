/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog5121p3;

import java.io.*;
import java.util.*;
import java.util.regex.*;


public class MessageStorage {

    private static final String FILE = "stored_messages.json";

    // Save the list of stored messages to JSON (overwrites file)
    public static void saveStoredMessages(List<Message> messages) throws IOException {
        try (FileWriter fw = new FileWriter(FILE)) {
            fw.write("[\n");
            for (int i = 0; i < messages.size(); i++) {
                Message m = messages.get(i);
                fw.write("  {\n");
                fw.write("    \"messageText\": \"" + escape(m.getMessageText()) + "\",\n");
                fw.write("    \"recipient\": \"" + escape(m.getRecipient()) + "\",\n");
                fw.write("    \"messageID\": \"" + escape(m.getMessageID()) + "\",\n");
                fw.write("    \"messageHash\": \"" + escape(m.getMessageHash()) + "\",\n");
                fw.write("    \"messageNumber\": " + m.getMessageNumber() + ",\n");
                fw.write("    \"action\": \"" + escape(m.getAction()) + "\"\n");
                fw.write("  }" + (i < messages.size()-1 ? "," : "") + "\n");
            }
            fw.write("]\n");
        }
    }

    // Load stored messages from JSON file into list
    public static List<Message> loadStoredMessages() throws IOException {
        File f = new File(FILE);
        if (!f.exists()) return new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        try (FileReader fr = new FileReader(f); BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) sb.append(line).append("\n");
        }
        String content = sb.toString().trim();
        if (content.isEmpty() || content.equals("[]")) return new ArrayList<>();

        // Regex to find objects
        List<Message> list = new ArrayList<>();
        Pattern objPattern = Pattern.compile("\\{(.*?)\\}", Pattern.DOTALL);
        Matcher objMatcher = objPattern.matcher(content);
        while (objMatcher.find()) {
            String obj = objMatcher.group(1);

            String messageText = extractStringField(obj, "messageText");
            String recipient = extractStringField(obj, "recipient");
            String messageID = extractStringField(obj, "messageID");
            String messageHash = extractStringField(obj, "messageHash");
            int messageNumber = extractIntField(obj, "messageNumber", -1);
            String action = extractStringField(obj, "action");

            Message m = new Message(unescape(messageText), unescape(recipient));
            // override generated fields with stored ones
            // reflect stored ID if present (we can't set private messageID directly, so recreate via reflection-like approach)
            // For simplicity, set messageNumber then set messageHash; we'll set messageID by direct field manipulation via a small helper:
            setMessageIdAndHash(m, messageID, messageHash);
            m.setMessageNumber(messageNumber >= 0 ? messageNumber : 0);
            m.setAction(action == null ? "" : action);
            // ensure hash consistency
            m.setMessageNumber(m.getMessageNumber());
            list.add(m);
        }
        return list;
    }

    // helper: extract "key": "value"
    private static String extractStringField(String obj, String key) {
        Pattern p = Pattern.compile("\"" + Pattern.quote(key) + "\"\\s*:\\s*\"(.*?)\"", Pattern.DOTALL);
        Matcher m = p.matcher(obj);
        if (m.find()) return m.group(1);
        return "";
    }

    private static int extractIntField(String obj, String key, int defaultVal) {
        Pattern p = Pattern.compile("\"" + Pattern.quote(key) + "\"\\s*:\\s*(\\d+)", Pattern.DOTALL);
        Matcher m = p.matcher(obj);
        if (m.find()) {
            try { return Integer.parseInt(m.group(1)); } catch (NumberFormatException ex) { return defaultVal; }
        }
        return defaultVal;
    }

    // escape special characters for JSON strings
    private static String escape(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r");
    }

    private static String unescape(String s) {
        if (s == null) return "";
        return s.replace("\\n", "\n").replace("\\r", "\r").replace("\\\"", "\"").replace("\\\\", "\\");
    }

    // small utility to set messageID and messageHash in Message instance (no direct setter) using reflection
    private static void setMessageIdAndHash(Message m, String id, String hash) {
        try {
            java.lang.reflect.Field idField = Message.class.getDeclaredField("messageID");
            idField.setAccessible(true);
            idField.set(m, id == null ? m.getMessageID() : id);

            java.lang.reflect.Field hashField = Message.class.getDeclaredField("messageHash");
            hashField.setAccessible(true);
            hashField.set(m, hash == null ? m.getMessageHash() : hash);
        } catch (Exception ex) {
            // ignore - leave generated values
        }
    }
}
