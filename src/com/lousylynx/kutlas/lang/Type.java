package com.lousylynx.kutlas.lang;

public interface Type {

    public static Type match(String str)
    {
        try
        {
            return BuiltinType.valueOf(str.toUpperCase());
        }catch(Exception e) {
            //TODO: match str to class
            return null;
        }
    }
}
