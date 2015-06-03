package WhitespaceParser;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class Controller {

    @FXML
    private TextArea codeLeft;
    @FXML
    private TextArea codeMiddle;
    @FXML
    private TextArea codeRight;
    @FXML
    private Button buttonLeft;
    @FXML
    private Button buttonMiddle;
    @FXML
    private Button buttonRight;
    @FXML
    private TextArea console;

    private static String messages;

    public static void setMessages(String message) {
        messages = message;
    }

    @FXML
    private void onButtonLeft(){
        PseudoCode pseudoCode = new PseudoCode(codeLeft.getText());
        STLSpace sTLSpace = new STLSpace(pseudoCode.toSTLSpace());
        Whitespace whitespace = new Whitespace(sTLSpace.toWhitespaceCode());
        codeMiddle.setText(sTLSpace.getsTLSpaceCode());
        codeRight.setText(whitespace.getWhitespaceCode());
        console.setText(messages);
    }

    @FXML
    private void onButtonMiddle(){
        STLSpace stlSpace = new STLSpace(codeMiddle.getText());
        PseudoCode pseudoCode = new PseudoCode(stlSpace.toPseudoCode());
        Whitespace whitespace = new Whitespace(stlSpace.toWhitespaceCode());
        codeLeft.setText(pseudoCode.getPseudoCode());
        codeRight.setText(whitespace.getWhitespaceCode());
        console.setText(messages);
    }

    @FXML
    private void onButtonRight(){
        Whitespace whitespace = new Whitespace(codeRight.getText());
        STLSpace sTLSpace = new STLSpace(whitespace.toSTLSpaceCode());
        PseudoCode pseudoCode = new PseudoCode(sTLSpace.toPseudoCode());
        codeMiddle.setText(sTLSpace.getsTLSpaceCode());
        codeLeft.setText(pseudoCode.getPseudoCode());
        console.setText(messages);
    }
}
