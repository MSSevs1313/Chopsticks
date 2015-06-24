import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Matt on 6/11/2015.
 */
public class Node implements Comparable<Node>
{
    Player A;
    Player B;
    int uvA, uvB, turn, type, moveIn;
    ArrayList<Node> nextList = new ArrayList<Node>();
    ArrayList<Node> parentList  = new ArrayList<Node>();
    Node parent;
    boolean processed = false;
    String ID;

    /* Types:
    * 0 - winA
    * 1 - winB
    * -1 - root
    * */
    /* Moves:
    * 0 - Split
    * 1 - L to L
    * 2 - L to R
    * 3 - R to L
    * 4 - R to R
    * */

     public Node(Player A, Player B, int turn, int moveIn)
    {
        this.A = A;
        this.sortHands(A);
        this.B = B;
        this.sortHands(B);
        this.turn = turn;
        this.type = -1;
        this.uvA = this.uvB = 0;
        this.moveIn = moveIn;
        this.ID = generateID();
    }

    public void sortHands(Player player)
    {
        int temp;
        if(player.left > player.right)
        {
            temp = player.left;
            player.left = player.right;
            player.right = temp;
        }
    }

    @Override
    public int compareTo(Node o)
    {
        if (this.ID == o.ID)
            return 0;
        else
            return -1;
        /*if(this.turn < o.turn)
            return -1;
        else if (this.turn == o.turn)
            return 0;
        else
            return 1;*/

    }

    @Override
    public boolean equals(Object node)
    {
        if(node instanceof Node)
        {
            Node that = (Node) node;
            if (this.ID.equals(that.ID))
                return true;
            else
                return false;
        }
        else
            return false;
    }

    public String generateID()
    {
        return A.left +""+ A.right +""+ B.left +""+ B.right;
    }

    public String toString()
    {
        return A.left + " | " + A.right + "\n" +
                B.left + " | " + B.right;
    }

    public int gameOver()
    {
        if (this.A.left == 0 && this.A.right == 0)
            return 0;
        if(this.B.left == 0 && this.B.right == 0)
            return 1;
        else
            return -1;
    }
}
