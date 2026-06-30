import java.io.*;
import java.net.Socket;

public class RemoteComputerPlayer extends Player {

    private String host;
    private int port;

    public RemoteComputerPlayer(int mark, String host, int port) {
        super(mark);
        this.host = host;
        this.port = port;
    }

    @Override
    public void makeMove(Board board) {
        try {
            Socket socket = new Socket(host, port);

            // receiving and sending data to server
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            // encode board and send it to server
            output.println("BOARD:" + BoardProtocol.encode(board));

            // server response
            String response = input.readLine();

            // check if server response is valid
            if (response != null && response.startsWith("MOVE:")) {
                int move = Integer.parseInt(response.substring(5).trim());

                // update local board with computer mark for valid move
                if (move != -1) {
                    board.setCell(move, mark);
                }
            }

            // Close connection after one request-response
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
