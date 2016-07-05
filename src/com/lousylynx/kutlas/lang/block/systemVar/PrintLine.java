package com.lousylynx.kutlas.lang.block.systemVar;

import java.util.List;

public class PrintLine extends SystemFunction {

    public PrintLine() {
        super("printline");
    }

    @Override
    public void run(List<String> args) {
        if(args.size() > 1)
        {
            throw new IllegalArgumentException("Too many arguments were given");
        }else if(args.size() < 1)
        {
            return;
        }

        System.out.println(args.get(0));
    }
}
