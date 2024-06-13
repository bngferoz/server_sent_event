package com.bngferoz.encryption.elgamal.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Output {
	private String publicKey;
	private String[] signature;
}
