package utils;

import java.security.SecureRandom;
import java.util.Random;

public class SaltGenerator {

	private static final Random RANDOM = new SecureRandom();

	public static String getNextSalt() {
		byte[] salt = new byte[16];
		RANDOM.nextBytes(salt);
		return new sun.misc.BASE64Encoder().encode(salt);
	}
}
