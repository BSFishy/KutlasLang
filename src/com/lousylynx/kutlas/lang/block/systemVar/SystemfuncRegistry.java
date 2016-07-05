package com.lousylynx.kutlas.lang.block.systemVar;

import java.util.ArrayList;
import java.util.List;

public class SystemfuncRegistry {

    private static List<SystemFunction> functions = new ArrayList<>();

    private SystemfuncRegistry() { }

    public static void addFunction(SystemFunction f)
    {
        functions.add(f);
    }

    public static List<SystemFunction> getFunctions()
    {
        return functions;
    }
}
