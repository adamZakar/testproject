package com.flexionmobile.codingchallenge.integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CodingChallengeIntegrationTest {
    IntegrationTestRunner integrationTestRunner = new IntegrationTestRunner();
    CodingChallengeIntegration codingChallengeIntegration = new CodingChallengeIntegration();

//    ExpectedException expectedException = ExpectedException.none();
//
//    private final int numberOfRepetitions = 10;
//
//    @Before
//    public void setUpBeforeTest() {
//        for (int i = 0; i < numberOfRepetitions; i++) {
//            codingChallengeIntegration.buy("item1");
//        }
//    }
//
//    @Test
//    public void testPurchases() {
//        integrationTestRunner.runTests(codingChallengeIntegration);// for the original test, the rest should be commented out
//        assertEquals(codingChallengeIntegration.getPurchases().size(), equals(numberOfRepetitions));
//        codingChallengeIntegration.consume(codingChallengeIntegration.getPurchases().get(0));
//        assertTrue(codingChallengeIntegration.getPurchases().get(0).getConsumed());
//        assertFalse(codingChallengeIntegration.getPurchases().get(1).getConsumed());
//    }
//
//    @Test
//    public void testPurchasesExceptions() {
//        expectedException.expect(NullPointerException.class);
//        expectedException.expectMessage("There were no server response");
//        codingChallengeIntegration.buy("drhtd");
//
//    }
//
//    @After
//    public void reset() {
//        //here should be written the reset method, but it was not included into the pdf.
//    }

    @Test
    public void originalTestCase() {
        integrationTestRunner.runTests(codingChallengeIntegration);// for the original test, the rest should be commented out
    }

}
