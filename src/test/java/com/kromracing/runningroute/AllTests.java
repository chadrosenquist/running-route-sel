package com.kromracing.runningroute;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses( { 
    BasicTest.class,
    UrlTest.class,
    SearchTest.class,
    UndoNewTest.class,
    NavigateTest.class,
    CookieTest.class,
})
public class AllTests {
}

