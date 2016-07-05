package com.lousylynx.kutlas.lang.block;

import com.lousylynx.kutlas.lang.block.systemVar.SystemFunction;
import com.lousylynx.kutlas.lang.block.systemVar.SystemfuncRegistry;

import java.util.List;

public class SystemMethods extends SingleLineBlock {

    private String function;
    private List<String> args;

    public SystemMethods(Block superBlock, String function, List<String> args) {
        super(superBlock);

        this.function = function;
        this.args = args;
    }

    @Override
    public void run() {
        for(SystemFunction func : SystemfuncRegistry.getFunctions())
        {
            if(func.getName().equals(function))
            {
                System.out.println(args);
                func.run(args);
                return;
            }
        }
        throw new IllegalStateException("System function not found: " + function);
    }
}
