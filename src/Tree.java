import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Matt on 6/11/2015.
 */
public class Tree
{
    ArrayList<Node> winList = new ArrayList<Node>();
    ArrayList<Node> tree;
    int turn;
    public Tree(Node node)
    {
        this.turn = 1;
        this.tree = new ArrayList<Node>();
        this.tree.add(node);
    }

    /* Types:
   * 0 - root
   * 1 - winA
   * 2 - winB
   * */
    /* Moves:
    * 0 - Split
    * 1 - L to L
    * 2 - L to R
    * 3 - R to L
    * 4 - R to R
    * */
    public void popTree(Node root)
    {
        if(root.ID == "0224")
        {
            System.out.println();
        }
        if (turn % 2 == 1) //A moving
        {
            if ((root.A.left % 2 == 0 && root.A.right == 0) ||
                    (root.A.right % 2 == 0 && root.A.left == 0))
            {
                Player temp = tempPlayer(root.A);
                Node newNode = new Node(split(temp), root.B, turn, 0);
                newNode.parentList.add(root);
                addTo(root, newNode);
            }

            for (int i = 1; i <= 4; i++)
            {
                Player temp = tempPlayer(root.B);
                if(!legalMove(root.A, temp, i))
                    continue;
                Node newNode = new Node(root.A, makeMove(root.A, temp, i), turn + 1, i);
                newNode.parentList.add(root);
                newNode.type = gameOver(newNode.A, newNode.B);
                if (newNode.type != -1)
                    winList.add(newNode);
                addTo(root, newNode);
            }
        }

        else if (turn % 2 == 0) //B moving
        {
            if ((root.B.left % 2 == 0 && root.B.right == 0) ||
                    (root.B.right % 2 == 0 && root.B.left == 0))
            {
                Player temp = tempPlayer(root.B);
                Node newNode = new Node(root.A, split(temp), turn, 0);
                newNode.parentList.add(root);
                addTo(root, newNode);
            }

            for (int i = 1; i <= 4; i++)
            {
                Player temp = tempPlayer(root.A);
                if(!legalMove(root.B, temp, i))
                    continue;
                Node newNode = new Node(makeMove(root.B, temp, i), root.B, turn + 1, i);
                newNode.parentList.add(root);
                newNode.type = gameOver(newNode.A, newNode.B);
                if (newNode.type != -1)
                    winList.add(newNode);
                addTo(root, newNode);
            }
        }
        turn++;
        treeLoop(root);
    }

    public void findUtility()
    {
        for (Node node: winList)
        {
            node.uvA++;
            node.uvB++;
            walkUp(node);
        }
    }

    public void walkUp(Node node)
    {
        if(node.parentList.isEmpty())
            return;
        for(Node parent: node.parentList)
        {
            if (parent.turn % 2 != 0)
                parent.uvA++;
            else
                parent.uvB++;
            walkUp(parent);
        }
        if (node.type == 3)
            return;
    }

    public Player tempPlayer(Player copied)
    {
        Player temp = new Player();
        temp.left = copied.left;
        temp.right = copied.right;
        return temp;
    }

    public void addTo(Node root, Node newNode)
    {
        if (!root.nextList.contains(newNode)  && !tree.contains(newNode))
        {
            root.nextList.add(newNode);
            tree.add(newNode);
        }
    }
    
    public void treeLoop(Node root)
    {
        for (Node treeNode : root.nextList)
        {
            if(treeNode.processed)
                return;
            //System.out.println(treeNode.turn + ": " + treeNode.toString());
            if(!treeNode.processed)
            {
                treeNode.processed = true;
                popTree(treeNode);
            }

        }
        return;
    }

    public boolean legalMove(Player attacker, Player target, int move)
    {
        switch (move)
        {
            case 1:
                //System.out.println("L to L");
                if(attacker.left == 0 || target.left == 0)
                    return false;
            case 2:
                //System.out.println("L to R");
                if(attacker.left == 0 || target.right == 0)
                    return false;
            case 3:
                //System.out.println("R to L");
                if( attacker.right == 0 || target.left == 0)
                    return false;
            case 4:
                // System.out.println("R to R");
                if(attacker.right == 0 || target.right == 0)
                    return false;
        }
        return true;
    }

    public Player makeMove(Player attacker, Player target, int move)
    {
        switch (move)
        {
            case 1:
                //System.out.println("L to L");
                if(attacker.left != 0 && target.left != 0)
                    target.left = (attacker.left + target.left)%5;
                break;
            case 2:
                //System.out.println("L to R");
                if(attacker.left != 0 && target.right != 0)
                    target.right = (attacker.left + target.right)%5;
                break;
            case 3:
                //System.out.println("R to L");
                if( attacker.right != 0 && target.left != 0)
                    target.left = (attacker.right + target.left)%5;
                break;
            case 4:
               // System.out.println("R to R");
                if(attacker.right != 0 && target.right != 0)
                    target.right = (attacker.right + target.right)%5;
                break;
        }
        return target;
    }


    public Player split(Player splitter)
    {
        if(splitter.right%2 == 0 && splitter.left == 0)
        {
            splitter.right /= 2;
            splitter.left = splitter.right;
        }
        else if (splitter.left%2 == 0 && splitter.right == 0)
        {
            splitter.left /= 2;
            splitter.right = splitter.left;
        }
        return splitter;
    }

    public int gameOver(Player A, Player B)
    {
        if (A.left == 0 && A.right == 0)
            return 0;
        if(B.left == 0 && B.right == 0)
            return 1;
        else
            return -1;
    }
}
