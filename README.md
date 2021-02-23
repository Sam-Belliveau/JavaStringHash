
# Java String Hash Colision Tester

This project is a collection of a few different hash functions that are tested for colisions.

`CommonHash.java` is usually the same hash as the JVM, however it is not certain.

When looking at the results, ideally you have as few colisions as possible. Speed is also a factor, however this **is not** a utility to test speed, as fewer colisions causes the test to use more memory, slowing it down. This is not a real world condition, so speed can be ignored.

Hashes demonstrated here are **NOT** cryptographically secure, and should be used strictly for binning. Hashes for HashSets require low compute, and high colision resistance.


This program contains two tests. 

- The first test tests many sequential strings. Startings with `0` then `1`, then eventually `00` `01`. It counts up in base 36, so you should get many different characters in the test. This test is really good at showing the avalanche effect of certain hashes.

- the second test tests many random strings, usually with the length of 12 / 13 characters. This should give very different results as the amount of entropy in the string is greatly higher. The results should be similar among all of the hashes for this one, as long as there is no major flaw.

## Results 

```
+-------------------------+
| Sequential Strings Test |
+-------------------------+
DefaultHash:    6,422,174 / 10,000,000 colisions
CommonHash:     6,422,174 / 10,000,000 colisions
RotateHash:     5,512,304 / 10,000,000 colisions
BetterCommonHash:       0 / 10,000,000 colisions
XOR32Hash:      0 / 10,000,000 colisions
XOR64Hash:      0 / 10,000,000 colisions
MurmurHash:     10,917 / 10,000,000 colisions
CRC32Hash:      0 / 10,000,000 colisions
+---------------------+
| Random Strings Test |
+---------------------+
DefaultHash:    11,573 / 10,000,000 colisions
CommonHash:     11,573 / 10,000,000 colisions
RotateHash:     12,972 / 10,000,000 colisions
BetterCommonHash:       12,132 / 10,000,000 colisions
XOR32Hash:      11,425 / 10,000,000 colisions
XOR64Hash:      11,563 / 10,000,000 colisions
MurmurHash:     11,575 / 10,000,000 colisions
CRC32Hash:      11,559 / 10,000,000 colisions
```

Now it may appear that `BetterCommonHash` and `XOR64Hash` performed better than `MurmurHash` in the first test, and this is true. However that does not make it a better hash. Notice how `MurmurHash` performs similarly in the random test to how it does in the sequential test. This means that the results from MurmurHash are not related to the type of data you give it. This may be desirable, however Murmur hash tends to be too slow in java to be considered anyways.

While `BetterCommonHash` and `XOR64Hash` performed well, I think the real winner here in `CRC32Hash` as CRC32 has been backed by numerous papers and proofs. CRC is also very fast, as its implementation is quite simple. I think that JVMs should switch their implementation over to CRC32, as it performs significantly better. However if they need to stick to the same hash structure as before, `BetterCommonHash` prooves itself to be a worthy contender.