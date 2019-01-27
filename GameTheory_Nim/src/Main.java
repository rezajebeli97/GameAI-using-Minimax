import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	static Move lastMove;

	public static void main(String[] args) {
		int[] tab = new int[3];
		tab[0] = 3;
		tab[1] = 2;
		tab[2] = 1;
		
		Scanner scr = new Scanner(System.in);

		boolean first = true;
		boolean pvp = false;

		if (pvp) {
			while (true) {
				System.out.println("matrix : ");
				for (int i : tab) {
					System.out.print(i + " ");
				}
				System.out.println();
				
				System.out.println("your move : ");
				int row = scr.nextInt() - 1;
				int column = scr.nextInt();
				Move move = new Move(row, column);
				tab = result(tab, move);
				if (filled(tab)) {
					System.out.println("Player1 Won!");
					return;
				}
				
				System.out.println("matrix : ");
				for (int i : tab) {
					System.out.print(i + " ");
				}
				System.out.println();
				
				System.out.println("your move : ");
				int row2 = scr.nextInt() - 1;
				int column2 = scr.nextInt();
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
				for (int i : tab) {
					System.out.print(i + " ");
				}
				System.out.println();
				
				System.out.println("your move : ");
				int row = scr.nextInt() - 1;
				int column = scr.nextInt();
				Move move = new Move(row, column);
				tab = result(tab, move);
				if (filled(tab)) {
					System.out.println("Won!");
					return;
				}
				
				int score = minimax(tab, false);
				System.out.println("score : " + score);
				System.out.println("bot moved : (" + (lastMove.i + 1) + " , " + lastMove.j + ")");
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
				System.out.println("bot moved : (" + (lastMove.i + 1) + " , " + lastMove.j+ ")");
				tab = result(tab, lastMove);
				if (filled(tab)) {
					System.out.println("Lost!");
					return;
				}
				System.out.println("matrix : ");
				for (int i : tab) {
					System.out.print(i + " ");
				}
				System.out.println();
				
				System.out.println("your move : ");
				int row = scr.nextInt() - 1;
				int column = scr.nextInt();
				Move move = new Move(row, column);
				tab = result(tab, move);
				if (filled(tab)) {
					System.out.println("Won!");
					return;
				}

			}
		}
	}

	static int minimax(int[] tab, boolean maxPlayer) { // player may be "computer" or "opponent"

		if (maxPlayer) { // max player
			int bestScore = -10000000;
			ArrayList<Move> moves = possibleMoves(tab);
			for (Move move : moves) {
				int[] tempTab = result(tab, move);
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
				int[] tempTab = result(tab, move);
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

	private static boolean filled(int[] input) {
		for (int i : input) {
			if (i != 0) {
				return false;
			}
		}
		return true;
	}

	private static int[] result(int[] tab, Move move) {
		int i = move.i;
		int j = move.j;

		int[] tmp = new int[tab.length];

		for (int ii = 0; ii < tmp.length; ii++) {
				tmp[ii] = tab[ii];
		}

		tmp[i] -= j;
		return tmp;
	}

	private static ArrayList<Move> possibleMoves(int[] tab) {
		ArrayList<Move> moves = new ArrayList<Move>();
		for (int i = 0; i < tab.length; i++) {
			for (int j = 1; j <= tab[i]; j++) {
				moves.add(new Move(i, j));
			}
		}
		return moves;
	}
}