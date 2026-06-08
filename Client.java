public class Client {

    public static void main(String[] args) {

        Board board = new Board();

        Player human = new HumanPlayer(1);

        Player computer = new RemoteComputerPlayer(2, "localhost", 5000);

        Game game = new Game(human, computer, 1);

        game.play();
    }
}