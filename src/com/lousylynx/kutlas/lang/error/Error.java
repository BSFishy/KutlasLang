package com.lousylynx.kutlas.lang.error;

import com.lousylynx.kutlas.lang.ColorCodes;

import java.util.HashMap;
import java.util.Map;

public class Error {

    private static Map<Errors, String> errorMessages = new HashMap<>();

    static{
        errorMessages.put(Errors.LINECOULDNOTPARSE,    "Could not parse line: %s");
        errorMessages.put(Errors.NOMAINMETHOD,         "No main method");
        errorMessages.put(Errors.WRONGPARAMNUMBER,     "Wrong number of parameters");
        errorMessages.put(Errors.WRONGPARAM,           "Wrong parameter %s. Requires %s.");
        errorMessages.put(Errors.METHODRETURNSNOTHING, "Method does not return anything.");
        errorMessages.put(Errors.NOSYSFUNC,            "System function not found: %s");
        errorMessages.put(Errors.VOIDVARS,             "Cannot declare void variables");
        errorMessages.put(Errors.TOOMANYARGS,          "Too many arguments were given");
        errorMessages.put(Errors.COULDNOTPARSE,        "Could not parse '%s'");
    }

    private Error() { }

    public static void throwError(Errors e, String... datas)
    {
        String m = errorMessages.get(e);
        System.out.println(ColorCodes.ParseColors(":red,N:" + " Error: " + String.format(m, datas)));
        System.exit(1);
    }

    public static void addError(Errors e, String message)
    {
        errorMessages.put(e, message);
    }
}
