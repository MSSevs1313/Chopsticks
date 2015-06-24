import java.util.Random;

/**
 * Created by Matt on 6/2/2015.
 */
public class CpuPlayer extends Player
{
    //int left, right;

    public CpuPlayer()
    {
        super();
    }

    /*
   * 1 = L to L
   * 2 = L to R
   * 3 = R to L
   * 4 = R to R
   */
    public int selectMove()
    {
        System.out.println("Computer moving... ");
        Random rand = new Random();
        return rand.nextInt(4) + 1;
    }

    public void split()
    {
        if(this.left == 0 && this.right%2 == 0)
        {
                System.out.println("Computer splitting");
                this.right /= 2;
                this.left = this.right;
        }
        else if(this.right == 0 && this.left%2 == 0)
        {
                System.out.println("Computer splitting");
                this.left /= 2;
                this.right = this.left;
        }
    }

    public String toString()
    {
        return this.left + " | " + this.right;
    }
}
