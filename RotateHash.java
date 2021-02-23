public class RotateHash implements IHash {
    
    public int hash(String in) {
        int hpool = 0;

        for(byte c : in.getBytes()) {
            hpool = (hpool << 19) | (hpool >>> 13);
            hpool ^= c;
        }

        return hpool;
    }

}
