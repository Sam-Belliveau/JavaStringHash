class XOR64Hash implements IHash {

    public int hash(String in) {
        long hpool = 0xDEADBEEFDEADBEEFL;

        for(byte c : in.getBytes()) {
            hpool ^= hpool << 13;
            hpool ^= hpool >>> 7;
            hpool ^= hpool << 17;
            hpool ^= ((long)c) << 24;
        }

        return (int)(0xffffffff & (hpool ^ (hpool >>> 32)));
    }

}