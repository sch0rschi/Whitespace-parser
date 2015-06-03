package WhitespaceParser;

public class STLSpace {

    private String sTLSpaceCode;
    private String sTLSpaceCodeTemp;
    private String messages;
    private int lineNumber = 0;


    public STLSpace(String sTLSpaceCode){
        this.sTLSpaceCode = sTLSpaceCode;
    }

    public String getsTLSpaceCode() {
        return sTLSpaceCode;
    }

    public String toWhitespaceCode(){
        String whitespaceCode = new String(sTLSpaceCode);
        whitespaceCode = whitespaceCode.replace('s', ' ');
        whitespaceCode = whitespaceCode.replace('t', '\t');
        return whitespaceCode;
    }

    public String toPseudoCode(){
        sTLSpaceCodeTemp = new String (sTLSpaceCode);
        String pseudoCode = "";
        lineNumber = 0;
        messages = "";

        while(!sTLSpaceCodeTemp.isEmpty()){
            if(sTLSpaceCodeTemp.startsWith("s")){
                //Stack manipulation
                sTLSpaceCodeTemp = sTLSpaceCodeTemp.substring(1);
                pseudoCode += sm() + "\n";
            } else if(sTLSpaceCodeTemp.startsWith("ts")){
                //Arithmetics
                sTLSpaceCodeTemp = sTLSpaceCodeTemp.substring(2);
                pseudoCode += ar() + "\n";
            } else if(sTLSpaceCodeTemp.startsWith("tt")){
                //Heap access
                sTLSpaceCodeTemp = sTLSpaceCodeTemp.substring(2);
                pseudoCode += ha() + "\n";
            } else if(sTLSpaceCodeTemp.startsWith("\n")){
                //Flow control
                lineNumber++;
                sTLSpaceCodeTemp = sTLSpaceCodeTemp.substring(1);
                pseudoCode += fc() + "\n";
            } else if(sTLSpaceCodeTemp.startsWith("t\n")){
                //I/O
                lineNumber++;
                sTLSpaceCodeTemp = sTLSpaceCodeTemp.substring(2);
                pseudoCode += io() + "\n";
            } else{
                sTLSpaceCodeTemp = sTLSpaceCodeTemp.substring(1);
                messages += "In Line " + lineNumber + " was an IMP failure " + "\n";
            }
        }
        Controller.setMessages(messages);
        return pseudoCode;
    }

    private String sm(){
        String ret = "";
        if(sTLSpaceCodeTemp.startsWith("s")){
            sTLSpaceCodeTemp = sTLSpaceCodeTemp.substring(1);
            ret += "push " + number();
        } else if(sTLSpaceCodeTemp.startsWith("\ns")){
            sTLSpaceCodeTemp = sTLSpaceCodeTemp.substring(2);
            lineNumber++;
            ret += "dup";
        } else if(sTLSpaceCodeTemp.startsWith("ts")){
            sTLSpaceCodeTemp = sTLSpaceCodeTemp.substring(2);
            lineNumber++;
            ret += "copy " + number();
        } else if(sTLSpaceCodeTemp.startsWith("\nt")){
            sTLSpaceCodeTemp = sTLSpaceCodeTemp.substring(2);
            lineNumber++;
            ret += "swap";
        } else if(sTLSpaceCodeTemp.startsWith("\n\n")){
            sTLSpaceCodeTemp = sTLSpaceCodeTemp.substring(2);
            lineNumber++;lineNumber++;
            ret += "discard";
        } else if(sTLSpaceCodeTemp.startsWith("t\n")){
            sTLSpaceCodeTemp = sTLSpaceCodeTemp.substring(2);
            lineNumber++;
            ret += "slide " + number();
        } else{
            messages += "In Line " + lineNumber + " was a Command failure " + "\n";
        }
        return ret;
    }

    private String ar() {
        String ret = "";
        if(sTLSpaceCodeTemp.startsWith("ss")) {
            sTLSpaceCodeTemp = sTLSpaceCodeTemp.substring(2);
            ret += "add";
        } else if(sTLSpaceCodeTemp.startsWith("st")) {
            sTLSpaceCodeTemp = sTLSpaceCodeTemp.substring(2);
            ret += "sub";
        } else if(sTLSpaceCodeTemp.startsWith("s\n")) {
            sTLSpaceCodeTemp = sTLSpaceCodeTemp.substring(2);
            lineNumber++;
            ret += "mul";
        } else if(sTLSpaceCodeTemp.startsWith("ts")) {
            sTLSpaceCodeTemp = sTLSpaceCodeTemp.substring(2);
            ret += "div";
        } else if(sTLSpaceCodeTemp.startsWith("tt")) {
            sTLSpaceCodeTemp = sTLSpaceCodeTemp.substring(2);
            ret += "mod";
        } else{
            messages += "In Line " + lineNumber + " was a Command failure " + "\n";
        }
        return ret;
    }

