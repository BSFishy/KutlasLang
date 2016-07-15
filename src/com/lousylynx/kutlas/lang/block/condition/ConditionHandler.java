package com.lousylynx.kutlas.lang.block.condition;

import com.lousylynx.kutlas.lang.tokenizer.Token;
import com.lousylynx.kutlas.lang.tokenizer.TokenType;
import com.lousylynx.kutlas.lang.tokenizer.Tokenizer;

import java.util.HashMap;
import java.util.Map;

public class ConditionHandler {

    private String cond = "";
    private Tokenizer tokenizer;
    public Map<Integer, Token> tokens = new HashMap<>();
    public int currentKey = 0;

    public ConditionHandler(String cond)
    {
        this.cond = cond;
        tokenizer = new Tokenizer(cond);
    }

    public Token run()
    {
        while(tokenizer.hasNextToken())
        {
            Token t = fixInts((tokens.size() == currentKey) ? tokenizer.nextToken() : tokens.get(currentKey + 1));
            int putinKey = tokens.size();
            tokens.put(putinKey, t);
            Token returnToken = null;
            for(Condition c : ConditionRegistry.getConditions())
            {
                c.setup(this, tokenizer);
                //System.out.println(t.getType() + " " + t.getToken());
                if(c.shouldRun())
                {
                    returnToken = c.run();
                    break;
                }
            }
            tokens.remove(putinKey);
            tokens.put(tokens.size(), ((returnToken != null) ? returnToken : t));
            currentKey++;
        }
        return tokens.get(tokens.size()-1);
    }

    private Token fixInts(Token in)
    {
        Token out = in;
        if(in.getToken().matches("[0-9]+"))
        {
            out = new Token(in.getToken(), TokenType.INTEGER);
        }
        return out;
    }
}
