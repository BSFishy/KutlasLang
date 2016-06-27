package com.lousylynx.kutlas.lang.parser;

import com.lousylynx.kutlas.lang.block.Block;
import com.lousylynx.kutlas.lang.block.Method;
import com.lousylynx.kutlas.lang.tokenizer.Tokenizer;

public class MethodParser extends Parser<Method> {
    @Override
    public boolean shouldParse(String line) {
        return line.matches("fun [^\\W][a-zA-Z0-9]* [^\\W][a-zA-Z0-9]+ [^\\W][a-zA-Z0-9]+\\(([^\\W][a-zA-Z0-9]+:.*)*\\)");
    }

    @Override
    public Method parse(Block superBlock, Tokenizer tokenizer) {
        tokenizer.nextToken();

        String type = tokenizer.nextToken().getToken();
        return null;
    }
}
