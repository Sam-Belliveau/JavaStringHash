/**
 * This is not exactly the same implementation as murmur hash. The way it
 * sections bytes is slightly different. But it should have similar results so
 * it does not matter. It is not speed effiecent, but it is simple for
 * demonstrations. This is commonly used for hashing strings, as its fast, and
 * it has relatively good properties.
 */
public class MurmurHash implements IHash {

    // This is some random 32 bit prime number
    static final int INITIAL_SEED = 0x85ebca6b;

    static int scramble(int k) {
        k *= 0xcc9e2d51;
        k = (k << 15) | (k >>> 17);
        k *= 0x1b873593;
        return k;
    }

    static int[] breakUp(byte[] in) {
        int p = 0;
        int[] out = new int[(in.length + 3) / 4];

        for (int i = 0; i < in.length; ++i) {
            out[p] <<= 8;
            out[p] |= in[i];
            if ((i & 0b11) == 0b11)
                p += 1;
        }

        return out;
    }

    public int hash(String in) {
        int hpool = INITIAL_SEED;

        for (int c : breakUp(in.getBytes())) {
            hpool ^= scramble(c);
            hpool = (hpool << 13) | (hpool >>> 19);
            hpool = hpool * 5 + 0xe6546b64;
        }

        hpool ^= in.length();
        hpool ^= hpool >>> 16;
        hpool *= 0x85ebca6b;
        hpool ^= hpool >>> 13;
        hpool *= 0xc2b2ae35;
        hpool ^= hpool >>> 16;

        return hpool;
    }
}
