package com.lousylynx.kutlas.lang.block;

import com.lousylynx.kutlas.lang.Parameter;
import com.lousylynx.kutlas.lang.Type;
import com.lousylynx.kutlas.lang.Value;

public class Method extends Block
{

    private String name;
    private Type type;
    private Parameter[] params;
    private Value returnValue;

    public Method(Block superBlock, String name, Type type, Parameter[] params, Value returnValue)
    {
        super(superBlock);

        this.name = name;
        this.type = type;
        this.params = params;
        this.returnValue = returnValue;
    }

    @Override
    public void run()
    {
         invoke();
    }

    public void invoke(Value... values)
    {

    }
}
