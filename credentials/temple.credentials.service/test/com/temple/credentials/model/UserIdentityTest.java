package com.temple.credentials.model;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.temple.util.TempleUtil;
import com.temple.util.security.Security;

@RunWith(Parameterized.class)
public class UserIdentityTest {

	private final String login = "flominou";

	@Parameter
	public String rawPass;

	@Before
	public void before() {
	}

	@Parameters
	public static Collection<String> data() {
		return Arrays.asList("fuckYa !", "1234567890", "é\"'(-è_çà", "kj8_i'à@", "a", "bbbbbbbbbbbb");
	}

	@Test
	public void test() {
		final UserIdentity ui = this.createIdentity();
		final int length = this.rawPass.length();

		// Only the rawPass works
		Assert.assertTrue(ui.matchesPass(this.rawPass));
		Assert.assertFalse(ui.matchesPass(""));
		Assert.assertFalse(ui.matchesPass(this.rawPass.substring(TempleUtil.random(1, length - 1))));
		Assert.assertFalse(ui.matchesPass(this.rawPass + this.rawPass));
		Assert.assertFalse(ui.matchesPass(new String(Security.randomBytes(length))));
		Assert.assertFalse(ui.matchesPass(new String(Security.randomChars(length))));

		// Once pass is changed rawPass doesn't work anymore
		final String newPass = new String(Security.randomBytes(16));
		ui.setPass(newPass);
		Assert.assertTrue(ui.matchesPass(newPass));
		Assert.assertFalse(ui.matchesPass(this.rawPass));

		// Once pass is changed and salt too rawPass doesn't work anymore
		final String salt = new String(Security.randomBytes(32));
		ui.setPass(newPass, salt);
		Assert.assertTrue(ui.matchesPass(newPass));
		Assert.assertFalse(ui.matchesPass(this.rawPass));

	}

	public UserIdentity createIdentity() {
		return new UserIdentity(this.login, this.rawPass, 0);
	}

}
