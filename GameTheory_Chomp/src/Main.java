import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	static Move lastMove;

	public static void main(String[] args) {
		boolean[][] tab = new boolean[4][5];
		tab[0][0] = true;

		Scanner scr = new Scanner(System.in);

		boolean first = false;
		boolean pvp = false;

		System.out.println(minimax(tab , true));
		
		if (pvp) {
			while (true) {
				System.out.println("matrix : ");
				for (boolean[] bs : tab) {
					for (boolean b : bs) {
						if (b)
							System.out.print(0 + " ");
						else
							System.out.print("O ");
					}
					System.out.println();
				}
				System.out.println("your move : ");
				int row = scr.nextInt() - 1;
				int column = scr.nextInt() - 1;
				Move move = new Move(row, column);
				tab = result(tab, move);
				if (filled(tab)) {
					System.out.println("Player1 Won!");
					return;
				}
				
				System.out.println("matrix : ");
				for (boolean[] bs : tab) {
					for (boolean b : bs) {
						if (b)
							System.out.print(0 + " ");
						else
							System.out.print("O ");
					}
					System.out.println();
				}
				System.out.println("your move : ");
				int row2 = scr.nextInt() - 1;
				int column2 = scr.nextInt() - 1;
				Move move2 = new Move(row2, column2);
				tab = result(tab, move2);
				if (filled(tab)) {
					System.out.println("Player2 Won!");
					return;
				}
			}
		}
		
		else if (first) {

			while (true) {
				System.out.println("matrix : ");
				for (boolean[] bs : tab) {
					for (boolean b : bs) {
						if (b)
							System.out.print(0 + " ");
						else
							System.out.print("O ");
					}
					System.out.println();
				}
				System.out.println("your move : ");
				int row = scr.nextInt() - 1;
				int column = scr.nextInt() - 1;
				Move move = new Move(row, column);
				tab = result(tab, move);
				if (filled(tab)) {
					System.out.println("Won!");
					return;
				}
				
				minimax(tab, false);
				System.out.println("bot moved : (" + (lastMove.i + 1) + " , " + (lastMove.j + 1) + ")");
				tab = result(tab, lastMove);
				if (filled(tab)) {
					System.out.println("Lost!");
					return;
				}
			}
		}
		
		else {
			while (true) {
				minimax(tab, true);
				System.out.println("bot moved : (" + (lastMove.i + 1) + " , " + (lastMove.j + 1) + ")");
				tab = result(tab, lastMove);
				if (filled(tab)) {
					System.out.println("Lost!");
					return;
				}
				System.out.println("matrix : ");
				for (boolean[] bs : tab) {
					for (boolean b : bs) {
						if (b)
							System.out.print(0 + " ");
						else
							System.out.print("O ");
					}
					System.out.println();
				}
				System.out.println("your move : ");
				int row = scr.nextInt() - 1;
				int column = scr.nextInt() - 1;
				Move move = new Move(row, column);
				tab = result(tab, move);
				if (filled(tab)) {
					System.out.println("Won!");
					return;
				}

			}
		}
	}

	static int minimax(boolean[][] tab, boolean maxPlayer) { // player may be "computer" or "opponent"

		if (maxPlayer) { // max player
			int bestScore = -10000000;
			ArrayList<Move> moves = possibleMoves(tab);
			for (Move move : moves) {
				boolean[][] tempTab = result(tab, move);
				if (filled(tempTab)) {
					lastMove = move;
					return 1;
				} else {
					int score = minimax(tempTab, !maxPlayer);
					if (score > bestScore)
						bestScore = score;
					if (bestScore == 1) {
						lastMove = move;
						return 1;
					}

				}
			}
			return bestScore;
		}

		else { // min player
			int bestScore = +10000000;
			ArrayList<Move> moves = possibleMoves(tab);
			for (Move move : moves) {
				boolean[][] tempTab = result(tab, move);
				if (filled(tempTab)) {
					lastMove = move;
					return -1;
				} else {
					int score = minimax(tempTab, !maxPlayer);
					if (score < bestScore)
						bestScore = score;
					if (bestScore == -1) {
						lastMove = move;
						return -1;
					}
				}
			}
			return bestScore;
		}
	}

	private static boolean filled(boolean[][] input) {
		for (boolean[] row : input) {
			for (boolean b : row) {
				if (b == false) {
					return false;
				}
			}
		}
		return true;
	}

	private static boolean[][] result(boolean[][] tab, Move move) {
		int i = move.i;
		int j = move.j;

		boolean[][] tmp = new boolean[tab.length][tab[0].length];

		for (int ii = 0; ii < tmp.length; ii++) {
			for (int jj = 0; jj < tmp[0].length; jj++) {
				tmp[ii][jj] = tab[ii][jj];
			}
		}

		for (int ii = i; ii < tab.length; ii++) {
			for (int jj = j; jj < tab[0].length; jj++) {
				tmp[ii][jj] = true;
			}
		}
		return tmp;
	}

	private static ArrayList<Move> possibleMoves(boolean[][] tab) {
		ArrayList<Move> moves = new ArrayList<Move>();
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab[0].length; j++) {
				if (tab[i][j] == false) {
					moves.add(new Move(i, j));
				}
			}
		}
		return moves;
	}
}