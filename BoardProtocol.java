public class BoardProtocol {

    public static String encode(Board board) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 9; i++) {
            sb.append(board.getCells(i));
        }

        return sb.toString();
    }

    public static Board decode(String data) {
        Board board = new Board();

        for (int i = 0; i < 9; i++) {
            int value = Character.getNumericValue(data.charAt(i));
            board.setCell(i, value);
        }

        return board;
    }
}