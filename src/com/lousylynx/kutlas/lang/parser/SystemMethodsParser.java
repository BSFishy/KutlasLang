package com.lousylynx.kutlas.lang.parser;

import com.lousylynx.kutlas.lang.block.Block;
import com.lousylynx.kutlas.lang.tokenizer.Tokenizer;

public class SystemMethodsParser extends Parser<Block> {
    @Override
    public boolean shouldParse(String line) {
        return line.matches("System\\.[^\\W][a-zA-Z0-9]+\\((.*)?\\)"); // is 'System.[?](?)
    }

    @Override
    public Block parse(Block superBlock, Tokenizer tokenizer) {
        while(tokenizer.hasNextToken())
        {
            System.out.println(tokenizer.nextToken().getToken());
        }
        return null;
        /*tokenizer.nextToken(); // System
        String function = tokenizer.nextToken().getToken();
        List<String> args = new ArrayList<>();
        while(tokenizer.hasNextToken())
        {
            Token t = tokenizer.nextToken();
            if(t.getType() == TokenType.STRING_LITERAL)
            {
                args.add(t.getToken());
            }
        }
        return new SystemMethods(superBlock, function, args);*/
    }
}
