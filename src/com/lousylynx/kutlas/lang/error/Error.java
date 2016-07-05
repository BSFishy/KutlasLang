package com.lousylynx.kutlas.lang.error;

import com.lousylynx.kutlas.lang.ColorCodes;

import java.util.HashMap;
import java.util.Map;

public class Error {

    private static Map<Errors, String> errorMessages = new HashMap<>();

    static{
        errorMessages.put(Errors.LINECOULDNOTPARSE, "Could not parse line: %s");
    }

    private Error() { }

    public static void throwError(Errors e, String data)
    {
        String m = errorMessages.get(e);
        System.out.println(ColorCodes.ParseColors(":red,N:" + String.format(m, data)));
        System.exit(1);
    }

    public static void addError(Errors e, String message)
    {
        errorMessages.put(e, message);
    }
}
