package com.lousylynx.kutlas.lang.block.systemVar;

import com.lousylynx.kutlas.lang.Value;
import com.lousylynx.kutlas.lang.block.Block;

import java.util.List;

public abstract class SystemFunction {

    private String name;
    private Block superBlock;

    public SystemFunction(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public Block getSuperBlock()
    {
        return superBlock;
    }

    public abstract void run(List<Value> args);

    public void setSuperBlock(Block superBlock)
    {
        this.superBlock = superBlock;
    }
}
