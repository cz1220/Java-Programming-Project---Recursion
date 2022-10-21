package connect4;
import java.util.Scanner;

public class connect4 {
    static final int X = 1, O = 2;
    static Scanner input = new Scanner(System.in);
    static int size;
    static int firstplayer;
    static int p1 = 0, p2 = 0, ties = 0;
    static long cnt = 0;

    public static void main(String[] args) {
        System.out.print("Enter '3' for 3x3 mode, or Enter '4' for 4x4 mode: ");
        size = input.nextInt();
        for (int i = 0; i < size; i++) {
            int[][] list = new int[size][size];
            firstplayer = X;
            p1 = 0;
            p2 = 0;
            cnt = 0;
            ties = 0;
            list[size - 1][i] = X;
            Play(list, O);
            System.out.println("NetWins for column " + i + ": " + Math.abs(p1 - p2));
            System.out.println("Recursion calls: " + cnt);
            System.out.println("Player1-Wins: " + p1 + " Player2-Wins: " + p2);
            System.out.println("******************");
        }
    }

    public static int Play(int[][] inlist, int clr) {
        cnt++;
        
        int res = checkBoard(inlist, clr);
       
        if (res < 3) {
            if (res == 0) {
                ties++;
                return 0;
            } else {
                if (res == firstplayer) {
                    p1++;
                    return 1;
                } else {
                    p2++;
                    return -1;
                }
            }
        }

        res = 0;

        for (int col = 0; col < size; col++) {
            int emptyRow = -1;
            for (int row = size - 1; row >= 0; row--) {
                if (inlist[row][col] == 0) {
                    emptyRow = row;
                    break;
                }
            }
            if (emptyRow != -1) {
                int[][] clonelist = new int[size][size];
                for (int x = 0; x < size; x++) {
                    for (int y = 0; y < size; y++) {
                        clonelist[x][y] = inlist[x][y];
                    }
                }
                clonelist[emptyRow][col] = clr;
                Play(clonelist, 3 - clr);
            }
        }

        // recursively call Play
        return res;
    }

    public static boolean isFull(int[][] inlist) {
        boolean empty = true;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (inlist[i][j] == 0) {
                    empty = false;
                    break;
                }
            }
        }
        return empty;
    }

    public static int checkBoard(int[][] inlist, int clr) {
        int chkclr = 3 - clr;
        for (int i = 0; i < size; i++) {
            int colcnt = 0;
            for (int j = 0; j < size; j++) {
                if (inlist[i][j] == chkclr) {
                    colcnt++;
                    if (colcnt == size) {
                        return chkclr;
                    }
                } else {
                    colcnt = 0;
                }
            }
        }
        for (int i = 0; i < size; i++) {
            int colcnt = 0;
            for (int j = 0; j < size; j++) {
                if (inlist[j][i] == chkclr) {
                    colcnt++;
                    if (colcnt == size) {
                        return chkclr;
                    }
                } else {
                    colcnt = 0;
                }
            }
        }
        int colcnt = 0;
        for (int i = 0; i < size; i++) {
            if (inlist[i][i] == chkclr) {
                colcnt++;
                if (colcnt == size) {
                    return chkclr;
                }
            } else {
                colcnt = 0;
            }
        }
        colcnt = 0;
        for (int i = 0; i < size; i++) {
            if (inlist[size - 1 - i][i] == chkclr) {
                colcnt++;
                if (colcnt == size) {
                    return chkclr;
                }
            } else {
                colcnt = 0;
            }
        }
        if (isFull(inlist)) {
            return 0;
        } else {
            return 3;
        }

    }

    public static void printlist(int[][] inlist) {
        for (int i = 0; i < inlist.length; i++) {
            for (int j = 0; j < inlist.length; j++) {
                System.out.print(inlist[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}