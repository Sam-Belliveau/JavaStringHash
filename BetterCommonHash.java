/**
 * This hash function is almost identical to the CommonHash function that can be
 * found in the JVM. However this one uses a larger prime number that was used
 * in MurmurHash. This simple change leads to a significant drop in collisions. I
 * do not know why the JVM still uses the bad hashing algorithm it does.
 */
public class BetterCommonHash implements IHash {

    public int hash(String in) {
        int hash = 0;

        for (byte c : in.getBytes()) {
            hash = 0xcc9e2d51 * hash + c;
        }

        return hash;
    }

}
