import java.awt.geom.Line2D;
import java.util.LinkedList;
import java.util.Locale;

public class GrayCodes {

    public GrayCodes() {

    }

    public void graycodesarefun(int n) {
        LinkedList<String> graycodes = graycodes(n);
        LinkedList<String> names = names(graycodes);
        LinkedList<String> inOrOut = inOrOut(names);
        LinkedList<String> action = action(names, inOrOut);
        LinkedList<LinkedList<String>> inPhoto = inPhoto(graycodes, n);

        printTable(graycodes, inPhoto, action);
    }

    public LinkedList<String> graycodes(int n) {
        LinkedList<String> g;

        if (n == 1) {
            g = new LinkedList<String>();
            g.add("0");
            g.add("1");
        } else {
            LinkedList<String> l1 = graycodes(n-1);
            LinkedList<String> l2 = new LinkedList<>();

            for (int i = l1.size()-1; i >= 0; i--) {
                l2.add(l1.get(i));
            }

            for (int i = 0; i < l1.size(); i++) {
                String e = l1.get(i);
                e = "0" + e;
                l1.set(i, e);
            }

            for (int i = 0; i < l2.size(); i++) {
                String e = l2.get(i);
                e = "1" + e;
                l2.set(i, e);
            }

            l1.addAll(l2);
            g = l1;
        }

        return g;
    }

    public LinkedList<String> names(LinkedList<String> g) {
        // Loop through all values
        // Use XOR to find which bit flipped
        // Switch to print <name>,

        LinkedList<String> n = new LinkedList<>();

        for (int i = 1; i < g.size(); i++) {
            int flipped = Integer.parseInt(g.get(i), 2) ^ Integer.parseInt(g.get(i-1), 2);

            switch (flipped) {
                case 1:
                    n.add("Alice");
                    break;
                case 2:
                    n.add("Bob");
                    break;
                case 4:
                    n.add("Chris");
                    break;
                case 8:
                    n.add("Dylan");
                    break;
                case 16:
                    n.add("Eve");
                    break;
                case 32:
                    n.add("Felix");
                    break;
            }

        }

        return n;
    }

    public LinkedList<String> inOrOut(LinkedList<String> n) {
        int[] p = {0, 0, 0, 0, 0, 0};
        LinkedList<String> i = new LinkedList<>();

        for (String s : n) {
            switch (s) {
                case "Alice":
                    p[0] ^= 1;
                    if (p[0] == 1) i.add("In");
                    else i.add("Out");
                    break;
                case "Bob":
                    p[1] ^= 1;
                    if (p[1] == 1) i.add("In");
                    else i.add("Out");
                    break;
                case "Chris":
                    p[2] ^= 1;
                    if (p[2] == 1) i.add("In");
                    else i.add("Out");
                    break;
                case "Dylan":
                    p[3] ^= 1;
                    if (p[3] == 1) i.add("In");
                    else i.add("Out");
                    break;
                case "Eve":
                    p[4] ^= 1;
                    if (p[4] == 1) i.add("In");
                    else i.add("Out");
                    break;
                case "Felix":
                    p[5] ^= 1;
                    if (p[5] == 1) i.add("In");
                    else i.add("Out");
                    break;
            }
        }

        return i;
    }

    public LinkedList<String> action(LinkedList<String> n, LinkedList<String> i) {
        LinkedList<String> a = new LinkedList<>();

        for (int k = 0; k < n.size(); k++) {
            a.add(n.get(k) + " " + i.get(k));
        }

        return a;
    }

    public LinkedList<LinkedList<String>> inPhoto(LinkedList<String> g, int n) {
        LinkedList<LinkedList<String>> p = new LinkedList<>();
        LinkedList<String> c = new LinkedList<>();

        // Look at each gray code and determine who is in each photo

        for (String s : g) {
            try {
                if (s.charAt(n-1) == '1') c.add("Alice");
                if (s.charAt(n-2) == '1') c.add("Bob");
                if (s.charAt(n-3) == '1') c.add("Chris");
                if (s.charAt(n-4) == '1') c.add("Dylan");
                if (s.charAt(n-5) == '1') c.add("Eve");
                if (s.charAt(n-6) == '1') c.add("Felix");
            } catch (StringIndexOutOfBoundsException ignore) {}
            p.add((LinkedList<String>) c.clone());
            c.clear();
        }

        return p;
    }

    public void printTable(LinkedList<String> g, LinkedList<LinkedList<String>> p, LinkedList<String> a) {
        System.out.println("Index   Gray Code   Child(ren) in Photo                      Action");
        System.out.println("----------------------------------------------------------------------");
        for (int i = 1; i < g.size(); i++) {
            System.out.format("%-8s", i);
            System.out.format("%-12s", g.get(i));
            System.out.format("%-41s", p.get(i));
            System.out.print(a.get(i-1));
            System.out.println();
        }
    }
}
