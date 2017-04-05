package com.kromracing.runningroute;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import com.kromracing.runningroute.*;

@RunWith(Suite.class)
@Suite.SuiteClasses( { 
    BasicTest.class,
    UrlTests.class,
    SearchTests.class,
    UndoNewTests.class,
    NavigateTests.class,
    CookieTests.class,
})
public class AllTests {
}

