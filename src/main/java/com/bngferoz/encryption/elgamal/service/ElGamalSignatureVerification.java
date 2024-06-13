package com.bngferoz.encryption.elgamal.service;

import java.math.BigInteger;

public class ElGamalSignatureVerification {
    public static boolean verify(BigInteger message, BigInteger S1, BigInteger S2, BigInteger q, BigInteger a, BigInteger YA) {
        if (S1.compareTo(BigInteger.ONE) <= 0 || S1.compareTo(q.subtract(BigInteger.ONE)) >= 0) {
            return false;
        }
        if (S2.compareTo(BigInteger.ONE) <= 0 || S2.compareTo(q.subtract(BigInteger.ONE)) >= 0) {
            return false;
        }


        BigInteger v1 = a.pow(message.intValue()).mod(q);
        BigInteger v2 = YA.pow(S1.intValue()).multiply(S1.pow(S2.intValue())).mod(q);

        return v1.equals(v2);
    }
}

