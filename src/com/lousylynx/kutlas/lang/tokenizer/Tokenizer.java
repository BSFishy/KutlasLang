package com.lousylynx.kutlas.lang.tokenizer;

import com.lousylynx.kutlas.lang.error.*;

import java.util.ArrayList;

public class Tokenizer {

    private ArrayList<String> tokenDatas;

    private String str;

    private Token lastToken;
    private boolean pushBack;

    @SuppressWarnings("all")
    public Tokenizer(String str)
    {
        this.tokenDatas = new ArrayList<>();
        this.str = str;

        /*tokenDatas.add(new TokenData(Pattern.compile("\".*\"|([a-zA-Z]+)"), TokenType.IDENTIFIER));
        tokenDatas.add(new TokenData(Pattern.compile("\"[^\"]*\""), TokenType.STRING_LITERAL));
        tokenDatas.add(new TokenData(Pattern.compile("(-)?[0-9]+"), TokenType.INTEGER));*/

        /*for(String t : new String[] { "=", "(", ")", ",", "{", "}", ":", "...", "." })
        {
            tokenDatas.add(t);
        }*/

        for(String t : TokenRegistry.getTokens())
        {
            tokenDatas.add(t);
        }
    }

    public Token nextToken()
    {
        str = str.trim();

        if(pushBack)
        {
            pushBack = false;
            return lastToken;
        }

        if(str.isEmpty())
        {
            return (lastToken = new Token("", TokenType.EMPTY));
        }

        String[] splitted = str.split("");
        String retToken = "";
        TokenType type = null;
        String quotes = "\"";
        for(String character : splitted)
        {
            if(((retToken + character).equals("True") || (retToken + character).equals("False")) && type != null && !type.equals(TokenType.STRING_LITERAL))
            {
                str = str.substring(retToken.length() + 1);
                return (lastToken = new Token(retToken + character, TokenType.BOOLEAN));
            }

            //System.out.println((str.length() >= 1) ? (contains(str.substring(0, 1)) ? contains(str.substring(0, 1)) + " " + str : "") : "");
            if(contains(character) && !(type != null && type.equals(TokenType.STRING_LITERAL)))
            {
                if(retToken.isEmpty())
                {
                    str = str.substring(1);
                    return (lastToken = new Token(character, TokenType.TOKEN));
                } else {
                    str = str.substring(retToken.length());
                    //pushBack();
                    return (lastToken = new Token(retToken, TokenType.IDENTIFIER));
                }
            }

            /*if((">".equals(character) || ">" == character) && type == TokenType.IDENTIFIER)
            {
                str = str.substring(retToken.length() + 1);
                return (lastToken = new Token(retToken, TokenType.IDENTIFIER));
            }*/

            if((quotes.equals(character) || quotes == character) && type == null)
            {
                type = TokenType.STRING_LITERAL;
                retToken += character;
                continue;
            }
            //System.out.println((type == TokenType.STRING_LITERAL) ? retToken : "");

            if(quotes.equals(character) && (type != null && type.equals(TokenType.STRING_LITERAL)))
            {
                str = str.substring(retToken.length() + 1);
                return (lastToken = new Token(retToken.substring(1), type));
            }

            if(character.equals("-") && type == null)
            {
                type = TokenType.INTEGER;
                retToken += character;
                continue;
            }

            //System.out.println(type + " " + retToken);
            if(character.matches("[0-9]") && (type == null || (type != null && type.equals(TokenType.INTEGER))))
            {
                type = TokenType.INTEGER;
                retToken += character;
                continue;
            }

            if(character.matches("[a-zA-Z0-9]") && ((type != null && type.equals(null)) || !(type != null && type.equals(TokenType.INTEGER))))
            {
                retToken += character;
                type = ((type == TokenType.STRING_LITERAL) ? TokenType.STRING_LITERAL : TokenType.IDENTIFIER);
                continue;
            }

            if(character.charAt(0) == ' ' && (type != null && !type.equals(TokenType.STRING_LITERAL)))
            {
                str = str.substring(retToken.length());
                return (lastToken = new Token(retToken, type));
            }

            if((type != null && !type.equals(null)) || (type != null && type.equals(TokenType.STRING_LITERAL)))
            {
                retToken += character;
                continue;
            }
        }

        if(str.substring(retToken.length()).isEmpty())
        {
            str = str.substring(retToken.length());
            return (lastToken = new Token(retToken, type));
        }

        //System.out.println(type + " " + retToken);

        com.lousylynx.kutlas.lang.error.Error.throwError(Errors.COULDNOTPARSE, str);
        System.exit(1);
        return null;
    }

    private boolean contains(String character)
    {
        for(String t : tokenDatas)
        {
            if(t.equalsIgnoreCase(character))
            {
                return true;
            }
        }
        return false;
    }

    public boolean hasNextToken()
    {
        return !str.isEmpty();
    }

    public void pushBack()
    {
        if(lastToken != null)
        {
            this.pushBack = true;
        }
    }
}
