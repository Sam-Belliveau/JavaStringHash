import java.text.NumberFormat;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

class Main {

    public static String formatInt(int n) {
        return NumberFormat.getNumberInstance(Locale.US).format(n);
    }

    public static void getColisions(int iters, IHash hash) {
        int colisions = 0;
        Set<Integer> hashs = new HashSet<>();

        for(int i = 0; i < iters; ++i) {
            String test = Integer.toString(i, Character.MAX_RADIX);
            int h = hash.hash(test);
            if(hashs.contains(h)) {
                colisions += 1;
            } else {
                hashs.add(h);
            }
        }

        System.out.println(hash.getClass().getSimpleName() + ": ");
        System.out.println("\t# of Colisions:    " + formatInt(colisions) + " / " + formatInt(iters));
        System.out.println("\t# of Unique Hashs: " + formatInt(hashs.size()) + " / " + formatInt(iters));
        System.out.println();
    }

    public static void main(String[] args) {
        int iter = 100_000_000;

        try {
            iter = Integer.parseInt(args[0]);
        } catch(Exception e) {

        }

        getColisions(iter, new DefaultHash());
        getColisions(iter, new CommonHash());
        getColisions(iter, new XOR32Hash());
        getColisions(iter, new XOR64Hash());
        getColisions(iter, new RotateHash());
    }
}