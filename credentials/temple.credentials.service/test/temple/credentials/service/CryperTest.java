package temple.credentials.service;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.temple.credentials.model.UserIdentity;
import com.temple.credentials.service.IncorrectPassException;
import com.temple.util.TempleUtil;
import com.temple.util.security.Security;

@RunWith(Parameterized.class)
public class CryperTest {

	private final String login = "flominou";

	@Parameter
	public String rawPass;

	@Parameters
	public static Collection<String> data() {
		return Arrays.asList("fuckYa !", "1234567890", "é\"'(-è_çà", "kj8_i'à@", "a", "bbbbbbbbbbbb");
	}

	@Test
	public void test() throws IncorrectPassException {
		final int length = this.rawPass.length();
		final UserIdentity ui = this.createIdentity();
		Assert.assertTrue(ui.getEncryptedPass().equals(this.crypt(ui.getLogin(), ui.getSalt(), this.rawPass)));
		Assert.assertFalse(ui.getEncryptedPass().equals(this.crypt(ui.getLogin(), ui.getSalt(), "")));
		Assert.assertFalse(ui.getEncryptedPass().equals(this.crypt(ui.getLogin(), ui.getSalt(), this.rawPass.substring(TempleUtil.random(1, length - 1)))));
		Assert.assertFalse(ui.getEncryptedPass().equals(this.crypt(ui.getLogin(), ui.getSalt(), this.rawPass + this.rawPass)));
		Assert.assertFalse(ui.getEncryptedPass().equals(this.crypt(ui.getLogin(), ui.getSalt(), new String(Security.randomBytes(length)))));
		Assert.assertFalse(ui.getEncryptedPass().equals(this.crypt(ui.getLogin(), ui.getSalt(), new String(Security.randomChars(length)))));
	}

	public UserIdentity createIdentity() {
		final String salt = TempleUtil.base64Encode(Security.randomBytes(32));
		final String encryptedPass = this.crypt(this.login, salt, this.rawPass);
		return new UserIdentity(this.login, encryptedPass, salt, 0);
	}

	private String crypt(String login, String salt, String rawPass) {
		return Security.SHA512CryptAlgorithm.instance.encrypt64(salt, rawPass, login);
	}

}
