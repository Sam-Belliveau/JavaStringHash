
/**
 * https://en.wikipedia.org/wiki/Fowler%E2%80%93Noll%E2%80%93Vo_hash_function
 */
class FNV1a64 implements IHash {

	 static final long OFFSET = 0xcbf29ce484222325L;
	 static final long PRIME = 0x100000001b3L;

  public int hash(String in) {
      long hash = OFFSET;

      for (byte c : in.getBytes()) {
         hash = (hash ^ (c & 0xFFL)) * PRIME;
      }

      return (int) (hash ^ (hash >>> 32));
  }

}