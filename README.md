
# Java String Hash Colision Tester

This project is a collection of a few different hash functions that are tested for colisions.

`CommonHash.java` is usually the same hash as the default hash, however, it is not guaranteed depending on the java runtime.

When looking at the results, you want to ideally have as few colisions as possible, while having as many unique values as possible. Speed is also a factor, however this **is not** a utility to test speed, as fewer colisions means higher memory usage. This is usually not the case, however for this test, better hashes need more memory.

All of these hashes are supposed to be very simple hashes, that are used for binning in HashSets. Hashes for HashSets require low compute, and high colision resistance.

Running this simuation takes a large amount of memory, as checking for coliding hashes is very memory intensive.

The strings are just a representation of an integer in base 36. This is basically just a bunch of random strings.

## Results 

```
DefaultHash:
        # of Colisions:    68,487,010 / 100,000,000
        # of Unique Hashs: 31,512,990 / 100,000,000

CommonHash:
        # of Colisions:    68,487,010 / 100,000,000
        # of Unique Hashs: 31,512,990 / 100,000,000

XOR32Hash: 
        # of Colisions:    116,000 / 100,000,000   
        # of Unique Hashs: 99,884,000 / 100,000,000

XOR64Hash: 
        # of Colisions:    20 / 100,000,000        
        # of Unique Hashs: 99,999,980 / 100,000,000

RotateHash: 
        # of Colisions:    65,330,113 / 100,000,000
        # of Unique Hashs: 34,669,887 / 100,000,000
```