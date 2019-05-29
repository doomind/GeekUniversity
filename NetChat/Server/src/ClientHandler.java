
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientHandler implements Runnable {
    private DataInputStream in;
    private DataOutputStream out;
    private Socket socket;
    private MainServer server;
    private String nick;

    public ClientHandler(Socket socket, MainServer server) {
        try {
            this.socket = socket;
            this.server = server;
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            new Thread(this).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            // цикл для авторизации
            while (true) {
                String str = in.readUTF();
                // если сообщение начинается с /auth
                if(str.startsWith("/auth")) {
                    String[] tokens = str.split(" ");
                    // Вытаскиваем данные из БД
                    String newNick = AuthService.getNickByLoginAndPass(tokens[1], tokens[2]);
                    if (newNick != null) {
                        // отправляем сообщение об успешной авторизации
                        sendMsg("/authok");
                        nick = newNick;
                        server.subscribe(ClientHandler.this);
                        System.out.println(nick + " подключился.");
                        break;
                    } else {
                        sendMsg("Неверный логин/пароль!");
                    }
                }
            }

            // блок для отправки сообщений
            while (true) {
                String str = in.readUTF();
                if(str.equals("/end")) {
                    out.writeUTF("/serverClosed");
                    break;
                // Реализация whisper
                } else if (str.matches("^\\/w \\w+ [\\w\\W\\s]*$")) {
                    String regex = "^\\/w\\s(?<nickName>[a-zA-Z0-9]+)\\s(?<message>.*)$";
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(str);
                    matcher.find();
                    String nickName = matcher.group("nickName");
                    String message = matcher.group("message");
                    String msg = nick + ": " + message;
                    server.unicastMsg(nickName, msg);
                } else {
                    server.broadcastMsg(nick + ": " + str);
                }
            }
        }  catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            server.unsubscribe(ClientHandler.this);
        }
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
