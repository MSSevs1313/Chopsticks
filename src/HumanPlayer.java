import java.util.Scanner;

/**
 * Created by Matt on 6/2/2015.
 */
public class HumanPlayer extends Player
{
//    int right, left;
    public HumanPlayer()
    {
        super();
//        this.left = this.right = 1;
    }

    /*
    * 1 = L to L
    * 2 = L to R
    * 3 = R to L
    * 4 = R to R
    * */
    public int selectMove()
    {
        String own, target;
        Scanner scan = new Scanner(System.in);
        
        do
        {
            System.out.println("Select a hand to move with");
            own = scan.nextLine();
            own.toLowerCase();
        }while(!own.startsWith("l") && !own.startsWith("r"));

        do
        {
            System.out.println("Select a hand to move against");
            target = scan.nextLine();
            target.toLowerCase();
        }while(!own.startsWith("l") && !own.startsWith("r"));

        if(own.startsWith("l"))
        {
            if(target.startsWith("l"))
                return 1;
            else if(target.startsWith("r"))
                return 2;
        }
        else if(own.startsWith("r"))
        {
            if(target.startsWith("l"))
                return 3;
            else if(target.startsWith("r"))
                return 4;
        }
        return 0;
    }

    public void split()
    {
        String answer;
        Scanner scan = new Scanner(System.in);

        if(this.left == 0 && this.right%2 == 0)
        {
            do
            {
                System.out.println("Would you like to split?");
                answer = scan.nextLine();
                answer.toLowerCase();
            }while(!answer.startsWith("y") && !answer.startsWith("n"));

            if (answer.startsWith("y"))
            {
                this.right /= 2;
                this.left = this.right;
            }
        }
        else if(this.right == 0 && this.left%2 == 0)
        {
            do
            {
                System.out.println("Would you like to split?");
                answer = scan.nextLine();
                answer.toLowerCase();
            }while(!answer.startsWith("y") && !answer.startsWith("n"));

            if (answer.startsWith("y"))
            {
                this.left /= 2;
                this.right = this.left;
            }
        }
    }

    public String toString()
    {
        return this.left + " | " + this.right;
    }
}
