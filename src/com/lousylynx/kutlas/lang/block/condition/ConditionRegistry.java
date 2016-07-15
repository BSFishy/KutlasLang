package com.lousylynx.kutlas.lang.block.condition;

import java.util.ArrayList;
import java.util.List;

public class ConditionRegistry {

    private static List<Condition> conditions = new ArrayList<>();

    private ConditionRegistry() { }

    public static void addCondition(Condition condition)
    {
        conditions.add(condition);
    }

    public static List<Condition> getConditions()
    {
        return conditions;
    }
}
