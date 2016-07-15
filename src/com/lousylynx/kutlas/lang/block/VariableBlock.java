package com.lousylynx.kutlas.lang.block;

import com.lousylynx.kutlas.lang.BuiltinType;
import com.lousylynx.kutlas.lang.Type;
import com.lousylynx.kutlas.lang.Variable;
import com.lousylynx.kutlas.lang.error.*;

public class VariableBlock extends SingleLineBlock
{

    private String type, name;
    private Object value;

    public VariableBlock(Block superBlock, String type, String name, Object value) {
        super(superBlock);

        this.type = type;
        this.name = name;
        this.value = value;
    }

    @Override
    public void run() {
        Type t = Type.match(type);

        if(t == BuiltinType.VOID)
        {
            com.lousylynx.kutlas.lang.error.Error.throwError(Errors.VOIDVARS);
        }

        getSuperBlock().addVariable(new Variable(getSuperBlock(), Type.match(type), name, value));
    }
}
