package com.temple.util.security;

import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.temple.util.TempleUtil;
import com.temple.util.security.AESBDCryptAlgorithm;
import com.temple.util.security.BDCryptAlgorithm;
import com.temple.util.security.KeySize;
import com.temple.util.security.RSABDCryptAlgorithm;
import com.temple.util.security.Security;

@RunWith(Parameterized.class)
public class BDCryptAlgorithmTest {

	@Parameter
	public String test;

	@Parameters
	public static String[] data() {
		return new String[] { "fuck Ya !", "01232454I5436T05316429", new String(Security.randomBytes(32)), new String(Security.randomChars(32)),
		"fuckYa abcdefghijklmnopqurstuvwxyz abcdefghijklmnopqurstuvwxyz abcdefghijklmnopqurstuvwxyz abcdefghijklmnopqurstuvwxyz abcdefghijklmnopqurstuvwxyz &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= ! " };
	}

	@Test
	public void testRSA() throws UnsupportedEncodingException {
		this.test(new RSABDCryptAlgorithm(KeySize._1024)) ;
	}

	@Test
	public void testAES() throws UnsupportedEncodingException {
		this.test(new AESBDCryptAlgorithm(KeySize._128));
	}

	private void test(final BDCryptAlgorithm bdAlgorithm) {
		final byte[] decrypt = bdAlgorithm.decrypt(bdAlgorithm.encrypt(this.test));
		Assert.assertArrayEquals(this.test, this.test.getBytes(), decrypt);
		final String decrypt64 = TempleUtil.base64Encode(bdAlgorithm.decrypt64(bdAlgorithm.encrypt64(this.test)));
		Assert.assertEquals(this.test, TempleUtil.base64Encode(this.test), decrypt64);
	}

}
