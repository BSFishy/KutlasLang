package com.lousylynx.kutlas.lang.block.condition;

import com.lousylynx.kutlas.lang.tokenizer.Token;
import com.lousylynx.kutlas.lang.tokenizer.Tokenizer;

public abstract class Condition {

    Tokenizer tokenizer;
    ConditionHandler conditionHandler;

    public void setup(ConditionHandler conditionHandler, Tokenizer tokenizer)
    {
        this.tokenizer = tokenizer;
        this.conditionHandler = conditionHandler;
    }

    public abstract boolean shouldRun();

    public abstract Token run();
}
