import java.math.BigInteger;



/**
 * This class is for Red Balck tree
 * @author Anshu Anand
 *
 */
public class RedBlackTree {
	//Test keys
	private static String[] testKeys = new String[] { "NY", "AZ", "TX", "CA", "NE","PA","FL","OH","UT",
			                                           "KN" ,"NC","CO","DE","MA","HW"};
	//Test values
	private static BigInteger[] testVals = new BigInteger[] { new BigInteger("1000000000"), new BigInteger("20000000000"),
			                               new BigInteger("300000000000"), new BigInteger("40000000000"), new BigInteger("5000000000"),
			                               new BigInteger("600000000000"), new BigInteger("700000000000"), new BigInteger("800000000000"),
			                               new BigInteger("900000000000"), new BigInteger("1000000000000"), new BigInteger("1100000000000"),
			                               new BigInteger("1200000000000"), new BigInteger("1300000000000"), new BigInteger("1400000000000"),new BigInteger("1500000000000")};
	
	public static final int RED = 0;
	public static final int BLACK = 1;
    //Nil node
	public static final RedBlackNode nil = new RedBlackNode();
	private RedBlackNode root = nil;

	/**
	 * This is constructor for red balck tree
	 */
	public RedBlackTree() {
		this.root.left = this.nil;
		this.root.right = this.nil;
		this.root.parent = this.nil;
	}

	/**
	 * This method inserts node in RedBlack tree
	 * @Precondition: RedBlack tree exists
	 * @Postcondition:new node  is inserted in tree
	 * @param tNode
	 */
	public void insert(RedBlackNode tNode) {
		RedBlackNode temp = root;
		if (root == nil) {
			root = tNode;
			tNode.color = BLACK;
			tNode.parent = nil;
		} else {
			tNode.color = RED;
			while (true) {
				if (tNode.key.compareTo(temp.key) < 0) {
					if (temp.left == nil) {
						temp.left = tNode;
						tNode.parent = temp;
						break;
					} else {
						temp = temp.left;
					}
				} else if (tNode.key.compareTo(temp.key) > 0) {
					if (temp.right == nil) {
						temp.right = tNode;
						tNode.parent = temp;
						break;
					} else {
						temp = temp.right;
					}
				}
				else if(tNode.key.compareTo(temp.key) == 0) {
					temp.val = tNode.val;
					break;
				}
			}
			RBInsertFixup(tNode);
		}
	}

	/**
	 * This method rotates the left left
	 * @Precondition: RedBlack tree exists
	 * @Postcondition:nodes are left rotated
	 * @param x
	 */
	public void leftRotate(RedBlackNode x) {
		if (x.parent != nil) {
			if (x == x.parent.left) {
				x.parent.left = x.right;
			} else {
				x.parent.right = x.right;
			}
			x.right.parent = x.parent;
			x.parent = x.right;
			if (x.right.left != nil) {
				x.right.left.parent = x;
			}
			x.right = x.right.left;
			x.parent.left = x;
		} else {// rotate root
			RedBlackNode right = root.right;
			root.right = right.left;
			right.left.parent = root;
			root.parent = right;
			right.left = root;
			right.parent = nil;
			root = right;
		}
	}

	/**
	 * This method rotates the nodes to right
	 * @Precondition:RedBlack tree exists
	 * @Postcondition:nodes are rotated to right
	 * @param x
	 */
	public void rightRotate(RedBlackNode x) {
		if (x.parent != nil) {
			if (x == x.parent.left) {
				x.parent.left = x.left;
			} else {
				x.parent.right = x.left;
			}

			x.left.parent = x.parent;
			x.parent = x.left;
			if (x.left.right != nil) {
				x.left.right.parent = x;
			}
			x.left = x.left.right;
			x.parent.right = x;
		} else {// rotate root
			RedBlackNode left = root.left;
			root.left = root.left.right;
			left.right.parent = root;
			root.parent = left;
			left.right = root;
			left.parent = nil;
			root = left;
		}
	}

