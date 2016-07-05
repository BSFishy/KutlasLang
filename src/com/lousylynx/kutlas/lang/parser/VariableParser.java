package com.lousylynx.kutlas.lang.parser;

import com.lousylynx.kutlas.lang.block.Block;
import com.lousylynx.kutlas.lang.block.VariableBlock;
import com.lousylynx.kutlas.lang.tokenizer.Token;
import com.lousylynx.kutlas.lang.tokenizer.TokenType;
import com.lousylynx.kutlas.lang.tokenizer.Tokenizer;

public class VariableParser extends Parser<Block>
{
    @Override
    public boolean shouldParse(String line) {
        return line.matches("[^\\W][a-zA-Z0-9]+ [^\\W][a-zA-Z]+ = \"?[a-zA-Z0-9]\"?");
    }

    @Override
    public Block parse(Block superBlock, Tokenizer tokenizer) {
        String type = tokenizer.nextToken().getToken();

        /*if(type == BuiltinType.VOID)
        {
            throw new IllegalStateException("Cannot declare void variables.");
        }*/

        String name = tokenizer.nextToken().getToken();

        tokenizer.nextToken();

        Token v = tokenizer.nextToken();
        Object value;

        if(v.getType() == TokenType.INTEGER)
        {
            value = Integer.valueOf(v.getToken());
        }else if(v.getType() == TokenType.STRING_LITERAL)
        {
            value = v.getToken();
        }else
        {
            value = superBlock.getVariable(v.getToken()).getValue();
        }

        return new VariableBlock(superBlock, type, name, value);
    }
}
