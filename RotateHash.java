/**
 * This hash is bad. It is slightly better than the one the JVM uses, but it is
 * still a bad hash. It just rotates the pool around, and xors the byte in.
 */
public class RotateHash implements IHash {

    public int hash(String in) {
        int hpool = 0;

        for (byte c : in.getBytes()) {
            hpool = (hpool << 19) | (hpool >>> 13);
            hpool ^= c;
        }

        return hpool;
    }

}
