package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Controller {

    @FXML
    VBox vBox;

    @FXML
    TextArea chatTextArea;

    @FXML
    HBox authBox;

    @FXML
    TextField loginField;

    @FXML
    PasswordField passwordField;

    @FXML
    HBox sendMsgBox;

    @FXML
    TextField sndMsgTextField;

    private boolean isAuthorized;
    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    final String IP_ADRESS = "localhost";
    final int PORT = 8189;


    // метод для показа нижней панели или верхней
    public void setAuthorized(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;

        if(!isAuthorized) {
            authBox.setVisible(true);
            authBox.setManaged(true);
            sendMsgBox.setVisible(false);
            sendMsgBox.setManaged(false);
        } else {
            authBox.setVisible(false);
            authBox.setManaged(false);
            sendMsgBox.setVisible(true);
            sendMsgBox.setManaged(true);
        }
    }

    public void connect() {
        try {
            socket = new Socket(IP_ADRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // блок для авторизации
                        while (true) {
                            String str = in.readUTF();
                            if(str.startsWith("/authok")) {
                                setAuthorized(true);
                                break;
                            } else {
                                chatTextArea.appendText(str + "\n");
                            }
                        }

                        // блок для разбора сообщений
                        while (true) {
                            String str = in.readUTF();
                            if(str.equals("/serverClosed")) {
                                break;
                            }
                            chatTextArea.appendText(str + "\n");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        setAuthorized(false);
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tryToAuth() {
        if(socket == null || socket.isClosed()) {
            // сначала подключаемся к серверу
            connect();
        }
        try {
            // отправка сообщений на сервер для авторизации
            out.writeUTF("/auth " + loginField.getText() + " " + passwordField.getText());
            loginField.clear();
            passwordField.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(ActionEvent actionEvent) {
        try {
            out.writeUTF(sndMsgTextField.getText());
            sndMsgTextField.clear();
            sndMsgTextField.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