	/**
	 * This method fixes the tree after new insert
	 * @Precondition:RedBlack tree exists
	 * @Postcondition: tree is adjusted to satisfy all the property of redBlack tree
	 * @param z
	 */
	public void RBInsertFixup(RedBlackNode z) {
		while (z.parent.color == RED) {
			RedBlackNode uncle = nil;
			if (z.parent == z.parent.parent.left) {
				uncle = z.parent.parent.right;

				if (uncle != nil && uncle.color == RED) {
					z.parent.color = BLACK;
					uncle.color = BLACK;
					z.parent.parent.color = RED;
					z = z.parent.parent;
					continue;
				}
				if (z == z.parent.right) {
					// Double rotation
					z = z.parent;
					leftRotate(z);
				}
				z.parent.color = BLACK;
				z.parent.parent.color = RED;
				
				rightRotate(z.parent.parent);
			} else {
				uncle = z.parent.parent.left;
				if (uncle != nil && uncle.color == RED) {
					z.parent.color = BLACK;
					uncle.color = BLACK;
					z.parent.parent.color = RED;
					z = z.parent.parent;
					continue;
				}
				if (z == z.parent.left) {
					// Double rotation 
					z = z.parent;
					rightRotate(z);
				}
				z.parent.color = BLACK;
				z.parent.parent.color = RED;
				
				leftRotate(z.parent.parent);
			}
		}
		root.color = BLACK;
	}

	/**
	 * @Precondition:Tree exists
	 * @Postcondition: Tree is printed
	 * @param n
	 */
	public void printTree(RedBlackNode n) {
		if (n == nil) {
			return;
		}
		printTree(n.left);
		System.out.print(((n.color == RED) ? "Color: Red " : "Color: Black ") + "Key: " + n.key + " Parent: "
				+ n.parent.key + "\n");
		printTree(n.right);

	}
	
	/**
	 * This method looksup key in the node
	 * @Precondition:RedBlack tree is existing
	 * @Postcondition: lookup is returned for search key
	 * @param findKey
	 * @param node
	 * @return
	 */
	public RedBlackNode lookUp(String findKey, RedBlackNode node) {
        if (root == nil) {
            return null;
        }

        if (findKey.compareTo(node.key) <0 ) {
            if (node.left != nil) {
                return lookUp(findKey, node.left);
            }
        } else if (findKey.compareTo(node.key) >0) {
            if (node.right != nil) {
                return lookUp(findKey, node.right);
            }
        } else if (findKey.compareTo(node.key) ==0) {
            return node;
        }
        return null;
    }
	
	/**
	 * This method prints tree in Inorder traversal
	 * @Precondition:RedBlack tree exists
	 * @Postcondition: Inrder traversal is printed
	 * @param node
	 */
	public static void traverseInorder(RedBlackNode node) {
		if (node == nil)
			return;
		// traverse the left node first
		traverseInorder(node.left);
		// root
		System.out.print(node.key + "\n");
		// traverse the right node first
		traverseInorder(node.right);
	}
	
	/**
	 * This method prints tree in Pre-order traversal
	 * @Precondition:RedBlack tree exists
	 * @Postcondition: Pre-order traversal is printed
	 * @param node
	 */
	public static void traversePreorder(RedBlackNode node) {
		if (node == nil)
			return;

		// traverse the left node first
		System.out.print(node.key + "\n");
		// traverse the left node first
		traversePreorder(node.left);
		// traverse the right node first
		traversePreorder(node.right);
	}

	/**
	 * This method returns root of tree
	 * @Precondition:RedBlack tree exists
	 * @Postcondition:root is returned
	 * @return
	 */
	public RedBlackNode getRoot() {
		return root;
	}

	/**
	 * This is main method
	 * @Precondition:RedBlack tree exists
	 * @Postcondition:Main subroutine will insert 15 nodes and will traverses them
	 * @param args
	 */
	public static void main(String[] args) {
		RedBlackTree rbt = new RedBlackTree();

		for (int i = 0; i < testKeys.length; i++) {
			RedBlackNode n = new RedBlackNode(testKeys[i], testVals[i], 1, nil, nil, nil);
			rbt.insert(n);
		}
		rbt.printTree(rbt.getRoot());
		System.out.println("Find the key \"HW\" in RedBlack tree");
		RedBlackNode f= rbt.lookUp("HW",rbt.getRoot());
		System.out.println("Node found , Value is :"+ f.val);
		System.out.println("In Order Traversal");
		traverseInorder(rbt.getRoot());
		System.out.println("Pre Order Traversal");
		traversePreorder(rbt.getRoot());
		
	}
}
