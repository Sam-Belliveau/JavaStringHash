public class CommonHash implements IHash {
    
    public int hash(String in) {
        int hash = 21;

        for(byte c : in.getBytes()) {
            hash = 31 * hash + c;
        }

        return hash;
    }

}
