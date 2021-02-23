class DefaultHash implements IHash {

    public int hash(String in) {
        return in.hashCode();
    }

}