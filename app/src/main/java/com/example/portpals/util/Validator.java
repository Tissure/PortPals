package com.example.portpals.util;

import android.widget.TextView;
import android.widget.Toast;

public class Validator {

    private static final String invalidPasswordMessage = "Invalid password! Must have at least 8 characters, " +
            "one letter that upper case and lower case, and one special character";
    private static final String passwordsNotSameMessage = "Passwords you entered do not match!";
    private static final String invalidEmailMessage = "Email must have an '@' symbol in it";
    private static final String invalidDisplayNameMessage = "Display name must consist of at least one character!";

    private static final int MIN_PASSWORD_LENGTH = 8;

    private Validator() {}

    public static boolean isSamePassword(TextView p1TextView, TextView p2TextView) {
        final String p1 = p1TextView.getText().toString();
        final String p2 = p2TextView.getText().toString();
        if (!p1.equals(p2)) {
            p1TextView.clearComposingText();
            p2TextView.clearComposingText();
            Toast.makeText(p1TextView.getContext(), passwordsNotSameMessage, Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public static boolean isValidDisplayName(TextView displayNameTextView) {
        String displayName = displayNameTextView.getText().toString();
        if (displayName.trim().isEmpty()) {
            displayNameTextView.setError(invalidDisplayNameMessage);
            return false;
        }
        return true;
    }

    // sample email: claytonhunter10@gmail.com
    public static boolean isValidEmail(TextView emailTextView) {
        String email = emailTextView.getText().toString();
        if (!email.contains("@")) {
            emailTextView.setError(invalidEmailMessage);
            return false;
        }
        return true;
    }

    public static boolean isValidPassword(TextView passwordTextView) {
        String password = passwordTextView.getText().toString();
        if (password.length() < MIN_PASSWORD_LENGTH) {
            passwordTextView.setError(invalidPasswordMessage);
            return false;
        }
        boolean hasUpperCaseLetter = false;
        boolean hasLowerCaseLetter = false;
        boolean hasNumber = false;
        boolean hasSpecialCharacter = false;
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                hasNumber = true;
            } else if (Character.isLetter(c)) {
                if (Character.isUpperCase(c)) {
                    hasUpperCaseLetter = true;
                } else if (Character.isLowerCase(c)) {
                    hasLowerCaseLetter = true;
                }
            } else {
                hasSpecialCharacter = true;
            }
        }
        if (!hasNumber || !hasLowerCaseLetter || !hasUpperCaseLetter || !hasSpecialCharacter) {
            passwordTextView.setError(invalidPasswordMessage);
        }
        return true;
    }

}
