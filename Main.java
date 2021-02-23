import java.text.NumberFormat;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

class Main {

    public static String formatInt(int n) {
        return NumberFormat.getNumberInstance(Locale.US).format(n);
    }

    public static void getCollisions(int iters, boolean random, IHash hash) {
        int collisions = 0;
        Set<Integer> hashs = new HashSet<>();

        for(long i = 0; i < iters; ++i) {
            long t = i;
            if (random) t *= 0xff51afd7ed558ccdL;
            String test = Long.toString(t, Character.MAX_RADIX);

            // Check to see if the hash collides
            int h = hash.hash(test);
            if(hashs.contains(h)) {
                collisions += 1;
            } else {
                hashs.add(h);
            }
        }

        StringBuilder msg = new StringBuilder();
        msg.append(hash.getClass().getSimpleName()).append(":\t");
        msg.append(formatInt(collisions)).append(" / ").append(formatInt(iters)).append(" collisions");
        System.out.println(msg);
    }

    public static void main(String[] args) {
        int iter = 100_000_000;

        try {
            iter = Integer.parseInt(args[0]);
        } catch(Exception e) {

        }

        IHash[] hashs = {
            new DefaultHash(),
            new CommonHash(),
            new RotateHash(),
            new BetterCommonHash(),
            new XOR32Hash(),
            new XOR64Hash(),
            new MurmurHash(),
            new OneAtATimeHash(),
            new CRC32Hash()
        };

        System.out.println("+-------------------------+");
        System.out.println("| Sequential Strings Test |");
        System.out.println("+-------------------------+");

        for(IHash hash : hashs) {
            getCollisions(iter, false, hash);
        }

        System.out.println("+---------------------+");
        System.out.println("| Random Strings Test |");
        System.out.println("+---------------------+");

        for(IHash hash : hashs) {
            getCollisions(iter, true, hash);
        }
    }
}