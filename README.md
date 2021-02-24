
# Java String Hash Collision Tester

This project is a collection of a few different hash functions that are tested for collisions.

`CommonHash.java` is usually the same hash as the JVM, however it is not certain.

When looking at the results, ideally you have as few collisions as possible. Speed is also a factor, however this **is not** a utility to test speed, as fewer collisions causes the test to use more memory, slowing it down. This is not a real world condition, so speed can be ignored.

Hashes demonstrated here are **NOT** cryptographically secure, and should be used strictly for binning. Hashes for HashSets require low compute, and high collision resistance.


This program contains two tests. 

- The first test tests many sequential strings. Startings with `0` then `1`, then eventually `00` `01`. It counts up in base 36, so you should get many different characters in the test. This test is really good at showing the avalanche effect of certain hashes.

- the second test tests many random strings, usually with the length of 12 / 13 characters. This should give very different results as the amount of entropy in the string is greatly higher. The results should be similar among all of the hashes for this one, as long as there is no major flaw.


It is important to realize that many of the collisions happen towards the end of the test, when many of the hashes have been used up. So just because some hash has 10% collision rate when testing 1,000,000,000 hashes, doesn't make it bad. In fact, 10% collision at 1,000,000,000 is very good.

## 1M Results

```
+-------------------------+
| Sequential Strings Test |
+-------------------------+
DefaultHash:    565,970 / 1,000,000 collisions
CommonHash:     565,970 / 1,000,000 collisions
RotateHash:     433,440 / 1,000,000 collisions
BetterCommonHash:       0 / 1,000,000 collisions
XOR32Hash:      112 / 1,000,000 collisions
XOR64Hash:      228 / 1,000,000 collisions
MurmurHash:     6 / 1,000,000 collisions
OneAtATimeHash: 4,880 / 1,000,000 collisions
CRC32Hash:      0 / 1,000,000 collisions
+---------------------+
| Random Strings Test |
+---------------------+
DefaultHash:    138 / 1,000,000 collisions
CommonHash:     138 / 1,000,000 collisions
RotateHash:     133 / 1,000,000 collisions
BetterCommonHash:       104 / 1,000,000 collisions
XOR32Hash:      136 / 1,000,000 collisions
XOR64Hash:      101 / 1,000,000 collisions
MurmurHash:     138 / 1,000,000 collisions
OneAtATimeHash: 119 / 1,000,000 collisions
CRC32Hash:      105 / 1,000,000 collisions
```

## 10M Results

```
+-------------------------+
| Sequential Strings Test |
+-------------------------+
DefaultHash:    6,422,174 / 10,000,000 collisions
CommonHash:     6,422,174 / 10,000,000 collisions
RotateHash:     5,512,304 / 10,000,000 collisions
BetterCommonHash:       0 / 10,000,000 collisions
XOR32Hash:      11,743 / 10,000,000 collisions
XOR64Hash:      11,396 / 10,000,000 collisions
MurmurHash:     10,917 / 10,000,000 collisions
OneAtATimeHash: 136,286 / 10,000,000 collisions
CRC32Hash:      0 / 10,000,000 collisions
+---------------------+
| Random Strings Test |
+---------------------+
DefaultHash:    11,573 / 10,000,000 collisions
CommonHash:     11,573 / 10,000,000 collisions
RotateHash:     12,972 / 10,000,000 collisions
BetterCommonHash:       12,132 / 10,000,000 collisions
XOR32Hash:      11,732 / 10,000,000 collisions
XOR64Hash:      11,628 / 10,000,000 collisions
MurmurHash:     11,575 / 10,000,000 collisions
OneAtATimeHash: 11,833 / 10,000,000 collisions
CRC32Hash:      11,559 / 10,000,000 collisions
```

## 100M Results 

```
+-------------------------+
| Sequential Strings Test |
+-------------------------+
DefaultHash:    68,487,010 / 100,000,000 collisions
CommonHash:     68,487,010 / 100,000,000 collisions
RotateHash:     65,330,113 / 100,000,000 collisions
BetterCommonHash:       394,560 / 100,000,000 collisions
XOR32Hash:      1,151,350 / 100,000,000 collisions
XOR64Hash:      1,164,570 / 100,000,000 collisions
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
XOR32Hash:      1,152,509 / 100,000,000 collisions
XOR64Hash:      1,155,975 / 100,000,000 collisions
MurmurHash:     1,155,719 / 100,000,000 collisions
OneAtATimeHash: 1,155,967 / 100,000,000 collisions
CRC32Hash:      1,154,097 / 100,000,000 collisions
```

# 1B Results

```
+-------------------------+
| Sequential Strings Test |
+-------------------------+
DefaultHash:    753,298,210 / 1,000,000,000 collisions
CommonHash:     753,298,210 / 1,000,000,000 collisions
RotateHash:     916,378,192 / 1,000,000,000 collisions
BetterCommonHash:       175,836,937 / 1,000,000,000 collisions
XOR32Hash:      107,682,462 / 1,000,000,000 collisions
XOR64Hash:      107,591,746 / 1,000,000,000 collisions
MurmurHash:     107,791,155 / 1,000,000,000 collisions
OneAtATimeHash: 126,017,834 / 1,000,000,000 collisions
CRC32Hash:      91,522,676 / 1,000,000,000 collisions
+---------------------+
| Random Strings Test |
+---------------------+
DefaultHash:    111,632,755 / 1,000,000,000 collisions
CommonHash:     111,632,755 / 1,000,000,000 collisions
RotateHash:     118,619,874 / 1,000,000,000 collisions
BetterCommonHash:       108,173,606 / 1,000,000,000 collisions
XOR32Hash:      107,879,429 / 1,000,000,000 collisions
XOR64Hash:      107,886,104 / 1,000,000,000 collisions
MurmurHash:     107,882,341 / 1,000,000,000 collisions
OneAtATimeHash: 107,892,546 / 1,000,000,000 collisions
CRC32Hash:      107,883,254 / 1,000,000,000 collisions
```

# What these results mean

It may appear from these results that `CRC32Hash` and `BetterCommonHash` perform better in the sequential tests with small numbers. This is because the string is really short, and it is able to perfectly avoid collisions. However, hashes like `XOR32Hash` and `XOR64Hash` perform amazingly at all sizes, independent of sequential or random strings. This is means that these hashes are very good at generating random keys, regardless of their inputs. This is very impressive, considering that these XOR hashes are stupid fast. 

When looking at the results, its notable that almost all of the hashes did similarly in the random test. This is because the inputs to the hashes were so large and random that any semi decent hash would be able to output a truely random result. What is notable of a good hash is if they perform as well in the sequential test as they do in the random test. Even if that means they have some collisions the sequential 1M test. 

Even though `BetterCommonHash` performs better in the sequential test <= 100M, it performs significantly worse in the 1B test. This is because it is not amazing at generating random hashes once you give it large enough strings. Meanwhile, the XOR Hashes are always performing good.