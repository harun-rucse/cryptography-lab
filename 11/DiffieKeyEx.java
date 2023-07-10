import java.math.BigInteger;

public class DiffieKeyEx {
    private static BigInteger calculate_key(BigInteger primitive_root, BigInteger private_key, BigInteger prime) {
        return primitive_root.modPow(private_key, prime);
    }

    public static void main(String[] args) {
        BigInteger prime, primitive_root, Xa, Xb, Ya, Yb, Ka, Kb;

        prime = BigInteger.valueOf(353);
        System.out.println("The value of prime: " + prime);

        primitive_root = BigInteger.valueOf(3);
        System.out.println("The value of primitive root: " + primitive_root);

        Xa = BigInteger.valueOf(97);
        System.out.println("The private key Xa for Alice: " + Xa);

        Ya = calculate_key(primitive_root, Xa, prime);

        Xb = BigInteger.valueOf(233);
        System.out.println("The private key Xb for Bob: " + Xb);

        Yb = calculate_key(primitive_root, Xb, prime);

        Ka = calculate_key(Yb, Xa, prime);
        Kb = calculate_key(Ya, Xb, prime);

        System.out.println("Secret key for Alice: " + Ka);
        System.out.println("Secret key for Bob: " + Kb);
    }
}
