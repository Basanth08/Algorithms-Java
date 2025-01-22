package unit08.weighted;
import java.util.HashSet;
import java.util.Set;

public class PacManMaze extends WAdjacencyGraph<Cell> {
    private Cell pacManPosition;
    private Set<Cell> energizers;
    private Set<Cell> mazeNodes;

    public PacManMaze() {
        energizers = new HashSet<>();
        mazeNodes = new HashSet<>();
    }

    @Override
    public void add(Cell node) {
        super.add(node);
        mazeNodes.add(node);

        if (node.getType() == CellType.PAC_MAN) {
            pacManPosition = node;
        }
        if (node.getType() == CellType.POWER_PELLET) {
            energizers.add(node);
        }
    }

    public Set<Cell> getMazeNodes() {
        return mazeNodes;
    }

    public Cell getPacManPosition() {
        return pacManPosition;
    }

    public void updatePacManPosition(Cell newPosition) {
        this.pacManPosition = newPosition;
    }

    public Set<Cell> getEnergizers() {
        return energizers;
    }

    public void connectAdjacentNodes() {
        for (Cell node1 : mazeNodes) {
            for (Cell node2 : mazeNodes) {
                if (node1.equals(node2)) {
                    continue;
                }

                int rowDifference = Math.abs(node1.getRow() - node2.getRow());
                int colDifference = Math.abs(node1.getCol() - node2.getCol());
                int manhattanDistance = rowDifference + colDifference;
                if (manhattanDistance == 1) {
                    connect(node1, node2, 1.0);
                }
            }
        }
    }
}
