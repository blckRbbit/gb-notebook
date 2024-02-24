package notebook.util;

public class Strings {
    public boolean hasSpace(String s) {
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                return true;
            }
        }
        return false;
    }
}
