package com.lousylynx.kutlas.lang.parser;

import com.lousylynx.kutlas.lang.block.Block;
import com.lousylynx.kutlas.lang.tokenizer.Tokenizer;
import com.lousylynx.kutlas.lang.block.Class;

public class ClassParser extends Parser<Class> {

    @Override
    public boolean shouldParse(String line) {
        return line.matches("class [a-zA-Z0-9]+( ?\\{)?");
    }

    @Override
    public Class parse(Block superBlock, Tokenizer tokenizer) {
        tokenizer.nextToken();
        String name = tokenizer.nextToken().getToken();

        return new Class(name);
    }
}
