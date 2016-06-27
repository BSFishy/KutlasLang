
class WhileStyledWhile
{
    fun void main(args:String...)
    {
        while(args.hasNext())
        {
            String arg = args.next()
            if(arg == "hello")
            {
                System.printline("Well hello to you, too!")
            }
        }
    }
}