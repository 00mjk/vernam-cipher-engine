# vernam-cipher-engine
Vernam Cipher Engine using Java streams to create a one time pad and enciphered value of same data type, length and sign as the original. Useful for when data types need to match data types of Java beans or database fields.

# Perfect Secrecy
Used by the US and USSR in the cold war, the Vernam Cipher was an unbreakable method of enciphering and deciphering messages. Unlike many 
common encryption methods, it doesn't use an algorithm to obscure the data or make it difficult to figure out. There isn't a common key or 
password which, once gained, will reveal all the data. It is considered "infomation-theoretically secure" - perfect secrecy.

Instead it uses a fast and simple method of generating random numbers and letters which are added to the original. If the result after 
addtion is found to be larger than the maximum value, the modulo of the total set size is used to wrap the results. To decipher, one simply subtracts the keys or one time pad from the enciphered value (see the wiki link below for more information).

Because the cipher is based on addition/subtraction, it is extremely fast in operation.

As a practical matter it means that data stored at rest is junk and random data. Without both the encrypted data and the keys, the data is
valueless. By storing the keys in a separate database, it thwarts hacking while making its use in any integration both simple
and fast. The only time the data exists is when it is reconstructed on the wire during processing. 

The current implementation uses Java SecureRandom for its random generator but plans are to add other connectors in the future for other
generators perhaps with connections to HotBits or other radioactive sources of randomness. Additionally it may well be that the stream
of random numbers used will come from multiple PRNGs to ensure no detectable patterns. During the encryption process, some random digits
are thrown out as a result of processing. Even if a weaker PRNG were used, the store for random one time pads would be informationally 
incomplete making it impossible to determine the random stream. Finally, it must be noted that the one time pads, after
generation, will be stored in a database which will result in generation order being lost which makes it impossible to reconstruct the stream. Even if the one time pads or their enciphered twin were published in clear text on the web, no useful data could be extracted - perfect secrecy.

"This is a very strong notion of security first developed during WWII by Claude Shannon and proved, mathematically, to be true for the 
one-time pad by Shannon about the same time. His result was published in the Bell Labs Technical Journal in 1949.
Properly used, one-time pads are secure in this sense even against adversaries with infinite computational power.

Claude Shannon proved, using information theory considerations, that the one-time pad has a property he termed perfect secrecy; 
that is, the ciphertext C gives absolutely no additional information about the plaintext This is because, given a truly 
random key that is used only once, a ciphertext can be translated into any plaintext of the same length, and all are equally likely. 
Thus, the a priori probability of a plaintext message M is the same as the a posteriori probability of a plaintext message M given 
the corresponding ciphertext"

https://en.wikipedia.org/wiki/One-time_pad
