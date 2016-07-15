package com.lousylynx.kutlas.lang.tokenizer;

public enum TokenType
{

    // Absolutely nothing
    EMPTY,

    // A token. For example, ( ) = ,
    TOKEN,

    // The type a variable or function is. For example, String Integer etc
    IDENTIFIER,

    // A normal string in double quotes
    STRING_LITERAL,

    // Any type of number
    INTEGER,

    // A true or false
    BOOLEAN;

}
