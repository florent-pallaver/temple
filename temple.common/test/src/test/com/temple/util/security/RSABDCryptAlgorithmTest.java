package test.com.temple.util.security;

import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.temple.util.TempleUtil;
import com.temple.util.security.KeySize;
import com.temple.util.security.RSABDCryptAlgorithm;
import com.temple.util.security.Security;

@RunWith(Parameterized.class)
public class RSABDCryptAlgorithmTest {

	@Parameter
	public String test;

	@Parameters
	public static String[] data() {
		return new String[] { "fuck Ya !", "01232454I5436T05316429", new String(Security.randomBytes(32)), new String(Security.randomChars(32)),
				"fuckYa abcdefghijklmnopqurstuvwxyz abcdefghijklmnopqurstuvwxyz abcdefghijklmnopqurstuvwxyz abcdefghijklmnopqurstuvwxyz abcdefghijklmnopqurstuvwxyz &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= &é\"'(-è_çà)= ! " };
	}

	@Test
	public void test() throws UnsupportedEncodingException {
		final RSABDCryptAlgorithm bdAlgorithm = new RSABDCryptAlgorithm(KeySize._1024);

		final byte[] decrypt = bdAlgorithm.decrypt(bdAlgorithm.encrypt(this.test));
		Assert.assertArrayEquals(this.test.getBytes(), decrypt);
		final String decrypt64 = TempleUtil.base64Encode(bdAlgorithm.decrypt64(bdAlgorithm.encrypt64(this.test)));
		Assert.assertEquals(TempleUtil.base64Encode(this.test), decrypt64);
	}

}
