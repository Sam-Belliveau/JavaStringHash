import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Main {

    public static String formatInt(int n) {
        return NumberFormat.getNumberInstance(Locale.US).format(n);
    }

    public static int getCollisions(int iters, boolean random, IHash hash) {

        // This is a hashset that is basically so optimized for integers, it uses
        // practically no memory
        IntSet hashs = new IntSet();
        int collisions = 0;

        for (long i = 0; i < iters; ++i) {
            long t = i;
            if (random)
                t *= 0xff51afd7ed558ccdL;

            // Generate hash from random string
            int h = hash.hash(Long.toString(t, Character.MAX_RADIX));

            // Check for unique hash
            if (!hashs.add(h))
                ++collisions;
        }

        return collisions;
    }

    static final IHash[] HASH_LIST = {
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

    public static void printResults(final int iter, final boolean random) throws InterruptedException, ExecutionException {
                
        ExecutorService executor = Executors.newCachedThreadPool();
        ArrayList<Future<Integer>> results = new ArrayList<>();

        for(int i = 0; i < HASH_LIST.length; ++i) {
            final IHash hasher = HASH_LIST[i];
            results.add(executor.submit(new Callable<Integer>() {
                public Integer call() {
                    return getCollisions(iter, random, hasher);
                }
            }));
        }

        for(int i = 0; i < HASH_LIST.length; ++i) {
            StringBuilder msg = new StringBuilder();
            msg.append(HASH_LIST[i].getClass().getSimpleName()).append(":\t");
            msg.append(formatInt(results.get(i).get())).append(" / ").append(formatInt(iter)).append(" collisions");
            System.out.println(msg);
        }

        executor.shutdown();
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        final int iter = 10_000_000;

        System.out.println("+-------------------------+");
        System.out.println("| Sequential Strings Test |");
        System.out.println("+-------------------------+");

        printResults(iter, false);

        System.out.println("+---------------------+");
        System.out.println("| Random Strings Test |");
        System.out.println("+---------------------+");

        printResults(iter, true);

    }
}