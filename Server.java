import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(5000);

            System.out.println("Server started");

            while (true) {
                Socket socket = serverSocket.accept();

                // sending response and read message from client
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

                // read client request
                String message = input.readLine();

                // check if the message follows the protocol
                if (message != null && message.startsWith("BOARD:")) {
                    // remove "BOARD" prefix
                    String boardString = message.substring(6);

                    // decode board
                    Board board = BoardProtocol.decode(boardString);

                    // create computer player
                    ComputerPlayer computer = new ComputerPlayer(2);

                    // ask computer to choose a move
                    int move = computer.chooseMove(board);

                    // send move back to client
                    output.println("MOVE:" + move);
                }

                // Close this client connection
                // Then loop waits for next client
                socket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