    private String ha() {
        String ret = "";
        if(sTLSpaceCodeTemp.startsWith("s")) {
            sTLSpaceCodeTemp = sTLSpaceCodeTemp.substring(1);
            ret += "store";
        } else if(sTLSpaceCodeTemp.startsWith("t")) {
            sTLSpaceCodeTemp = sTLSpaceCodeTemp.substring(1);
            ret += "retr";
        } else{
            messages += "In Line " + lineNumber + " was a Command failure " + "\n";
        }
        return ret;
    }

    private String io() {
        String ret = "";
        if(sTLSpaceCodeTemp.startsWith("ss")) {
            sTLSpaceCodeTemp = sTLSpaceCodeTemp.substring(2);
            ret += "prchr";
        } else if(sTLSpaceCodeTemp.startsWith("st")) {
            sTLSpaceCodeTemp = sTLSpaceCodeTemp.substring(2);
            ret += "prnum";
        } else if(sTLSpaceCodeTemp.startsWith("ts")) {
            sTLSpaceCodeTemp = sTLSpaceCodeTemp.substring(2);
            ret += "rdchr";
        } else if(sTLSpaceCodeTemp.startsWith("tt")) {
            sTLSpaceCodeTemp = sTLSpaceCodeTemp.substring(2);
            ret += "rdnum";
        } else{
            messages += "In Line " + lineNumber + " was a Command failure " + "\n";
        }
        return ret;
    }

    private String fc() {
        String ret = "";
        if(sTLSpaceCodeTemp.startsWith("ss")) {
            sTLSpaceCodeTemp = sTLSpaceCodeTemp.substring(2);
            ret += "mark " + label();
        } else if(sTLSpaceCodeTemp.startsWith("st")) {
            sTLSpaceCodeTemp = sTLSpaceCodeTemp.substring(2);
            ret += "callsub " + label();
        } else if(sTLSpaceCodeTemp.startsWith("s\n")) {
            sTLSpaceCodeTemp = sTLSpaceCodeTemp.substring(2);
            lineNumber++;
            ret += "jump " + label();
        } else if(sTLSpaceCodeTemp.startsWith("ts")) {
            sTLSpaceCodeTemp = sTLSpaceCodeTemp.substring(2);
            ret += "jump0 " + label();
        } else if(sTLSpaceCodeTemp.startsWith("tt")) {
            sTLSpaceCodeTemp = sTLSpaceCodeTemp.substring(2);
            ret += "jumpn " + label();
        } else if(sTLSpaceCodeTemp.startsWith("t\n")) {
            lineNumber++;
            sTLSpaceCodeTemp = sTLSpaceCodeTemp.substring(2);
            ret += "end";
        } else if(sTLSpaceCodeTemp.startsWith("\n\n")) {
            lineNumber++;lineNumber++;
            sTLSpaceCodeTemp = sTLSpaceCodeTemp.substring(2);
            ret += "exit";
        } else{
            messages += "In Line " + lineNumber + " was a Command failure " + "\n";
        }
        return ret;
    }

    private long number(){
        String sign = sTLSpaceCodeTemp.substring(0, 1);
        String number = sTLSpaceCodeTemp.substring(1,sTLSpaceCodeTemp.indexOf('\n'));
        lineNumber++;

        sTLSpaceCodeTemp = sTLSpaceCodeTemp.substring(sTLSpaceCodeTemp.indexOf('\n')+1);

        number = number.replace('s', '0');
        number = number.replace('t', '1');

        Long ret = Long.parseLong(number, 2);

        if (sign.equals("t")){
            ret *= -1;
        }
        return ret;
    }

    private String label(){
        String ret = "";
        String label = sTLSpaceCodeTemp.substring(0,sTLSpaceCodeTemp.indexOf('\n'));
        sTLSpaceCodeTemp = sTLSpaceCodeTemp.substring(sTLSpaceCodeTemp.indexOf('\n')+1);
        boolean isString = true;
        lineNumber++;

        while(!label.isEmpty()){
            String temp = label.substring(0,Math.min(8, label.length()));
            temp = temp.replace('s', '0');
            temp = temp.replace('t', '1');
            label = label.substring(Math.min(8, label.length()));

            Integer number = Integer.parseInt(temp, 2);

            if (number < 32){
                ret += number;
                isString = false;
            } else{
                ret += (char)Integer.parseInt(temp, 2);
            }
        }

        if (isString) ret = "\"" + ret;

        return ret;
    }
}
