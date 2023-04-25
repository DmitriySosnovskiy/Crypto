import java.math.BigInteger
import java.security.SecureRandom
import java.util.*

class User {
    val publicKey = arrayOfNulls<BigInteger?>(2)
    private val secretKey = arrayOfNulls<BigInteger>(2)

    init {
        val p: BigInteger
        val q: BigInteger
        val euler_val: BigInteger
        var e: BigInteger
        val one = BigInteger.valueOf(1)

        // p и q это случайные простые числа с размером в битах bitLength
        val bitLength = 1024
        val rnd = SecureRandom()
        p = BigInteger.probablePrime(bitLength, rnd)
        q = BigInteger.probablePrime(bitLength, rnd)

        // n = p*q
        val n: BigInteger = p.multiply(q)

        // Ф(n) = (p - 1)*(q - 1)
        euler_val = p.subtract(one).multiply(q.subtract(one))

        // 1 < e < Ф(n),  gcd(e, Ф) = 1 , gcd = наибольший общий делитель
        e = BigInteger.valueOf(2)
        while (e.compareTo(euler_val) < 0 && euler_val.gcd(e) != one) {
            e = e.add(one)
        }

        // 1 < d < Ф, e*d = 1(mod Ф(n))
        // то есть
        // d = e^(-1) mod Ф(n)
        val d: BigInteger = e.modInverse(euler_val)
        publicKey[0] = e
        publicKey[1] = n
        secretKey[0] = d
        secretKey[1] = n
    }

    fun encrypt(publicKey: Array<BigInteger?>, message: String): Array<BigInteger?> {
        val m_chars = message.toCharArray()
        val encrypted_blocks = arrayOfNulls<BigInteger>(m_chars.size)
        for (i in m_chars.indices) {
            // c = m^e mod n
            encrypted_blocks[i] = BigInteger.valueOf(m_chars[i].code.toLong()).modPow(
                publicKey[0],
                publicKey[1]
            )
        }
        return encrypted_blocks
    }

    fun decrypt(message: Array<BigInteger?>): String {
        println("Секретный ключ [d, n]: " + Arrays.toString(secretKey))
        val m_chars = CharArray(message.size)
        val decrypted_str = StringBuilder()
        for (i in message.indices) {
            // m = c^d mod n
            m_chars[i] = message[i]?.modPow(secretKey[0], secretKey[1])?.toInt()?.toChar() ?: ' '
            decrypted_str.append(m_chars[i])
        }
        return decrypted_str.toString()
    }
}