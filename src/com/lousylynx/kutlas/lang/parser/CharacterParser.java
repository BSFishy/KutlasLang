package com.lousylynx.kutlas.lang.parser;

import com.lousylynx.kutlas.lang.block.Block;
import com.lousylynx.kutlas.lang.tokenizer.Tokenizer;

public class CharacterParser extends Parser{

    String line = "";

    public boolean upBlock()
    {
        return line.matches("\\}");
    }

    @Override
    public boolean shouldParse(String line) {
        this.line = line;
        return line.matches("(\\{|\\})");
    }

    @Override
    public Block parse(Block superBlock, Tokenizer tokenizer) {
        return null;
    }
}
