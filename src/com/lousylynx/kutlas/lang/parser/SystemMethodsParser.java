package com.lousylynx.kutlas.lang.parser;

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
        return line.matches("System\\.[^\\W][a-zA-Z0-9]+\\((.*)?\\)"); // is 'System.[?](?)
    }

    @Override
    public Block parse(Block superBlock, Tokenizer tokenizer) {
        tokenizer.nextToken(); // System
        String function = tokenizer.nextToken().getToken();
        List<String> args = new ArrayList<>();
        while(tokenizer.hasNextToken())
        {
            Token t = tokenizer.nextToken();
            if(t.getType() == TokenType.STRING_LITERAL)
            {
                System.out.println(t.getToken());
                args.add(t.getToken());
            }
        }
        return new SystemMethods(superBlock, function, args);
    }
}
