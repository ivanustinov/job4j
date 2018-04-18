package tasks;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListCompare implements Comparator {


    @Override
    public int compare(Object o1, Object o2) {
        String a = (String) o1;
        String b = (String) o2;
        Integer size1 = a.length();
        Integer size2 = b.length();
        if (a.length() == b.length()) {
            for (int i = 0; i < a.length(); i++) {
                Character character1 = a.charAt(i), character2 = b.charAt(i);
                if (character1.compareTo(character2) == 0)
                    continue;
                else return character1.compareTo(character2) > 0 ? -1 : 1;
            }
        }

        return size1.compareTo(size2);
    }

    public List<String> sortStrings(List<String> g) {
        g.sort(new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                Integer size1 = a.length();
                Integer size2 = b.length();
                int result = size1.compareTo(size2);
                if (a.length() == b.length()) {
                    for (int i = 0; i < a.length(); i++) {
                        Character character1 = a.charAt(i), character2 = b.charAt(i);
                        if (character1.compareTo(character2) == 0)
                            continue;
                        else {
                            result = character1.compareTo(character2) > 0 ? 1 : -1;
                            break;
                        }
                    }
                }
                return result;
            }
        });
        return g;
    }
    public static void main(String[] args) {
        ArrayList<String> r = new ArrayList<>();
        r.add("Africa");
        r.add("Afonia");
        System.out.println(new ListCompare().sortStrings(r));
    }
}
