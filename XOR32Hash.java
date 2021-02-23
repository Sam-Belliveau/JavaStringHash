/**
 * This is a very simple hashing algorithm that I threw together that is based
 * off of the XOR shift random number generator. XOR shift has proven itself to
 * be a good RNG, and so this is based on that. The pool size for this is 32
 * bits, and after it is shifted around, the next character in the string is
 * added to the pool. There is no research done with this hash, I just thought
 * of it, and it has some nice properties
 */
class XOR32Hash implements IHash {

    // This is some random 32 bit prime number
    static final int INITIAL_SEED = 0x85ebca6b;

    public int hash(String in) {
        int hpool = INITIAL_SEED;

        for (byte c : in.getBytes()) {
            hpool ^= hpool << 13;
            hpool ^= hpool >>> 17;
            hpool ^= hpool << 5;
            hpool ^= c * 0x00_01_00_01;
        }

        return hpool;
    }

}