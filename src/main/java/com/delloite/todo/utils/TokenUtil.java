package com.delloite.todo.utils;

import java.nio.charset.StandardCharsets;
import java.security.DigestException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.delloite.todo.domain.User;

public class TokenUtil {
	private static String secret = "kinjal_todo_delloite";

	public static String generateToken(User user) {
		String token = null;
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			token = JWT.create().withIssuer("todo_" + user.getId())
					.withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 1000)).sign(algorithm);
		} catch (JWTCreationException exception) {
			return null;
		}
		return token;
	}

	public static Long verifyToken(String token) {
		Long userId = null;
		try {
			Algorithm algorithm = null;

			algorithm = Algorithm.HMAC256(secret);
			JWTVerifier verifier = JWT.require(algorithm).acceptLeeway(1).build();
			DecodedJWT dJwt = verifier.verify(token);

			System.out.println("Issuer:" + dJwt.getIssuer() + ", Audience:" + dJwt.getAudience() + ", Issued At:"
					+ dJwt.getIssuedAt());
			if (dJwt != null)
				userId = Long.parseLong(dJwt.getIssuer().split("_")[1]);

		} catch (JWTVerificationException exception) {
			userId = null;
			exception.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userId;
	}

	public static String decryptPassword(String password) {
		String secret = "Kinjal_Todo_Delloite";
		String decryptedText = null;
		byte[] cipherData = java.util.Base64.getDecoder().decode(password);
		byte[] saltData = Arrays.copyOfRange(cipherData, 8, 16);
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			final byte[][] keyAndIV = GenerateKeyAndIV(32, 16, 1, saltData, secret.getBytes(StandardCharsets.UTF_8),
					md5);
			SecretKeySpec key = new SecretKeySpec(keyAndIV[0], "AES");
			
			int maxKeyLen = Cipher.getMaxAllowedKeyLength("AES");
			
			IvParameterSpec iv = new IvParameterSpec(keyAndIV[1]);

			byte[] encrypted = Arrays.copyOfRange(cipherData, 16, cipherData.length);
			Cipher aesCBC = Cipher.getInstance("AES/CBC/PKCS5Padding");
			aesCBC.init(Cipher.DECRYPT_MODE, key, iv);
			byte[] decryptedData = aesCBC.doFinal(encrypted);
			decryptedText = new String(decryptedData, StandardCharsets.UTF_8);

			return decryptedText;
		} catch (Exception ex) {
			return decryptedText;
		}
	}

	public static byte[][] GenerateKeyAndIV(int keyLength, int ivLength, int iterations, byte[] salt, byte[] password,
			MessageDigest md) {

		int digestLength = md.getDigestLength();
		int requiredLength = (keyLength + ivLength + digestLength - 1) / digestLength * digestLength;
		byte[] generatedData = new byte[requiredLength];
		int generatedLength = 0;

		try {
			md.reset();

			// Repeat process until sufficient data has been generated
			while (generatedLength < keyLength + ivLength) {

				// Digest data (last digest if available, password data, salt if
				// available)
				if (generatedLength > 0)
					md.update(generatedData, generatedLength - digestLength, digestLength);
				md.update(password);
				if (salt != null)
					md.update(salt, 0, 8);
				md.digest(generatedData, generatedLength, digestLength);

				// additional rounds
				for (int i = 1; i < iterations; i++) {
					md.update(generatedData, generatedLength, digestLength);
					md.digest(generatedData, generatedLength, digestLength);
				}

				generatedLength += digestLength;
			}

			// Copy key and IV into separate byte arrays
			byte[][] result = new byte[2][];
			result[0] = Arrays.copyOfRange(generatedData, 0, keyLength);
			if (ivLength > 0)
				result[1] = Arrays.copyOfRange(generatedData, keyLength, keyLength + ivLength);

			return result;

		} catch (DigestException e) {

			throw new RuntimeException(e);

		} finally {
			// Clean out temporary data
			Arrays.fill(generatedData, (byte) 0);
		}
	}
}
