package com.lousylynx.kutlas.lang.tokenizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TokenRegistry {

    private static List<String> tokens = new ArrayList<>();

    private TokenRegistry() { }

    public static void addToken(String t)
    {
        tokens.add(t);
    }

    public static List<String> getTokens()
    {
        return tokens;
    }

    public static void addTokens(List<String> t)
    {
        tokens.addAll(t);
    }

    public static void addTokens(String[] t)
    {
        Collections.addAll(tokens, t);
    }
}
