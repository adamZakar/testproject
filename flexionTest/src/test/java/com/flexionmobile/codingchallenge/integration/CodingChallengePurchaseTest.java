package com.flexionmobile.codingchallenge.integration;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.jupiter.api.Assertions.*;

public class CodingChallengePurchaseTest {
    @Rule
    public final ExpectedException expectedException= ExpectedException.none();

    @Test
    public void testPurchaseObjects(){
        String id="id";
        boolean consumed=true;
        String itemId="itemId";
        CodingChallengePurchase codingChallengePurchase= new CodingChallengePurchase(id,consumed,itemId);
        assertTrue(codingChallengePurchase.getConsumed());
        assertEquals(codingChallengePurchase.getId(),id);
        assertEquals(codingChallengePurchase.getItemId(),itemId);

    }

    @Test
    public void testNullPointerExceptions(){
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("Id, or itemId cannot be null!");
      CodingChallengePurchase codingChallengePurchaseForNullPointer=
              new CodingChallengePurchase(null,true,"itemId");
    }
}
