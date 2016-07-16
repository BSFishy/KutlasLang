package com.lousylynx.kutlas.lang.block.condition;

import com.lousylynx.kutlas.lang.tokenizer.Token;
import com.lousylynx.kutlas.lang.tokenizer.TokenType;

public class SimpleConditions extends Condition {

    private Object objOne, objTwo;
    private String operator;

    @Override
    public boolean shouldRun() {
        Token currentToken = conditionHandler.tokens.get(conditionHandler.currentKey);
        if(currentToken.getType() == TokenType.TOKEN && (currentToken.getToken().equals("<") || currentToken.getToken().equals(">") || currentToken.getToken().equals("=") || currentToken.getToken().equals("!")))
        {
            Token tokOne = conditionHandler.tokens.get(conditionHandler.currentKey-1);
            operator = currentToken.getToken();
            Token nextToken = tokenizer.nextToken();
            conditionHandler.tokens.put(conditionHandler.tokens.size(), nextToken);
            if (nextToken.getType() == TokenType.TOKEN && nextToken.getToken().equals("=")) 
            {
                operator += nextToken.getToken();
                Token lastToken = tokenizer.nextToken();
                conditionHandler.tokens.put(conditionHandler.tokens.size(), lastToken);
                objOne = ((tokOne.getType() == TokenType.STRING_LITERAL) ? tokOne.getToken() : ((tokOne.getType() == TokenType.INTEGER) ? Integer.parseInt(tokOne.getToken()) : ((tokOne.getType() == TokenType.BOOLEAN) ? Boolean.getBoolean(tokOne.getToken()) : tokOne.getToken())));
                objTwo = ((lastToken.getType() == TokenType.STRING_LITERAL) ? lastToken.getToken() : ((lastToken.getType() == TokenType.INTEGER) ? Integer.parseInt(lastToken.getToken()) : ((lastToken.getType() == TokenType.BOOLEAN) ? Boolean.getBoolean(lastToken.getToken()) : lastToken.getToken())));
                return true;
            }else if(nextToken.getType() != TokenType.TOKEN && currentToken.getToken().equals("<") || currentToken.getToken().equals(">"))
            {
                objOne = ((tokOne.getType() == TokenType.STRING_LITERAL) ? tokOne.getToken() : ((tokOne.getType() == TokenType.INTEGER) ? Integer.parseInt(tokOne.getToken()) : ((tokOne.getType() == TokenType.BOOLEAN) ? Boolean.getBoolean(tokOne.getToken()) : tokOne.getToken())));
                objTwo = ((nextToken.getType() == TokenType.STRING_LITERAL) ? nextToken.getToken() : ((nextToken.getType() == TokenType.INTEGER) ? Integer.parseInt(nextToken.getToken()) : ((nextToken.getType() == TokenType.BOOLEAN) ? Boolean.getBoolean(nextToken.getToken()) : nextToken.getToken())));
                return true;
            }
        }
        return false;
    }

    @Override
    public Token run() {
        boolean out = false;
        switch(operator)
        {
            case "==":
                removeTokens(3);

                out = objOne == objTwo;
                break;
            case "!=":
                removeTokens(3);

                out = objOne != objTwo;
                break;
            case "<=":
                removeTokens(3);

                out = (int) objOne <= (int) objTwo;
                break;
            case ">=":
                removeTokens(3);

                out = (int) objOne >= (int) objTwo;
                break;
            case "<":
                removeTokens(2);

                out = (int) objOne < (int) objTwo;
                break;
            case ">":
                removeTokens(2);

                out = (int) objOne > (int) objTwo;
                break;
        }
        return new Token(Boolean.toString(out), TokenType.BOOLEAN);
    }

    private void removeTokens(int many)
    {
        for(int i = 0; i < many; i++)
        {
            conditionHandler.tokens.remove(conditionHandler.tokens.size()-1); conditionHandler.currentKey--;
        }
    }
}
