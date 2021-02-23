/**
 * This hash is very simple, however, it performs unusually well. I am not sure
 * what the reason for this is, but despite how simple this one is, it manages a
 * shockingly low number of collisions, even in comparison to CRC32Hash.
 * 
 * This is probably the best hash due to its speed and colision resistance.
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