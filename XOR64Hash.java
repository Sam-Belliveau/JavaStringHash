/**
 * This is a very simple hashing algorithm that I threw together that is based
 * off of the XOR shift random number generator. XOR shift has proven itself to
 * be a good RNG, and so this is based on that. The pool size for this is 64
 * bits, and after it is shifted around, the next character in the string is
 * added to the pool. At the very end, the pool is shortended to 32 bits. When
 * adding characters, you have to make sure that you dont add it to the same
 * place in the two 32 bit sections of the 64 bit int, because otherwise you
 * will lose a lot of entropy when combining down to 32 bits. There is no
 * research done with this hash, I just thought of it, and it has some nice
 * properties. XORShift benifits from having a larger pool, as the pool is what
 * stores entropy. Ideally the XORShift pool is larger than the output, as
 * otherwise the mixing doesn't have much space.
 */
class XOR64Hash implements IHash {

    // This is some random 64 bit prime
    static final long INITIAL_SEED = 0xc4ceb9fe1a85ec53L;

    public int hash(String in) {
        long hpool = INITIAL_SEED;

        for (byte c : in.getBytes()) {
            hpool ^= hpool << 13;
            hpool ^= hpool >>> 7;
            hpool ^= hpool << 17;
            hpool ^= c * 0x00_00_01_00_00_01_00_00L;
        }

        return (int) (hpool ^ (hpool >>> 32));
    }

}