/**
 * Created by Matt on 6/2/2015.
 */
public class Game
{
    Player player1, player2;
    public Game(int type)
    {
        if (type == 1)
        {
            this.player1 = new HumanPlayer();
            this.player2 = new HumanPlayer();
        }
        else
        {
            this.player1 = new HumanPlayer();
            this.player2 = new CpuPlayer();
        }
    }

    public void play()
    {
        printState();
        if (!gameOver())
        {
            if(player1.left == 0 || player1.right == 0)
            {
                player1.split();
                printState();
            }
            makeMove(player1, player2);
            printState();
        }
        System.out.println();
        if (!gameOver())
        {
            if(player2.left == 0 || player2.right == 0)
            {
                player2.split();
                printState();
            }
            makeMove(player2, player1);
        }
        System.out.println();
    }

    /*
   * 1 = L to L
   * 2 = L to R
   * 3 = R to L
   * 4 = R to R
   * */
    public void makeMove(Player attacker, Player target)
    {
       int move = attacker.selectMove();
       switch (move)
       {
           case 1:
               System.out.println("L to L");
               if(attacker.left != 0 && target.left != 0)
                   target.left = (attacker.left + target.left)%5;
               else
               {
                   System.out.println("Try again, not a legal move.");
                   makeMove(attacker, target);
               }
               break;
           case 2:
               System.out.println("L to R");
               if(attacker.left != 0 && target.right != 0)
                   target.right = (attacker.left + target.right)%5;
               else
               {
                   System.out.println("Try again, not a legal move.");
                   makeMove(attacker, target);
               }
               break;
           case 3:
               System.out.println("R to L");
               if( attacker.right != 0 && target.left != 0)
                   target.left = (attacker.right + target.left)%5;
               else
               {
                   System.out.println("Try again, not a legal move.");
                   makeMove(attacker, target);
               }
               break;
           case 4:
               System.out.println("R to R");
               if(attacker.right != 0 && target.right != 0)
                   target.right = (attacker.right + target.right)%5;
               else
               {
                   System.out.println("Try again, not a legal move.");
                   makeMove(attacker, target);
               }
              break;
       }
    }

    public boolean gameOver()
    {
        if(player1.left == 0 && player1.right == 0)
        {
            System.out.println("Player 2 wins!");
            return true;
        }
        if(player2.left == 0 && player2.right == 0)
        {
            System.out.println("Player 1 wins!");
            return true;
        }
        return false;
    }

    public void printState()
    {
        System.out.println("Player 1: " + player1.toString());
        System.out.println("Player 2: " + player2.toString());
    }
}
