package trees;
// TreeNode: simple node in a binary search tree
class TreeNode {
   public String data;  // data at each node
   public TreeNode left, right;
   public TreeNode(String d) { // construct leaf node
      data = d;
      left = right = null;
   }
}

// Tree: simplest possible binary search tree
class Tree {
   private TreeNode root; // hidden root node

   // insert: if new entry, insert in tree
   public void insert(String d) {
      if (root == null) { // must handle case of empty tree first
         root = new TreeNode(d);
         return;
      }
      TreeNode loc = root; // start search downward at root
      while (true) {
         if (d.compareTo(loc.data) < 0) { // look left
            if (loc.left != null) loc = loc.left;
            else { loc.left = new TreeNode(d); break; }
         }
         else if (d.compareTo(loc.data) > 0) { // look right
            if (loc.right != null) loc = loc.right;
            else { loc.right = new TreeNode(d); break; }
         }
         else break; // found! Don't insert
      }
   }

   // inorderTraversal: need because root is hidden
   public void inorderTraversal() {inorderT(root); }

   // inorderT: recursive function that does the work
   private void inorderT(TreeNode t) {
      if (t != null) {
         inorderT(t.left);
         System.out.print(t.data + " ");
         inorderT(t.right);
      }
   }
}

// TreeTest: test the Tree class, a binary search tree
public class TreeTest {
   public static void main(String[] argv) {
      String[] months = {"Jan","Feb","Mar","Apr","May","Jun",
                         "Jul","Aug","Sep","Oct","Nov","Dec"};
      Tree tree = new Tree();
      for (int i = 0; i < months.length; i++)
         tree.insert(months[i]);
      tree.inorderTraversal();
      System.out.println();
   }
}