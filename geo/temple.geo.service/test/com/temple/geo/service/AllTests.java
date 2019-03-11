package com.temple.geo.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.temple.geo.service.ws.rest.GeonamesCountryServiceTest;
import com.temple.geo.service.ws.rest.GeonamesDivisionServiceTest;

@RunWith(Suite.class)
@SuiteClasses({ GeonamesCountryServiceTest.class, GeonamesDivisionServiceTest.class })
public class AllTests {

}
