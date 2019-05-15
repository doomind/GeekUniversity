package Lesson_3.PasswordComplexity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String password1 = "asdMNB678^@";
        String password2 = "asdpoi876";
        String password3 = "aS!2";
        System.out.println("Password \"" + password1 + "\" pass complexity: " + checkPassword(password1));
        System.out.println("Password \"" + password2 + "\" pass complexity: " + checkPassword(password2));
        System.out.println("Password \"" + password3 + "\" pass complexity: " + checkPassword(password3));
    }

    public static boolean checkPassword (String password) {
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*\\W)(?=.{8,20}$).*";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        return matcher.find();
    }
}
