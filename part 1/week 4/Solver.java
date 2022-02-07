import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {
    private boolean isSolvable;
    private final Stack<Board> solutions;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();
        this.isSolvable = false;
        this.solutions = new Stack<>();

        MinPQ<SearchNode> searchNodes = new MinPQ<>();

        searchNodes.insert(new SearchNode(initial, null));
        searchNodes.insert(new SearchNode(initial.twin(), null));

        while (!searchNodes.min().board.isGoal()) {
            SearchNode minNode = searchNodes.delMin();
            for (Board board : minNode.board.neighbors()) {
                if (minNode.prevNode == null || !minNode.prevNode.board.equals(board)) {
                    searchNodes.insert(new SearchNode(board, minNode));
                }
            }
        }

        SearchNode current = searchNodes.min();
        while (current.prevNode != null) {
            solutions.push(current.board);
            current = current.prevNode;
        }
        solutions.push(current.board);

        if (current.board.equals(initial)) this.isSolvable = true;
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return this.isSolvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (this.isSolvable()) return this.solutions.size() - 1;
        return -1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (this.isSolvable()) return this.solutions;
        return null;
    }

    // test client (see below)
    public static void main(String[] args) {
        // [UncommentedEmptyMethodBody]
    }

    private class SearchNode implements Comparable<SearchNode> {
        private final int moves;
        private final Board board;
        private final SearchNode prevNode;
        private final int manhattan;

        public SearchNode(Board board, SearchNode prevNode) {
            this.board = board;
            this.prevNode = prevNode;
            this.manhattan = board.manhattan();
            if (prevNode != null) this.moves = prevNode.moves + 1;
            else this.moves = 0;
        }

        public int compareTo(SearchNode that) {
            int priority = (this.manhattan + this.moves) - (that.manhattan + that.moves);
            return priority == 0 ? this.manhattan - that.manhattan : priority;
        }

    }
}

