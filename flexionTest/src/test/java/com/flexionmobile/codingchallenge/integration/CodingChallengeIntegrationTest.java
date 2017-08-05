package com.flexionmobile.codingchallenge.integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class CodingChallengeIntegrationTest {
    IntegrationTestRunner integrationTestRunner= new IntegrationTestRunner();
    CodingChallengeIntegration codingChallengeIntegration= new CodingChallengeIntegration();

    public static void main(String[] args) {
        IntegrationTestRunner integrationTestRunner= new IntegrationTestRunner();
        CodingChallengeIntegration codingChallengeIntegration= new CodingChallengeIntegration();
        integrationTestRunner.runTests(codingChallengeIntegration);
        List<Purchase> purchases= codingChallengeIntegration.getPurchases();
        System.out.println(purchases.size());
        codingChallengeIntegration.consume(purchases.get(0));
    }
   /* private final int numberOfRepetitions=100;

    @Before
    public void setUpBeforeTest(){

        for(int i=0;i<numberOfRepetitions;i++){
           codingChallengeIntegration.buy("item1");
        }
    }

    @Test
    public void testPurchases(){
        assertEquals(codingChallengeIntegration.getPurchases().size(),equals(numberOfRepetitions));
        codingChallengeIntegration.consume(codingChallengeIntegration.getPurchases().get(0));
        assertTrue(codingChallengeIntegration.getPurchases().get(0).getConsumed());
        assertFalse(codingChallengeIntegration.getPurchases().get(1).getConsumed());
    }

    @After
    public void reset(){
        //here should be written the reset method
    }
*/

}
