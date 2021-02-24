/**
 * This is just a very simple of a 32 bit XOR shift, with amazing results. It
 * avalances very well, and the results are almost perfectly random. This 32 bit
 * version performs just as well as the 64 version. This hash performs just as
 * well in the random test as it does in the sequential test, meaning that it
 * provides just as random results regardless of the input string.
 * 
 * This hash needs some more research to be done into it, because its
 * performance is promising.
 */
class XOR32Hash implements IHash {

    // This is some random 32 bit prime number
    static final int INITIAL_SEED = 0x85ebca6b;
    static final long INPUT_MULT = 0xcc9e2d51;

    public int hash(String in) {
        int hpool = INITIAL_SEED;

        for (byte c : in.getBytes()) {
            hpool ^= hpool << 13;
            hpool ^= hpool >>> 17;
            hpool ^= hpool << 5;
            hpool ^= c * INPUT_MULT;
        }

        return hpool;
    }

}