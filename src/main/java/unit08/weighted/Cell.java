package unit08.weighted;

public class Cell {
    private final int row;
    private final int col;
    private final CellType type;

    public Cell(int row, int col, CellType type) {
        this.row = row;
        this.col = col;
        this.type = type;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public CellType getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Cell other = (Cell) obj;
        return row == other.row && col == other.col;
    }

    @Override
    public int hashCode(){
        return 17 * (17 + row) + col;
    }


    @Override
    public String toString() {
        return type.getCode() + "-" + row + "-" + col;
    }
}


