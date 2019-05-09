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
        ArrayList<String> regexes = new ArrayList<>();

        regexes.add("[A-Z]+");
        regexes.add("[a-z]+");
        regexes.add("\\d+");
        regexes.add("\\W+");
        regexes.add("^.{8,20}$");

        Iterator iterator = regexes.iterator();
        boolean goodPassword = true;
        Pattern pattern;
        Matcher matcher;

        do {
            if (!goodPassword) {
                return false;
            }
            pattern = Pattern.compile((String) iterator.next());
            matcher = pattern.matcher(password);
            goodPassword = matcher.find();
        } while (iterator.hasNext());

        return goodPassword;
    }
}
