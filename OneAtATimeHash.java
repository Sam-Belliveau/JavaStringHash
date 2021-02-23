/**
 * One At A Time hash is part of the Jenkin Hash SFunctions, which are designed specifically for things like binning. It has very good avelanching / collision resistance, and is very fast.
 */
public class OneAtATimeHash implements IHash {
    
    public int hash(String in) {
        int hash = 0;

        for(byte b : in.getBytes()) {
            hash += b;
            hash += hash << 10;
            hash ^= hash >>> 6;
        }

        hash += hash << 3;
        hash ^= hash >>> 11;
        hash += hash << 15;

        return hash;
    }

}
