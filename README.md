
# Java String Hash Collision Tester

This project is a collection of a few different hash functions that are tested for collisions.

`CommonHash.java` is usually the same hash as the JVM, however it is not certain.

When looking at the results, ideally you have as few collisions as possible. Speed is also a factor, however this **is not** a utility to test speed, as fewer collisions causes the test to use more memory, slowing it down. This is not a real world condition, so speed can be ignored.

Hashes demonstrated here are **NOT** cryptographically secure, and should be used strictly for binning. Hashes for HashSets require low compute, and high collision resistance.


This program contains two tests. 

- The first test tests many sequential strings. Startings with `0` then `1`, then eventually `00` `01`. It counts up in base 36, so you should get many different characters in the test. This test is really good at showing the avalanche effect of certain hashes.

- the second test tests many random strings, usually with the length of 12 / 13 characters. This should give very different results as the amount of entropy in the string is greatly higher. The results should be similar among all of the hashes for this one, as long as there is no major flaw.

## Results 

```
+-------------------------+
| Sequential Strings Test |
+-------------------------+
DefaultHash:    68,487,010 / 100,000,000 collisions
CommonHash:     68,487,010 / 100,000,000 collisions
RotateHash:     65,330,113 / 100,000,000 collisions
BetterCommonHash:       394,560 / 100,000,000 collisions
XOR32Hash:      1,326,600 / 100,000,000 collisions
XOR64Hash:      0 / 100,000,000 collisions
MurmurHash:     1,142,520 / 100,000,000 collisions
OneAtATimeHash: 3,315,424 / 100,000,000 collisions
CRC32Hash:      5,137,625 / 100,000,000 collisions
+---------------------+
| Random Strings Test |
+---------------------+
DefaultHash:    1,294,466 / 100,000,000 collisions
CommonHash:     1,294,466 / 100,000,000 collisions
RotateHash:     1,292,966 / 100,000,000 collisions
BetterCommonHash:       1,199,276 / 100,000,000 collisions
XOR32Hash:      1,154,663 / 100,000,000 collisions
XOR64Hash:      1,153,252 / 100,000,000 collisions
MurmurHash:     1,155,719 / 100,000,000 collisions
OneAtATimeHash: 1,155,967 / 100,000,000 collisions
CRC32Hash:      1,154,097 / 100,000,000 collisions
```

Now it may appear that `BetterCommonHash` and `XOR64Hash` performed better than `MurmurHash` in the first test, and this is true. However that does not make it a better hash. Notice how `MurmurHash` performs similarly in the random test to how it does in the sequential test. This means that the results from MurmurHash are not related to the type of data you give it. This may be desirable, however Murmur hash tends to be too slow in java to be considered anyways.

`XOR64Hash` performed unexpectedly amazingly, even better than `CRC32Hash`, `MurmurHash`, or even `OneAtATimeHash`. All 3 of those hashes are well proven to be great, while `XOR64Hash` is some random hash I threw together, however as shown by these tests, it performs amazing. This is great because it is also one of the faster hashes in comparison to the other 3. Because of this, I would say that `XOR64Hash` is the winner here. However, if I got to chose what the JVM would switch too, I would pick `CRC32Hash` as it is the most proven of them all, and also the most common.