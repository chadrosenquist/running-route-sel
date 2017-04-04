package com.kromracing.runningroute;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses( { 
    BasicTests.class,
    UrlTests.class,
    SearchTests.class,
    UndoNewTests.class,
    NavigateTests.class,
    CookieTests.class,
})
public class AllTests {
}

