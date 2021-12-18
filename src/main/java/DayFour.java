import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.nio.file.Files.readAllLines;
import static java.util.Arrays.stream;

public class DayFour
{
    final Integer MATRIX_SIZE = 5;
    List<Integer> bingoNumbers;
    List<Bingo> bingoBoards;

    public DayFour(String path)
    {

        List<String> data;
        try {
            data = readAllLines(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException("Unable to read input data!!!\n" + e);
        }

        bingoNumbers = stream(data.get(0).split(",")).map(Integer::parseInt).collect(Collectors.toList());
        System.out.println("Bingo numbers are: " + bingoNumbers + "\n");

        createBingoBoards(data);
    }

    public void play()
    {
        for (Integer number : bingoNumbers) {
            System.out.println("Playing number:" + number);
            for (Bingo board : bingoBoards) {
                if (board.checkNumber(number)) {
                    System.out.println("Winner is:");
                    board.print();

                    System.out.println("Win number is " + number + ".Final score is:" + sumWinner(board, number));
                    return;
                }
            }
        }
        System.out.println("Nobody won?");
    }

    public void playForSquid()
    {
        List<Integer> playingBoardNumbers = new ArrayList<>();
        for (int i = 0; i <= bingoBoards.size() - 1; i++) {
            playingBoardNumbers.add(i + 1);
        }
        System.out.println("Initial board numbers: " + playingBoardNumbers);

        int lastToWinIndex = -1;
        int lastWinNumber = -1;
        for (Integer number : bingoNumbers) {
            System.out.println("Playing number:" + number);
            for (Bingo board : bingoBoards) {
                if (playingBoardNumbers.contains(bingoBoards.indexOf(board))) {
                    if (board.checkNumber(number)) {
                        System.out.println("Board number " + (bingoBoards.indexOf(board) + 1) + "won - shall be skipped if not last");
                        if (playingBoardNumbers.size() == 1) {
                            System.out.println("This is the last board - make a magic please");
                            return;
                        }
                        playingBoardNumbers.remove((Integer) bingoBoards.indexOf(board));
                        System.out.println("Last won index" + bingoBoards.indexOf(board));
                        lastToWinIndex = bingoBoards.indexOf(board);
                        lastWinNumber = number;
                    }
                }
            }
        }

        System.out.println("Squid last final score is:" + sumWinner(bingoBoards.get(lastToWinIndex), lastWinNumber));
    }

    private Integer sumWinner(Bingo board, Integer winNumber)
    {

        int sum = 0;
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                if (!board.winMarkers[i][j])
                    sum = sum + board.board[i][j];
            }
        }

        return sum * winNumber;
    }

    private void createBingoBoards(List<String> data)
    {
        bingoBoards = new ArrayList<>(5);

        for (int i = 2; i < data.size(); i = i + 6) {
            Bingo board = new Bingo();

            for (int j = 0; j < 5; j++) {

                board.setBoardRow(j, stream(data.get(i + j).split(" "))
                        .filter(x -> !x.isEmpty())
                        .map(Integer::parseInt)
                        .toArray(Integer[]::new));
            }

            bingoBoards.add(board);
        }

        System.out.println("Board players created:");
        bingoBoards.forEach(Bingo::print);
    }


    private class Bingo
    {
        Integer[][] board;
        Boolean[][] winMarkers;

        Bingo()
        {
            board = new Integer[MATRIX_SIZE][MATRIX_SIZE];
            winMarkers = new Boolean[MATRIX_SIZE][MATRIX_SIZE];
            for (int i = 0; i < MATRIX_SIZE; i++) {
                for (int j = 0; j < MATRIX_SIZE; j++) {
                    winMarkers[i][j] = false;
                }
            }
        }

        void setBoardRow(int rowNum, Integer[] row)
        {
            board[rowNum] = row;
        }

        public void print()
        {
            System.out.println("Bingo card");
            for (int i = 0; i < MATRIX_SIZE; i++) {
                System.out.println(Arrays.toString(board[i]) + " " + Arrays.toString(winMarkers[i]));
            }
        }

        boolean checkNumber(int number)
        {

            for (int i = 0; i < MATRIX_SIZE; i++) {
                for (int j = 0; j < MATRIX_SIZE; j++) {
                    if (board[i][j] == number) {
                        winMarkers[i][j] = true;
                        return verifyWin();
                    }
                }
            }

            return false;
        }

        boolean verifyWin()
        {
            if (stream(winMarkers).anyMatch(x -> stream(x).allMatch(y -> y)))
                return true;

            for (int i = 0; i < MATRIX_SIZE; i++) {
                int finalI = i;
                if (IntStream.range(0, MATRIX_SIZE).allMatch(x -> winMarkers[x][finalI]))
                    return true;
            }
            return false;
        }
    }

}
