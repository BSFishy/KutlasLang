package com.lousylynx.kutlas.lang.block;

import com.lousylynx.kutlas.lang.*;
import com.lousylynx.kutlas.lang.error.Error;
import com.lousylynx.kutlas.lang.error.Errors;

public class Method extends Block
{

    private String name,  type;
    private Parameter[] params;
    private Value returnValue;

    public Method(Block superBlock, String name, String type, Parameter[] params)
    {
        super(superBlock);

        this.name = name;
        this.type = type;
        this.params = params;
    }

    public String getName()
    {
        return name;
    }

    public String getType()
    {
        return type;
    }

    public Parameter[] getParameters()
    {
        return params;
    }

    @Override
    public void run()
    {
         invoke();
    }

    public Value invoke(Value... values)
    {
        Type t = Type.match(type);

        if(values.length != params.length)
        {
            com.lousylynx.kutlas.lang.error.Error.throwError(Errors.WRONGPARAMNUMBER);
        }

        for(int i=0; i < values.length && i < params.length; i++)
        {
            Parameter p = params[i];
            Value v = values[i];

            if(p.getType() != v.getType())
            {
                Error.throwError(Errors.WRONGPARAM, v.getType().toString(), p.getType().toString());
            }

            addVariable(new Variable(this, p.getType(), p.getName(), v.getValue()));
        }

        for(Block b : getSubBlocks())
        {
            b.run();

            if(returnValue != null)
            {
                break;
            }
        }

        if(returnValue == null && t != BuiltinType.VOID)
        {
            Error.throwError(Errors.METHODRETURNSNOTHING);
        }

        Value localReturnValue = returnValue;
        returnValue = null;
        return localReturnValue;
    }
}
