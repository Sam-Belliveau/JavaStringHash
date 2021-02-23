/**
 * For something like HashSet binning, theoretically CRC32 should be the most
 * optimal. Its collision resistance is very good, however the lookup table can
 * cause slowness on some machines. It's cryptographic resistance is not ideal,
 * however for circumstances such as binning, it is great.
 * 
 * If I had were to be responsible for updating the JVM implementation, it would
 * be using this function. This function is amazing for hashing any type of data
 * that can turn into a array of bytes.
 */
public class CRC32Hash implements IHash {
    private static final int POLYNOMIAL = 0xEDB88320;
    private static final int[] TABLE = new int[0x100];

    static {
        for (int i = 0; i < 0x100; ++i) {
            int result = i;

            for (int bit = 0; bit < 8; ++bit) {
                result = (result >>> 1) ^ ((result & 1) * (POLYNOMIAL));
            }

            TABLE[i] = result;
        }
    }

    public int hash(String in) {
        int state = 0;

        for (byte c : in.getBytes()) {
            state = (state >>> 8) ^ (TABLE[(state ^ c) & 0xff]);
        }

        return state;
    }
}
