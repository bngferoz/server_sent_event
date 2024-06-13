package com.bngferoz.encryption.elgamal.service;
import java.math.BigInteger;
import java.security.SecureRandom;


public class ElGamalKeyPair {
    private BigInteger q;  // Large prime number
    private BigInteger a;  // Generator
    private BigInteger XA;  // Private key
    private BigInteger YA;  // Public key

    public ElGamalKeyPair() {
       
    }
    
    public ElGamalKeyPair(BigInteger q, BigInteger a) {
        this.q = q;
        this.a = a;
        generateKeys();
    }

    private void generateKeys() {
        SecureRandom random = new SecureRandom();
        //XA = new BigInteger(q.bitLength() - 1, random);
        XA = new BigInteger("16");
        YA = a.modPow(XA, q);
    }

    public BigInteger getQ() {
        return q;
    }

    public BigInteger getA() {
        return a;
    }

    public BigInteger getXA() {
        return XA;
    }

    public BigInteger getYA() {
        return YA;
    }
    
    public void setQ(BigInteger q) {
        this.q = q;
    }

    public void setA(BigInteger a) {
        this.a = a;
    }

    public void setXA(BigInteger XA) {
        this.XA = XA;
    }

    public void setYA(BigInteger YA) {
        this.YA = YA;
    }
}
