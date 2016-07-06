package com.lousylynx.kutlas.lang.block.systemVar;

import com.lousylynx.kutlas.lang.BuiltinType;
import com.lousylynx.kutlas.lang.Value;

import java.util.List;

public class PrintLine extends SystemFunction {

    public PrintLine() {
        super("printline");
    }

    @Override
    public void run(List<Value> args) {
        if(args.size() > 1)
        {
            throw new IllegalArgumentException("Too many arguments were given");
        }else if(args.size() < 1)
        {
            return;
        }

        String output;
        if(args.get(0).getType() == BuiltinType.VARIABLE)
        {
            if(getSuperBlock().getVariable((String) args.get(0).getValue()).getType() == BuiltinType.STRING)
            {
                output = (String) (getSuperBlock().getVariable((String) args.get(0).getValue()).getValue());
            }else/* if(getSuperBlock().getVariable((String) args.get(0).getValue()).getType() == BuiltinType.INTEGER)*/
            {
                output = (getSuperBlock().getVariable((String) args.get(0).getValue()).getValue()).toString();
            }
        }else{
            output = args.get(0).getValue().toString();
        }

        System.out.println(output);
    }
}
