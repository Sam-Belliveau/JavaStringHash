class XOR32Hash implements IHash {

    public int hash(String in) {
        int hpool = 0xDEADBEEF;

        for(byte c : in.getBytes()) {
            hpool ^= hpool << 13;
            hpool ^= hpool >>> 17;
            hpool ^= hpool << 5;
            hpool ^= ((int)c) << 8;
        }

        return hpool;
    }

}