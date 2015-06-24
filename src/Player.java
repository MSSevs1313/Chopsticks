/**
 * Created by Matt on 6/2/2015.
 */
public class Player
{
    int left, right;

    public Player()
    {
        this.left = this.right = 1;
    }

    public int selectMove(){return 0;};

    public void split()
    {
        if(this.right%2 == 0 && this.left == 0)
        {
            this.right /= 2;
            this.left = right;
        }
        else if (this.left%2 == 0 && this.right == 0)
        {
            this.left /= 2;
            this.right = left;
        }
    }

    public String toString()
    {
        return this.left + " | " + this.right;
    }
}
