import java.io.IOException;
import java.util.Scanner;

public class SimpleChatbot {
    public static void main(String[] args) {
        // Setting up a scanner to read user input
        try (Scanner scanner = new Scanner(System.in)) {
            String command;

            // Welcoming the user
            System.out.println("😊 Hello! I am your friendly chatbot. How can I assist you today?");
            
            // Starting a loop to keep the chat going until the user decides to exit
            while (true) {
                System.out.print("> "); // Prompt for user input
                command = scanner.nextLine().trim().toLowerCase(); // Reading and formatting the command

                // Checking if the user wants to exit the chat
                if (command.equals("exit")) {
                    System.out.println("👋 Goodbye! Have a great day!");
                    break; // Exiting the loop
                } 
                // Handling commands to open applications
                else if (command.startsWith("open ")) {
                    openApplication(command.substring(5)); // Extracting the app name
                } 
                // Handling web search commands
                else if (command.startsWith("search ")) {
                    searchWeb(command.substring(7)); // Extracting the search query
                } 
                // Handling YouTube search commands
                else if (command.startsWith("youtube ")) {
                    searchYouTube(command.substring(8)); // Extracting the search query
                } 
                // Responding to user queries about well-being
                else if (command.contains("how are you")) {
                    System.out.println("😊 I'm good, but what about you!");
                } 
                // Responding if the user is feeling good
                else if (command.contains("i am good")) {
                    System.out.println("😊 That's nice, how can I help you?");
                } 
                // Responding to inquiries about the chatbot's day
                else if (command.contains("how was your day")) {
                    System.out.println("😄 My day is always great when I get to chat with you!");
                } 
                // Handling jokes
                else if (command.contains("kidding")) {
                    System.out.println("😄 Haha, good one! I'm always up for a laugh!");
                } 
                // Responding to thanks
                else if (command.contains("thank you") || command.contains("thanks")) {
                    System.out.println("🤗 You're welcome! I'm happy to help!");
                } 
                // Responding to greetings
                else if (command.contains("hello") || command.contains("hi")) {
                    System.out.println("👋 Hello! It's nice to see you!");
                } 
                // Handling unrecognized commands
                else {
                    System.out.println("🤔 I didn't quite understand that. Could you please rephrase?");
                }
            }
        }
    }

    // Method to open specified applications
    private static void openApplication(String app) {
        String command = switch (app) {
            case "notepad" -> "notepad.exe"; // Opening Notepad
            case "calculator" -> "calc.exe"; // Opening Calculator
            default -> {
                // Responding to unsupported applications
                System.out.println("😕 Sorry, I can only open Notepad or Calculator.");
                yield null;  // Returning null for unrecognized apps
            }
        };

        // Attempting to start the application
        if (command != null) {
            try {
                new ProcessBuilder(command).start();
                System.out.println("✨ Opening " + app.substring(0, 1).toUpperCase() + app.substring(1) + "!");
            } catch (IOException e) {
                // Handling errors when opening an application
                System.out.println("😟 Failed to open " + app + ": " + e.getMessage());
            }
        }
    }

    // Method to search the web
    private static void searchWeb(String query) {
        String url = "https://www.google.com/search?q=" + query.replace(" ", "+");
        openBrowser(url); // Opening the browser with the search URL
    }

    // Method to search on YouTube
    private static void searchYouTube(String query) {
        String url = "https://www.youtube.com/results?search_query=" + query.replace(" ", "+");
        openBrowser(url); // Opening the browser with the YouTube search URL
    }

    // Method to open a web browser with a given URL
    private static void openBrowser(String url) {
        try {
            new ProcessBuilder("cmd", "/c", "start", url).start();
            System.out.println("🌐 Opening: " + url); // Informing the user that the browser is opening
        } catch (IOException e) {
            // Handling errors when trying to open the browser
            System.out.println("😟 Failed to open web browser: " + e.getMessage());
        }
    }
}
