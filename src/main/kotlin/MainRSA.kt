import java.math.BigInteger

object MainRSA {
//    @JvmStatic
//    fun main(args: Array<String>) {
//        RSA()
//    }

    fun RSA() {
        val alice = User()
        val bob = User()
        println("1ый шифрует сообщение")
        val message = "[Это текст зашифрованного сообщения"
        println("1ый получил публичный ключ 2го [e, n]: " + alice.publicKey.contentToString())
        println("Исходный текст: $message")
        val crypted_bigInt: Array<BigInteger?> = bob.encrypt(alice.publicKey, message)
        val sb = StringBuilder()
        for (value in crypted_bigInt) {
            sb.append(value)
        }
        val crypted_string = sb.toString()
        println("Зашифрованное сообщение: $crypted_string")
        println()
        println("2ой расшифровывает сообщение")
        val decrypted: String = alice.decrypt(crypted_bigInt)
        println("Расшифрованное сообщение: $decrypted")
    }
}