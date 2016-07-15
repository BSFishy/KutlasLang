package com.lousylynx.kutlas.lang.block.condition;

import com.lousylynx.kutlas.lang.tokenizer.Token;
import com.lousylynx.kutlas.lang.tokenizer.TokenType;

public class SimpleMathCondition extends Condition {

    Token intOne, intTwo;
    String operator;

    @Override
    public boolean shouldRun() {
        Token currentToken = conditionHandler.tokens.get(conditionHandler.currentKey);
        if(currentToken.getType() == TokenType.TOKEN && (currentToken.getToken().equals("+") || currentToken.getToken().equals("-") || currentToken.getToken().equals("*") || currentToken.getToken().equals("/") || currentToken.getToken().equals("^")))
        {
            operator = currentToken.getToken();
            intOne = conditionHandler.tokens.get(conditionHandler.currentKey-1);
            intTwo = tokenizer.nextToken();
            conditionHandler.tokens.put(conditionHandler.tokens.size(), intTwo);
            if(intOne.getType() == TokenType.INTEGER && intTwo.getType() == TokenType.INTEGER)
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public Token run() {
        int ret = -1;
        int realIntOne = Integer.parseInt(intOne.getToken());
        int realIntTwo = Integer.parseInt(intTwo.getToken());
        switch (operator)
        {
            case "+":
                ret = realIntOne + realIntTwo;
                break;
            case "-":
                ret = realIntOne - realIntTwo;
                break;
            case "*":
                ret = realIntOne * realIntTwo;
                break;
            case "/":
                ret = realIntOne / realIntTwo;
                break;
            case "^":
                ret = (int) Math.pow(Double.parseDouble(intOne.getToken()), Double.parseDouble(intTwo.getToken()));
                break;
        }
        conditionHandler.tokens.remove(conditionHandler.tokens.size()-1); conditionHandler.currentKey--;
        conditionHandler.tokens.remove(conditionHandler.tokens.size()-1); conditionHandler.currentKey--;
        //conditionHandler.tokens.remove(conditionHandler.tokens.size()-1); conditionHandler.currentKey--;
        return new Token(Integer.toString(ret), TokenType.INTEGER);
    }
}
