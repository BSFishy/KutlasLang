package com.lousylynx.kutlas.lang;

import com.lousylynx.kutlas.lang.block.Block;
import com.lousylynx.kutlas.lang.block.Class;
import com.lousylynx.kutlas.lang.block.Method;
import com.lousylynx.kutlas.lang.block.systemVar.PrintLine;
import com.lousylynx.kutlas.lang.block.systemVar.SystemfuncRegistry;
import com.lousylynx.kutlas.lang.error.Errors;
import com.lousylynx.kutlas.lang.parser.*;
import com.lousylynx.kutlas.lang.tokenizer.Tokenizer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class KutlasLang {

    private ArrayList<Class> classes;

    public KutlasLang(String code)
    {
        register();

        List<Parser<?>> parsers = ParserRegistry.getParsers();

        Class main = null;

        Block block = null;

        classes = new ArrayList<>();

        boolean success;

        for(String line : code.split("\n"))
        {
            success = false;
            line = line.trim();
            Tokenizer tokenizer = new Tokenizer(line);

            for(Parser<?> parser : parsers)
            {
                if(line.length() == 0)
                {
                    success = true;
                    break;
                }
                if(parser.shouldParse(line))
                {
                    if(parser instanceof CharacterParser)
                    {
                        CharacterParser p = (CharacterParser) parser;
                        if(p.upBlock())
                        {
                            block = block.getSuperBlock();
                        }
                        success = true;
                        break;
                    }

                    Block newBlock = parser.parse(block, tokenizer);

                    if(newBlock instanceof Class)
                    {
                        classes.add((Class) newBlock);
                    }else{
                        block.addBlock(newBlock);
                    }

                    block = newBlock;
                    success = true;
                    break;
                }
            }

            if(!success)
            {
                com.lousylynx.kutlas.lang.error.Error.throwError(Errors.LINECOULDNOTPARSE, line);
            }
        }

        for(Class c : classes)
        {
            for(Block b : c.getSubBlocks())
            {
                if(b instanceof Method)
                {
                    Method method = (Method) b;
                    if(method.getName().equals("main") && method.getType().equals("VOID") && method.getParameters().length == 0)
                    {
                        main = c;
                    }
                }
            }
        }

        if(main == null)
        {
            throw new IllegalStateException("No main method.");
        }

        main.run();
    }

    public KutlasLang(){}

    public static void main(String[] args)
    {
        Path file = Paths.get(args[0]);
        String contents = "";
        try {
            List<String> fileContents = Files.readAllLines(file);
            for(String line : fileContents)
            {
                contents += line + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        new KutlasLang(contents);
    }

    public void loadPlugins()
    {
        Path rootFolder = Paths.get(System.getProperty("user.home") + "/.kutlas/plugins");
        for(File f : rootFolder.toFile().listFiles())
        {
            //TODO: load plugin stuffs
        }
    }


    private void register()
    {
        // Builtin parser registry
        ParserRegistry.addParser(new CharacterParser());
        ParserRegistry.addParser(new ClassParser());
        ParserRegistry.addParser(new MethodParser());
        ParserRegistry.addParser(new VariableParser());
        ParserRegistry.addParser(new SystemMethodsParser());

        // Builtin system variable function registry
        SystemfuncRegistry.addFunction(new PrintLine());
    }
}
