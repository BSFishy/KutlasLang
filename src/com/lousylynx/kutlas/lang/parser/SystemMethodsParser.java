package com.lousylynx.kutlas.lang.parser;

import com.lousylynx.kutlas.lang.BuiltinType;
import com.lousylynx.kutlas.lang.Value;
import com.lousylynx.kutlas.lang.block.Block;
import com.lousylynx.kutlas.lang.block.SystemMethods;
import com.lousylynx.kutlas.lang.tokenizer.Token;
import com.lousylynx.kutlas.lang.tokenizer.TokenType;
import com.lousylynx.kutlas.lang.tokenizer.Tokenizer;

import java.util.ArrayList;
import java.util.List;

public class SystemMethodsParser extends Parser<Block> {
    @Override
    public boolean shouldParse(String line) {
        return line.matches("System\\->[^\\W][a-zA-Z0-9]+:\\((.*)?\\)"); // is 'System->[?]:(?)
    }

    @Override
    public Block parse(Block superBlock, Tokenizer tokenizer) {
        /*while(tokenizer.hasNextToken())
        {
            System.out.println(tokenizer.nextToken().getToken());
        }
        System.out.println("done");
        return null;*/
        tokenizer.nextToken(); // System
        String function = tokenizer.nextToken().getToken();
        tokenizer.nextToken();
        List<Value> args = new ArrayList<>();
        while(tokenizer.hasNextToken())
        {
            Token t = tokenizer.nextToken();
            if(t.getToken().isEmpty())
            {
                continue;
            }
            if(t.getType() == TokenType.STRING_LITERAL)
            {
                args.add(new Value(BuiltinType.STRING, t.getToken()));
            }else if(t.getType() == TokenType.IDENTIFIER)
            {
                args.add(new Value(BuiltinType.VARIABLE, t.getToken()));
            }
        }
        return new SystemMethods(superBlock, function, args);
    }
}
