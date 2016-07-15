package com.lousylynx.kutlas.lang.parser;

import com.lousylynx.kutlas.lang.block.Block;
import com.lousylynx.kutlas.lang.block.VariableBlock;
import com.lousylynx.kutlas.lang.block.condition.ConditionHandler;
import com.lousylynx.kutlas.lang.tokenizer.Token;
import com.lousylynx.kutlas.lang.tokenizer.TokenType;
import com.lousylynx.kutlas.lang.tokenizer.Tokenizer;

public class VariableParser extends Parser<Block>
{
    @Override
    public boolean shouldParse(String line) {
        return line.matches("[^\\W]+ [^\\W]+ = \"?.*\"?");
    }

    @Override
    public Block parse(Block superBlock, Tokenizer tokenizer) {
        /*while(tokenizer.hasNextToken())
        {
            tokenizer.nextToken();
            //System.out.println(tokenizer.nextToken().getToken());
        }
        return null;*/
        String type = tokenizer.nextToken().getToken();
        String name = tokenizer.nextToken().getToken();
        tokenizer.nextToken();

        String cond = "";
        while(tokenizer.hasNextToken())
        {
            Token t = tokenizer.nextToken();
            if(t.getType() != TokenType.STRING_LITERAL)
                cond += t.getToken();
            else
                cond += "\"" + t.getToken() + "\"";
        }
        Token v = new ConditionHandler(cond).run();

        Object value;

        if(v.getType() == TokenType.INTEGER)
        {
            value = Integer.valueOf(v.getToken());
        }else if(v.getType() == TokenType.STRING_LITERAL)
        {
            value = v.getToken();
        }else if(v.getType() == TokenType.BOOLEAN)
        {
            value = v.getToken();
        }else
        {
            value = superBlock.getVariable(v.getToken()).getValue();
        }

        return new VariableBlock(superBlock, type, name, value);
    }
}
