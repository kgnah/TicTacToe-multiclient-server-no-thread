import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(5000);

            System.out.println("Server started");

            while (true) {
                Socket socket = serverSocket.accept();

                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

                String message = input.readLine();

                if (message != null && message.startsWith("BOARD:")) {
                    String boardString = message.substring(6);

                    Board board = BoardProtocol.decode(boardString);

                    ComputerPlayer computer = new ComputerPlayer(2);

                    int move = computer.chooseMove(board);

                    output.println("MOVE:" + move);
                }

                socket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}