import java.util.Scanner;

/**
 * Created by Matt on 6/2/2015.
 */
public class main
{
    public static void main(String[]args)
    {
        System.out.println("Choose a game type:");
        System.out.println("1: Player vs Player");
        System.out.println("2: Computer vs Player");
        Scanner scan = new Scanner(System.in);
        Game game = new Game(scan.nextInt());
        while(!game.gameOver())
        {
            game.play();
        }
        System.out.println("Would you like to play another game?");
        String ans = scan.next();
        if(ans.startsWith("y") || ans.startsWith("Y"))
            main(args);
    }
}
