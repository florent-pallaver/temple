package test.com.temple.util;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.com.temple.util.calendar.StarSignTest;
import test.com.temple.util.json.JsonTest;
import test.com.temple.util.security.BDCryptAlgorithmTest;

@RunWith(Suite.class)
@SuiteClasses({ BDCryptAlgorithmTest.class, StarSignTest.class, JsonTest.class })
public class AllTests {

}
