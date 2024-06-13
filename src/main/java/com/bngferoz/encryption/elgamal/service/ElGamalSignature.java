package com.bngferoz.encryption.elgamal.service;

import java.math.BigInteger;

import java.security.*;

public class ElGamalSignature {
	public static String[] sign(BigInteger message, BigInteger q, BigInteger a, BigInteger privateKey) {
        SecureRandom random = new SecureRandom();
        BigInteger k = new BigInteger(q.bitLength() - 1, random);

        BigInteger S1 = a.pow(k.intValue()).mod(q);

        BigInteger kInverse = k.modInverse(q.subtract(BigInteger.ONE));

        BigInteger S2 = (message.subtract(privateKey.multiply(S1))).multiply(kInverse).mod(q.subtract(BigInteger.ONE));

        return new String[]{S1.toString(), S2.toString()};
    }

}
