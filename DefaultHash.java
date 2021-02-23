/**
 * This is just the hash that java uses. The majority of the time it is
 * identical to CommonHash.java
 */
class DefaultHash implements IHash {

    public int hash(String in) {
        return in.hashCode();
    }

}