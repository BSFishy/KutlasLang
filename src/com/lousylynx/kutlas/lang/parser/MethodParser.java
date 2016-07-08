package com.lousylynx.kutlas.lang.parser;

import com.lousylynx.kutlas.lang.BuiltinType;
import com.lousylynx.kutlas.lang.Parameter;
import com.lousylynx.kutlas.lang.block.Block;
import com.lousylynx.kutlas.lang.block.Method;
import com.lousylynx.kutlas.lang.tokenizer.Token;
import com.lousylynx.kutlas.lang.tokenizer.Tokenizer;

import java.util.ArrayList;

public class MethodParser extends Parser<Method>
{
    @Override
    public boolean shouldParse(String line)
    {
        return line.matches("fun [^\\W][a-zA-Z0-9]+ [^\\W][a-zA-Z0-9]+ \\(([^\\W][a-zA-Z0-9]+:[^\\W][a-zA-Z0-9]+)*\\)( ?\\{)?");
    }

    @Override
    public Method parse(Block superBlock, Tokenizer tokenizer)
    {
        tokenizer.nextToken();

        String returnType = tokenizer.nextToken().getToken().toUpperCase();

        String name = tokenizer.nextToken().getToken();

        tokenizer.nextToken();
        Token first = tokenizer.nextToken();
        ArrayList<Parameter> params = new ArrayList<>();
        if(!first.getToken().equals(")"))
        {
            String[] paramData = new String[] { first.getToken(), null };

            while(tokenizer.hasNextToken())
            {
                Token token = tokenizer.nextToken();

                if(token.getToken().equals(")"))
                {
                    break;
                }else if(token.getToken().equals("("))
                {
                    continue;
                }

                if(paramData[0] == null)
                {
                    paramData[0] = token.getToken();
                }else{
                    paramData[1] = token.getToken();

                    params.add(new Parameter(BuiltinType.valueOf(paramData[0].toUpperCase()), paramData[1]));

                    paramData =  new String[2];
                }
            }
        }

        return new Method(superBlock, name, returnType, params.toArray(new Parameter[params.size()]));
    }
}
