package com.lousylynx.kutlas.lang.parser;

import java.util.ArrayList;
import java.util.List;

public class ParserRegistry {

    private static List<Parser<?>> parsers = new ArrayList<>();

    private ParserRegistry() { }

    public static List<Parser<?>> getParsers()
    {
        return parsers;
    }

    public static void addParser(Parser<?> p)
    {
        parsers.add(p);
    }

}
