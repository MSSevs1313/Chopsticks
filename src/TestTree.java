/**
 * Created by Matt on 6/12/2015.
 */
public class TestTree
{
    public static void main(String[]args)
    {
        Node node = new Node(new Player(), new Player(), 1 , -1);
        node.processed = true;
        node.type = 3;
        Tree tree = new Tree(node);
        tree.popTree(node);
        tree.findUtility();
        simulate(node);
       /* int temp = 13;
        for(Node test: tree.tree)
        {
            if(test.uvB == temp - 1)
            {
                temp--;
                System.out.println(test);
                System.out.println(test.uvB);
            }
           // System.out.println("A: " + test.uvA + " B: " + test.uvB);
        }*/
    }

    public static void simulate(Node node)
    {
        if(node.gameOver() !=  -1)
            return;

        Node temp;
        if(!node.nextList.isEmpty())
            temp = node.nextList.remove(0);
        else
            return;

        for(Node move: node.nextList)
        {
            if(node.turn%2 == 1)
            {
                if (move.uvA > temp.uvA)
                    temp = move;
            }
            else if(node.turn%2 == 0)
            {
                if (move.uvB > temp.uvB)
                    temp = move;
            }
        }
        System.out.println(temp + "\n");
        simulate(temp);
    }
}
