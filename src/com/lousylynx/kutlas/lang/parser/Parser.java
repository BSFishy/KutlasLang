package com.lousylynx.kutlas.lang.parser;

import com.lousylynx.kutlas.lang.block.Block;
import com.lousylynx.kutlas.lang.tokenizer.Tokenizer;

public abstract class Parser<T extends Block>
{

    public abstract boolean shouldParse(String line);

    public abstract T parse(Block superBlock, Tokenizer tokenizer);

}
