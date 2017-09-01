package com.liberymutual.goforcode.wimp.models;

import static org.junit.Assert.*;
import org.meanbean.test.BeanTester;

import org.junit.Test;

public class AwardModelTests {

	@Test
    public void test_all_gets_and_sets() {
        new BeanTester().testBean(Award.class);
    }

}
