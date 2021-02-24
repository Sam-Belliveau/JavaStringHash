import java.text.NumberFormat;
import java.util.Locale;

class Main {

    public static String formatInt(int n) {
        return NumberFormat.getNumberInstance(Locale.US).format(n);
    }

    public static void getCollisions(int iters, boolean random, IHash hash) {
        
        // This is a hashset that is basically so optimized for integers, it uses practically no memory
        IntSet hashs = new IntSet();
        int collisions = 0;

        for(long i = 0; i < iters; ++i) {
            long t = i;
            if (random) t *= 0xff51afd7ed558ccdL;
            
            // Generate hash from random string
            int h = hash.hash(Long.toString(t, Character.MAX_RADIX));

            // Check for unique hash
            if(!hashs.add(h)) ++collisions;
        }

        // Build a message as a result
        StringBuilder msg = new StringBuilder();
        msg.append(hash.getClass().getSimpleName()).append(":\t");
        msg.append(formatInt(collisions)).append(" / ").append(formatInt(iters)).append(" collisions");
        System.out.println(msg);
    }

    public static void main(String[] args) {
        int iter = Integer.MAX_VALUE;

        try {
            iter = Integer.parseInt(args[0]);
        } catch(Exception e) {

        }

        IHash[] hashs = {
            new DefaultHash(),
            new CommonHash(),
            new FNV1a64(),
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
            System.gc();
            getCollisions(iter, true, hash);
        }
    }
}