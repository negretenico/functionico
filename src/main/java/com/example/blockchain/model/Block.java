package com.example.blockchain.model;

public record Block(int index, long ts, String hash, String previous,
                    int nonce) {
    public String getIdentifier(){
        return String.format("%s.%s.%s.%s",index,previous,ts,nonce);
    }
}
