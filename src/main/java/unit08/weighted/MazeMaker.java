package unit08.weighted;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MazeMaker {

    public static PacManMaze loadMaze(String filename) throws IOException {
        PacManMaze maze = new PacManMaze();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] nodes = line.split(" ");
                for (String nodeStr : nodes) {
                    Cell node = parseNode(nodeStr);
                    maze.add(node);
                }
            }
        }
        maze.connectAdjacentNodes();
        return maze;
    }

    public static Cell parseNode(String rawNode) {
        String[] nodeData = rawNode.split("-");
        Cell node;

        if ("PE".equals(nodeData[0])) {
            node = new Cell(Integer.parseInt(nodeData[2]), Integer.parseInt(nodeData[1]), CellType.PELLET);
        } else if ("PP".equals(nodeData[0])) {
            node = new Cell(Integer.parseInt(nodeData[2]), Integer.parseInt(nodeData[1]), CellType.POWER_PELLET);
        } else if ("PM".equals(nodeData[0])) {
            node = new Cell(Integer.parseInt(nodeData[2]), Integer.parseInt(nodeData[1]), CellType.PAC_MAN);
        } else {
            node = new Cell(Integer.parseInt(nodeData[2]), Integer.parseInt(nodeData[1]), CellType.GHOST);
        }
        return node;
    }

    public static void main(String[] args) {
        System.out.println("Enter the name of the PacMan Maze file (.maz): ");
        Scanner scanner = new Scanner(System.in);
        String filename = scanner.next();
        try {
            PacManMaze maze = loadMaze(filename);
            Cell currentPosition = maze.getPacManPosition();
            for (Cell energizer : maze.getEnergizers()) {
                WPath<Cell> path = maze.dijkstrasPath(currentPosition, energizer);
                if (path != null) {
                    System.out.println("Path to energizer: " + path);
                } else {
                    System.out.println("No path found to energizer: " + energizer);
                }
                maze.updatePacManPosition(energizer);
                currentPosition = maze.getPacManPosition();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading the maze.");
        }
        scanner.close();
    }
}