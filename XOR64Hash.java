/**
 * This is just a very simple of a 64 bit XOR shift, with amazing results. It
 * avalances very well, and the results are almost perfectly random. The 32 bit
 * version performs just as well as the 64 version, however this may be a
 * stronger hash due to its bigger entropy pool. This hash performs just as well
 * in the random test as it does in the sequential test, meaning that it
 * provides just as random results regardless of the input string.
 * 
 * This hash needs some more research to be done into it, because its
 * performance is promising.
 */
class XOR64Hash implements IHash {

    // This is some random 64 bit prime
    static final long INITIAL_SEED = 0xc4ceb9fe1a85ec53L;
    static final long INPUT_MULT = 0xff51afd7ed558ccdL;

    public int hash(String in) {
        long hpool = INITIAL_SEED;

        for (byte c : in.getBytes()) {
            hpool ^= hpool << 13;
            hpool ^= hpool >>> 7;
            hpool ^= hpool << 17;
            hpool ^= c * INPUT_MULT;
        }

        hpool ^= hpool >>> 32;

        return (int) hpool;
    }

}