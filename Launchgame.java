package pack1;
import java.util.Random;
import java.util.Scanner;


class Tictactoe{
	static char[][] board;
	public Tictactoe() {
		board = new char[3][3];
		initBoard();
	}
	
	public void initBoard() {
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[i].length;j++) {
				board[i][j]=' ';
			}
		}
	}
	
	
	static void dispBoard() {
		System.out.println(" ----------- ");
		for(int i=0;i<board.length;i++) {
			System.out.print("| ");
			for(int j=0;j<board[i].length;j++) {
				
				System.out.print(board[i][j]+" | ");
			}
			System.out.println();
			System.out.println(" ----------- ");
		}
	}
	
	static void placeMark(int row,int col,char mark) {
		if(row>=0 && row<=2 && col>=0 && col<=2) {
			if(board[row][col] ==' ' ) {
				board[row][col] = mark;
			}
			else {
				System.out.println("Invalid position!!!");
			}
		}
	}
	
	public static boolean checkColWin() {
		for(int j=0;j<=2;j++) {
			if(board[0][j] != ' ' && board[0][j]==board[1][j] && board[1][j]==board[2][j]) {
				return true;
			}
		}
		return false;
	}
	
	
	public static boolean checkRowWin() {
		for(int i=0;i<=2;i++) {
			if(board[i][0] != ' ' && board[i][0]==board[i][1] && board[i][1]==board[i][2]) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean checkDiagWin() {
		if(board[0][0] != ' ' && board[0][0]==board[1][1] && board[1][1]==board[2][2] || board[0][2] != ' ' && board[0][2]==board[1][1] && board[1][1]==board[2][0]) {
			return true;
		}
		return false;
	}
	
	
	static boolean checkDraw() {
		for(int i=0;i<=2;i++) {
			for(int j=0;j<=2;j++) {
				if(Tictactoe.board[i][j]==' ') {
					return false;
				}
			}
		}
		return true;
	}
	
	
}


abstract class Player{
	String name;
	char mark;
	abstract void makeMove();
	boolean isValid(int row,int col) {
		if(row>=0 && row<=2 && col>=0 && col<=2) {
			if(Tictactoe.board[row][col]==' ') {
				return true;
			}
		}
		return false;
	}
}


class HumanPlayer extends Player{
	HumanPlayer(String name,char mark){
		this.name=name;
		this.mark=mark;
	}
	void makeMove() {
		int row;
		int col;
		Scanner scan = new Scanner(System.in);
		do {
			System.out.println("Enter the row and col");
			row = scan.nextInt();
			col = scan.nextInt();
		} while (!isValid(row,col));
		Tictactoe.placeMark(row, col, mark);
	}
}



class AIPlayer extends Player{
	AIPlayer(String name,char mark){
		this.name = name;
		this.mark = mark;
	}
	void makeMove() {
		int row;
		int col;
		do {
			Random r = new Random();
			row = r.nextInt(3);
			col = r.nextInt(3);
		} while (!isValid(row, col));
		
		Tictactoe.placeMark(row, col, mark);
	}
}










public class Launchgame {

	public static void main(String[] args) {
		Tictactoe t = new Tictactoe();
		HumanPlayer p1 = new HumanPlayer("Allex",'x');
		AIPlayer p2 = new AIPlayer("AI",'o');
		Player cp;
		cp=p1;
		Tictactoe.dispBoard();
		
		while(true) {
			System.out.println(cp.name+" turn");
			cp.makeMove();
			Tictactoe.dispBoard();
			if(Tictactoe.checkColWin()||Tictactoe.checkRowWin()||Tictactoe.checkDiagWin()) {
				System.out.println(cp.name+" has won");
				break;
			}
			else if(Tictactoe.checkDraw()) {
				System.out.println("game is draw");
				break;
			}
			else {
				if(cp==p1) {
					cp=p2;
				}
				else {
					cp=p1;
				}
			}
			
		}
		
	}
}
