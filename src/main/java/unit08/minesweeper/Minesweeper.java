package unit08.minesweeper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

import unit08.backtracker.Backtracker;
import unit08.backtracker.Configuration;

public class Minesweeper implements Configuration<Minesweeper> {
    private char[][] board;
    private char[][] map;
    private int rows;
    private int cols;
    private int moveCount;

    public Minesweeper(String filename, int startRow, int startCol) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        rows = scanner.nextInt();
        cols = scanner.nextInt();
        map = new char[rows][cols];
        board = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            String line = scanner.next();
            for (int j = 0; j < cols; j++) {
                map[i][j] = line.charAt(j);
                board[i][j] = '-';
            }
        }

        board[startRow][startCol] = '1';
        moveCount = 1;
    }

    private Minesweeper(Minesweeper other) {
        rows = other.rows;
        cols = other.cols;
        map = other.map;
        board = Arrays.copyOf(other.board, other.board.length);
        moveCount = other.moveCount;
    }

    @Override
    public Collection<Minesweeper> getSuccessors() {
        Collection<Minesweeper> successors = new ArrayList<>();
    
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == '-') {
                    Minesweeper uncovered = new Minesweeper(this);
                    uncovered.board[i][j] = (char) (uncovered.moveCount + '0');
                    uncovered.moveCount++;
                    successors.add(uncovered);
    
                    if (map[i][j] == '*') {
                        Minesweeper flagged = new Minesweeper(this);
                        flagged.board[i][j] = 'F';
                        successors.add(flagged);
                    }
                }
            }
        }
    
        return successors;
    }

    @Override
    public boolean isValid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] != 'F' && board[i][j] != '-' && map[i][j] == '*') {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean isGoal() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (map[i][j] == '*' && board[i][j] != 'F') {
                    return false;
                }
                if (map[i][j] == '-' && board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sb.append(board[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the map file: ");
        String filename = scanner.nextLine();
        System.out.print("Enter the row and column to start your sweep: ");
        int startRow = scanner.nextInt();
        int startCol = scanner.nextInt();

        Backtracker<Minesweeper> backtracker = new Backtracker<>(false);
        Minesweeper startConfig = new Minesweeper(filename, startRow, startCol);
        Minesweeper solution = backtracker.solve(startConfig);

        if (solution != null) {
            System.out.println(solution);
        } else {
            System.out.println("Not able to find a solution for this.");
        }
    }
}
