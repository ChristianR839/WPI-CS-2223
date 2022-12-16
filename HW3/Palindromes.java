import java.util.Locale;

public class Palindromes {

    public Palindromes() {

    }

    public boolean palindromecheck(String s) {
        s = s.toLowerCase(Locale.ROOT).replaceAll("\\s+", "").replaceAll("\\p{Punct}", "");

        String substring;

        if (s.length() == 1 || s.length() == 0) {
            return true;
        } else {
            substring = s.substring(1, s.length()-1);
        }

        boolean isPalindrome = s.charAt(0) == s.charAt(s.length()-1);

        return (isPalindrome && palindromecheck(substring));
    }
}
