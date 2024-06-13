package com.bngferoz.encryption.elgamal.controller;


import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.bngferoz.encryption.elgamal.dto.Output;
import com.bngferoz.encryption.elgamal.dto.Message;
import com.bngferoz.encryption.elgamal.service.ElGamalKeyPair;
import com.bngferoz.encryption.elgamal.service.ElGamalSignatureVerification;
import com.bngferoz.encryption.elgamal.service.ElgamalEncryptionService;

import lombok.val;


@Controller
public class EncryptionController {
	
	@Autowired
	ElgamalEncryptionService elgamalEncryptionService;
	
	@MessageMapping("/chat")
	@SendTo("/topic/greetings")
	public Output greet(Message message) throws InterruptedException {
		Thread.sleep(2000);
		
		Output returnThis =  elgamalEncryptionService.elgamal(message.getMessage());
		return returnThis;
	}
	
	@MessageMapping("/verify")
	public String[] verify(Message message) throws InterruptedException {
		
		String[] values = null;
		if(message!=null && message.getMessage()!=null) {
			String string = message.getMessage();
			values = string.split("@");
		}
		String keys[] = values[0].split(",");
		ElGamalKeyPair keyPair = new ElGamalKeyPair();
		keyPair.setQ(new BigInteger(keys[0]));
		keyPair.setA(new BigInteger(keys[1]));
		keyPair.setYA(new BigInteger(keys[2]));
		String[] signature = values[1].split(",");
		BigInteger m = new BigInteger(values[2]);
		boolean isVerified = ElGamalSignatureVerification.verify(m, new BigInteger(signature[0]), new BigInteger(signature[1]), keyPair.getQ(), keyPair.getA(), keyPair.getYA());

		return values;
	}
}
