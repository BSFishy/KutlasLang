package com.lousylynx.kutlas.lang.block.systemVar;

import java.util.List;

public abstract class SystemFunction {

    private String name;

    public SystemFunction(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public abstract void run(List<String> args);
}
