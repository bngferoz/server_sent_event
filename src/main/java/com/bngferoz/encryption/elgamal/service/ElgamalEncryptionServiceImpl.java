package com.bngferoz.encryption.elgamal.service;

import java.math.BigInteger;

import org.springframework.stereotype.Service;

import com.bngferoz.encryption.elgamal.dto.Output;


@Service
public class ElgamalEncryptionServiceImpl implements ElgamalEncryptionService {

	@Override
	public Output elgamal(String s) {
		BigInteger q = new BigInteger("19"); // Replace with a large prime number
        BigInteger a = new BigInteger("10");  // Replace with a suitable generator

        ElGamalKeyPair keyPair = new ElGamalKeyPair(q, a);
        BigInteger message = new BigInteger("6"); // Replace with the message to be signed

        String[] signature = ElGamalSignature.sign(message, keyPair.getQ(), keyPair.getA(), keyPair.getXA());

        //boolean isVerified = ElGamalSignatureVerification.verify(message, signature[0], signature[1], keyPair.getP(), keyPair.getG(), keyPair.getY());

        //System.out.println("Is Signature Verified: " + isVerified);
        return new Output(keyPair.getQ().toString()+","+keyPair.getA().toString()+","+keyPair.getYA().toString()
        		,signature);
	}
}
