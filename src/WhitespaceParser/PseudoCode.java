package WhitespaceParser;

public class PseudoCode {

    private String pseudoCode;
    int lineNumber = 0;
    String messages = "";

    public PseudoCode(String pseudoCode) {
        this.pseudoCode = pseudoCode;
    }

    public String getPseudoCode() {
        return pseudoCode;
    }

    public String toSTLSpace() {
        String ret = "";
        lineNumber = 0;
        messages = "";

        for(String line : pseudoCode.split("\n")){
            if(line.startsWith("push")){
                ret += "ss" + number(line);
            } else if(line.startsWith("dup")){
                ret += "s\ns";
            } else if(line.startsWith("copy")){
                ret += "sts" + number(line);
            } else if(line.startsWith("swap")){
                ret += "s\nt";
            } else if(line.startsWith("discard")){
                ret += "s\n\n";
            } else if(line.startsWith("slide")){
                ret += "st\n" + number(line);
            } else if(line.startsWith("add")){
                ret += "tsss";
            } else if(line.startsWith("sub")){
                ret += "tsst";
            } else if(line.startsWith("mul")){
                ret += "tss\n";
            } else if(line.startsWith("div")){
                ret += "tsts";
            } else if(line.startsWith("mod")){
                ret += "tstt";
            } else if(line.startsWith("store")){
                ret += "tts";
            } else if(line.startsWith("retr")){
                ret += "ttt";
            } else if(line.startsWith("prchr")){
                ret += "t\nss";
            } else if(line.startsWith("prnum")){
                ret += "t\nst";
            } else if(line.startsWith("rdchr")){
                ret += "t\nts";
            } else if(line.startsWith("rdnum")){
                ret += "t\ntt";
            } else if(line.startsWith("mark")){
                ret += "\nss" + label(line);
            } else if(line.startsWith("callsub")){
                ret += "\nst" + label(line);
            }else if(line.startsWith("jump0")){
                ret += "\nts" + label(line);
            } else if(line.startsWith("jumpn")){
                ret += "\ntt" + label(line);
            } else if(line.startsWith("jump")){
                ret += "\ns\n" + label(line);
            }  else if(line.startsWith("end")){
                ret += "\nt\n";
            } else if(line.startsWith("exit")){
                ret += "\n\n\n";
            } else{
                messages += "In Line " + lineNumber + " was an IMP failure " + "\n";
            }
            lineNumber++;
        }
        Controller.setMessages(messages);
        return ret;
    }

    private String number(String line){
        String ret = "";
        String argument = (line.split(" ")[1]);

        if(argument.startsWith("\"")){
            return label(line);
        }

        Long number = Long.parseLong(argument);

        switch (Long.signum(number)){
            case 0: return "ss\n";
            case 1: {
                ret += "s" + Long.toBinaryString(number);
                break;
            }
            case -1: {
                ret += "t" + Long.toBinaryString(-number);
                break;
            }
            default: messages += "In Line " + lineNumber + " was a Number failure " + "\n";
        }
        ret = ret.replace('0', 's');
        ret = ret.replace('1', 't');
        return ret + "\n";
    }

    private String label(String line){
        String label = line.split(" ")[1];
        String ret = "";
        Integer number = 0;

        if(label.startsWith("\"")){
            label = label.replace("\"", "");
            for(Character character : label.toCharArray()){
                ret += intToBinaryST((int)character);
            }
        } else{

            try{
                number = Integer.parseInt(label);
            } catch(Exception e){
                messages += "In Line " + lineNumber + " was a Number failure " + "\n";
            }
            ret = intToBinaryST(number);
            ret = ret.substring(1);
        }

        return ret + "\n";
    }

    private static String intToBinaryST(Integer integer){
        String ret = Integer.toBinaryString(integer);
        while(ret.length()<8){
            ret = "0" + ret;
        }
        ret = ret.replace('0', 's');
        ret = ret.replace('1', 't');
        return ret;
    }

}
