package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Controller {

    @FXML
    VBox vBox;

    @FXML
    TextField sndMsgTextField;

    @FXML
    TextArea chatTextArea;

    public void sendMsg(ActionEvent actionEvent) {
        String msg = sndMsgTextField.getText();
        if (!msg.equals("")) {
            msg += "\n";
            chatTextArea.appendText(msg);
            sndMsgTextField.clear();
        }
    }
}
