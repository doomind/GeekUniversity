
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;

class MainServer {

    private Vector<ClientHandler> clients;

MainServer() throws SQLException {
        ServerSocket server = null;
        Socket socket = null;
        clients = new Vector<>();

        try {
            AuthService.connect();

           // System.out.println(AuthService.getNickByLoginAndPass("login12", "pass1"));

            server = new ServerSocket(8189);
            System.out.println("Сервер запущен");

            while (true) {
                socket = server.accept();
                new ClientHandler(socket, this);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            AuthService.disconnect();
        }
    }

    // подписываем клиента на рассылку
    void subscribe(ClientHandler client) {
        clients.add(client);
    }

    // отписываем клиента от рассылки сообщений
    synchronized void unsubscribe(ClientHandler client){
        clients.remove(client);
    }

    void broadcastMsg(String msg) {
        for (ClientHandler o: clients) {
            o.sendMsg(msg);
        }
    }

    void unicastMsg(String nickName, String msg) {
        for (ClientHandler o: clients) {
            if (o.getNick().equals(nickName)) {
                o.sendMsg(msg);
            }
        }
    }

}
