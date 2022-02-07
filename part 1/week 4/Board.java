import edu.princeton.cs.algs4.Stack;

public class Board {
    private final int[][] tiles;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.tiles = copy(tiles);
    }

    @Override
    // string representation of this board
    public String toString() {
        StringBuilder s = new StringBuilder(this.dimension() + "\n");
        for (int i = 0; i < this.dimension(); i++) {
            for (int j = 0; j < this.dimension(); j++) {
                s.append(String.format("%2d ", tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    // board dimension n
    public int dimension() {
        return this.tiles.length;
    }

    // number of tiles out of place
    public int hamming() {
        int hamDist = 0;
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (this.tiles[i][j] != 0 && this.tiles[i][j] != j + i * dimension() + 1) hamDist++;
            }
        }
        return hamDist;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int manDist = 0;
        int x, y;
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (this.tiles[i][j] != 0 && this.tiles[i][j] != j + i * dimension() + 1) {
                    x = (this.tiles[i][j] - 1) / dimension();
                    y = (this.tiles[i][j] - 1) % dimension();
                    manDist += Math.abs(x - i) + Math.abs(y - j);
                }
            }
        }
        return manDist;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return this.manhattan() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        return this == y;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Stack<Board> nbh = new Stack<>();
        int m = this.blankPosition();
        int i = (dimension() != 0) ? m / dimension() : 0;
        int j = (dimension() != 0) ? m % dimension() : m;
        if (i > 0) nbh.push(new Board(this.move(i, j, i - 1, j)));
        if (i < dimension() - 1) nbh.push(new Board(this.move(i, j, i + 1, j)));
        if (j > 0) nbh.push(new Board(this.move(i, j, i, j - 1)));
        if (j < dimension() - 1) nbh.push(new Board(this.move(i, j, i - 1, j)));
        return nbh;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[][] twinTiles = copy(this.tiles);

        if (twinTiles[0][0] != 0 && twinTiles[0][1] != 0)
            return new Board(move(0, 0, 0, 1));
        else
            return new Board(move(1, 0, 1, 1));
    }

    private int blankPosition() {
        for (int i = 0; i < dimension(); i++)
            for (int j = 0; j < dimension(); j++)
                if (tiles[i][j] == 0)
                    return i * dimension() + j;
        return -1;
    }

    private int[][] copy(int[][] myTiles) {
        int[][] copy = new int[myTiles.length][myTiles.length];
        for (int i = 0; i < myTiles.length; i++) {
            for (int j = 0; j < myTiles.length; j++) {
                copy[i][j] = myTiles[i][j];
            }
        }
        return copy;
    }

    private int[][] move(int i, int j, int x, int y) {
        int[][] moved = this.copy(this.tiles);
        moved[i][j] = moved[x][y];
        moved[x][y] = 0;
        return moved;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        // [UncommentedEmptyMethodBody]
        Board board = new Board(new int[][]{{1, 2, 3}, {4, 0, 6}, {7, 8, 5}});
        System.out.println(board.toString());
    }

}
