import java.util.BitSet;

/**
 * This class is a very simple implementation of a set, for integers. It is
 * optimized for memory usage to prevent the huge amount of memory a HashSet
 * uses with integers.
 * 
 * You need to store 0 and -1 in separate booleans in order for them to fit in
 * the arrays. You also MUST use a BitSet, as the JVM WILL NOT let you allocate
 * a boolean array that big.
 */
public class IntSet {

    private boolean zero;
    private boolean mOne;
    private BitSet negatives;
    private BitSet positives;

    public IntSet() {
        zero = false;
        mOne = false;
        negatives = new BitSet(Integer.MAX_VALUE);
        positives = new BitSet(Integer.MAX_VALUE);
    }

    public boolean contains(int n) {
        if (n == +0)
            return zero;
        if (n == -1)
            return mOne;
        if (n <= -2)
            return negatives.get(-n - 2);
        else
            return positives.get(n - 1);
    }

    private void set(int n, boolean value) {
        if (n == +0)
            zero = value;
        else if (n == -1)
            mOne = value;
        else if (n <= -2)
            negatives.set(-n - 2, value);
        else
            positives.set(n - 1, value);
    }

    public boolean add(int n) {
        if (contains(n))
            return false;
        set(n, true);
        return true;
    }

    public boolean remove(int n) {
        if (!contains(n))
            return false;
        set(n, false);
        return true;
    }

    /**
     * Quick test to see if the corner cases in the bitset work. 
     */
    public static void main(String[] args) {
        IntSet test = new IntSet();
        
        test.add(-5);
        test.add(-3);
        test.add(-2);
        test.add(-1);
        test.add(-0);
        test.add(1);
        test.add(2);
        test.add(3);
        test.add(5);

        System.out.println("Testing -5 " + test.contains(-5) + " [true]");
        System.out.println("Testing -4 " + test.contains(-4) + " [false]");
        System.out.println("Testing -3 " + test.contains(-3) + " [true]");
        System.out.println("Testing -2 " + test.contains(-2) + " [true]");
        System.out.println("Testing -1 " + test.contains(-1) + " [true]");
        System.out.println("Testing 0 " + test.contains(0) + " [true]");
        System.out.println("Testing 1 " + test.contains(1) + " [true]");
        System.out.println("Testing 2 " + test.contains(2) + " [true]");
        System.out.println("Testing 3 " + test.contains(3) + " [true]");
        System.out.println("Testing 4 " + test.contains(4) + " [false]");
        System.out.println("Testing 5 " + test.contains(5) + " [true]");
    }
}