
package view;

public class Validation {
    public static boolean checkName(String name) {
        // Check if the name contains only alphabetic characters and spaces
        return name.matches("^[a-zA-Z\\s]+$");
    }
}
