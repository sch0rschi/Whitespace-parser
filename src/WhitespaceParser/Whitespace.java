package WhitespaceParser;

public class Whitespace {

    private String whitespaceCode;

    public Whitespace(String whiteSpaceCode) {
        this.whitespaceCode = whiteSpaceCode;
    }

    public String getWhitespaceCode() {
        return whitespaceCode;
    }

    public String toSTLSpaceCode(){
        String sTLSpaceCode = "";

        for(char symbol : whitespaceCode.toCharArray()){
            switch (symbol){
                case ' ': sTLSpaceCode += "s";
                    break;
                case '\t': sTLSpaceCode += "t";
                    break;
                case '\n': sTLSpaceCode += "\n";
                    break;
                default: break;
            }
        }
        return sTLSpaceCode;
    }
}
