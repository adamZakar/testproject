package com.flexionmobile.codingchallenge.integration;

public class CodingChallengePurchase implements Purchase {

    private String id;
    private boolean consumed;
    private String itemId;

    public String getId() {
        return id;
    }

    public boolean getConsumed() {
        return consumed;
    }

    public String getItemId() {
        return itemId;
    }

    public CodingChallengePurchase(String id, boolean consumed, String itemId) {
        if(id==null || itemId==null){
            throw new NullPointerException("Id, or itemId cannot be null!");
        }
        this.id = id;
        this.consumed = consumed;
        this.itemId = itemId;
    }
}
