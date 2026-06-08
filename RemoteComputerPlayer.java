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

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            output.println("BOARD:" + BoardProtocol.encode(board));

            String response = input.readLine();

            if (response != null && response.startsWith("MOVE:")) {
                int move = Integer.parseInt(response.substring(5).trim());

                if (move != -1) {
                    board.setCell(move, mark);
                }
            }

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}