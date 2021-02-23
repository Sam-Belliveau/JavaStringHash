/**
 * This is the standard JVM implementation of a string hash. In my opinion, this
 * is a horrible way to do it. 31 is a prime number, but it is small, and doesnt
 * provide much randomness to the string. I made improvements to
 * BetterCommonHash, which is very similar in construction, yet performs
 * significantly better.
 */
public class CommonHash implements IHash {

    public int hash(String in) {
        int hash = 0;

        for (byte c : in.getBytes()) {
            hash = 31 * hash + c;
        }

        return hash;
    }

}
