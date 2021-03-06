package agent;

public class Position {
    private int row;
    private int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == this.getClass()) {
            Position pos = (Position) obj;
            return this.row == pos.getRow() && this.column == pos.getColumn();
        }
        return false;
    }

    @Override
    public String toString() {
        return "[" + row + ";" + column + ']';
    }
}
