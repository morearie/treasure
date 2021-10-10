import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class TreasureHunt {
	private static Scanner obj;
	private static Integer X = 4, Y = 1;
	private static Boolean target = false;
	private static char A = '#', B = '.', C = 'X', D = '$';
	private static String iKey = "";

	private static Integer mA = 0, mB = 0, mC = 0;
	private static Integer treasure;
	
	static char paint[][] = { 
			{ A, A, A, A , A, A, A, A},
			{ A, B, B, B , B, B, B, A},
			{ A, B, A, A , A, B, B, A},
			{ A, B, B, B , A, B, A, A},
			{ A, C, A, B , B, B, B, A},
			{ A, A, A, A , A, A, A, A}
			};

	private static void init() {
		treasure = new Random().nextInt(17);
		int count = 0;
		outerloop:
		for (int i = 0; i < paint.length; i++) {
			for (int j = 0; j < paint[i].length; j++) {
				if (paint[i][j] == B) {
					if (count == treasure) {
						paint[i][j] = D;
						break outerloop;
					} else
						count++;
				}
			}
		}
	}

	private static void draw() {
		print2D(paint);
	}
	
	public static void print2D(char paint[][]) {
		// Loop through all rows
		System.out.println("Treasure hunting");
		for (int i = 0; i < paint.length; i++) {
			// Loop through all elements of current row
			for (int j = 0; j < paint[i].length; j++)
				System.out.print(paint[i][j] + " ");
			System.out.println("");
		}
	}
	
	private static void printWIN() throws IOException {
		clrscr();
		System.out.println("YESSSS you won!!");
		System.out.println("move A::" + mA);
		System.out.println("move B::" + mB);
		System.out.println("move C::" + mC);
		int total = mA + mB + mC;
		System.out.println("total move::" + total);
	}
	
	private static void keyInput(String m) {
		switch (m) {
		case "w":
			move(-1, 0, m);
			break;
		case "a":
			move(0, -1, m);
			break;
		case "s":
			move(1, 0, m);
			break;
		case "d":
			move(0, 1, m);
			break;
		}
	}
	
	private static void count(String m) {
		switch (m) {
		case "w":
			mA++;
			break;
		case "a":
			mB++;
			break;
		case "s":
			mC++;
			break;
		case "d":
			mB++;
			break;
		}
	}
	
	private static void move(int x, int y, String m) {
		int tempX = X + x;
		int tempY = Y + y;
		if (tempX < 0 || tempX >= 6 || tempY < 0 || tempY >= 8)
			return;
		if(paint[tempX][tempY] == A)
			return;
		
		if(paint[tempX][tempY] == B) {
			paint[X][Y] = B;
			X=tempX;
			Y=tempY;
			paint[X][Y] = C;
			count(m);
		}
		
		if(paint[tempX][tempY] == D) {
			paint[X][Y] = B;
			X=tempX;
			Y=tempY;
			paint[X][Y] = C;
			count(m);
			target = true;
		}

	}
	
	private static void clrscr() {
		// Clears Screen in java
		try {
			final String os = System.getProperty("os.name");
			if (os.contains("Windows"))
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			else
				Runtime.getRuntime().exec("clear");
		} catch (Exception e) {
			System.out.print("\033[H\033[2J");
			System.out.flush();
		}
	}

	public static void main(String[] args) throws IOException {
		obj = new Scanner(System.in);
		System.out.println("Welcome to treasure game!");
		init();
		draw();
		while (!target) {
			System.out.println("Please insert your move(wasd): ");
			iKey = obj.next();
			keyInput(iKey);
			clrscr();
			draw();
		}
	
		if(target) {
			printWIN();
		}
	}

}
