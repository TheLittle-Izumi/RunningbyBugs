/***
 * 这是一个通过移动0来使得Puzzle排序的游戏
 * 但是如果你close掉assignread的 Scanner那么它就会炸
 * 现在大家来试试吧
 */
import java.util.*;

public class Puzzle
{
    private int currentboard[][]; 

    public Puzzle copy(){
        return new Puzzle(currentboard);
    }
    //return a new instance of a Puzzle with an identical board to the current instance.

    public void display(){
        for(int[] i : currentboard) {
            for(int j : i) System.out.printf("%4d",j);
            System.out.println();
        }
    }
    //display current the boards.

    public Puzzle(int[][] board){
        this.currentboard = board;
    }
    //board matching the supplied parameter.

    public void domove(char m){
        int [] something = this.FindzeroLocation();
        if(m == 'U')    something[0] -= 1;
        else if(m == 'D')   something[0] += 1;
        else if(m == 'L')   something[1] -= 1;
        else if(m == 'R')   something[1] += 1;
        this.swap(something, this.FindzeroLocation());
    }

    public void swap(int[] b, int[] a){
        int temp = currentboard[b[0]][b[1]];
        currentboard[b[0]][b[1]] = 0;
        currentboard[a[0]][a[1]] = temp;
    }

    public boolean vaildMove(char m){
        int[] temp = FindzeroLocation();
        if(m == 'U' && temp[0] - 1 < 0) return false;
        else if(m == 'D' && temp[0] + 1 > 3) return false;
        else if(m == 'L' && temp[1] - 1 < 0) return false;
        else if(m == 'R' && temp[1] + 1 > 3) return false;
        else return true;
    }

    public boolean solved(){
        for(int x = 0; x < currentboard.length; x++){
            for(int y = 0; y < currentboard[x].length - 1; y++){
                if(currentboard[x][y] + 1 != currentboard[x][y + 1]){
                    if(x != 3 || y != 2) return false;
                } 
            }
        }
        return true;
    }

    public boolean checkSolution(char[] moves){
        
        for(char i : moves) 
            {
                if (this.vaildMove(i)) this.domove(i);
                else {
                    System.out.println("Invaild");
                    this.display();
                    return false;
                }
            } //get each char in moves into vaildtest & put vaild one to move.
        return true;
    }

    public int[] FindzeroLocation(){
        int[] c = new int[2];
        for(int a = 0; a <currentboard.length; a++){
            for(int b = 0; b < currentboard[a].length; b++){
                if(currentboard[a][b] == 0) 
                {
                    c[0] = a;
                    c[1] = b;
                    return c;
                }
            }
        }
        return c;
    }
    //as the function called, it will return the location of 0 in current input[][], the 10th digit represent the row, the first digit rep the col of the 0. 

    public static void main(String[] args){
        System.out.println("Enter each row of integers as a separate line:");
        int input[][] = assigninput();
        System.out.println("Enter Your Moves:");
        String moves = readmoves();
        Puzzle test = new Puzzle(input);
        char[] charmove = new char[moves.length()];
        for(int a = 0; a < moves.length(); a++){
            charmove [a] = moves.charAt(a);
        }
        boolean Isvaild = test.checkSolution(charmove);
        if(Isvaild){
            if(test.solved()) System.out.println("Success");
            else System.out.println("Failure");
            test.display();
        }
    }

    public static int[][] assigninput(){
        Scanner sc = new Scanner(System.in);
        int input[][] = new int[4][4];
        for(int a = 0; a <input.length; a++){
            for(int b = 0; b < input[a].length; b++){
                input[a][b] = sc.nextInt();
            }
        }
        //不要在这里加sc.close()！！！
        //DO NOT ADD sc.close() HERE!!!
        return input;
    }
    //Read and assign user's board to a 2D array.

    public static String readmoves(){
        Scanner sc = new Scanner(System.in);
        String moves = sc.nextLine();
        sc.close();
        return moves;
    }
    //Read user's move.
}