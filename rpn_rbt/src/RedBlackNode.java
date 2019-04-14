import java.math.BigInteger;

/**
 * This class is to create a node for red balck tree
 * 
 * @author Anshu Anand
 *
 */
public class RedBlackNode {

	String key;
	BigInteger val;
	int color = RedBlackTree.BLACK;
	RedBlackNode left = RedBlackTree.nil;
	RedBlackNode right = RedBlackTree.nil;
	RedBlackNode parent = RedBlackTree.nil;

	/**
	 * This is a constructor for RedBlackNode
	 * 
	 * @Precondition:None
	 * @Postcondition:Node is created
	 * @param key
	 * @param val
	 * @param color
	 * @param l
	 * @param r
	 * @param p
	 */
	public RedBlackNode(String key, BigInteger val, int color, RedBlackNode l, RedBlackNode r, RedBlackNode p) {
		this.key = key;
		this.val = val;
		this.color = color;
		this.left = l;
		this.right = r;
		this.parent = p;
	}

	/**
	 * This is a constructor for RedBlackNode
	 * 
	 * @Precondition:None
	 * @Postcondition:Node is created
	 */
	RedBlackNode() {

		this.key = "NONE";
		this.val = BigInteger.ZERO;
		this.color = RedBlackTree.BLACK;

	}

	/**
	 * This method is called on node to returns color of the node
	 * 
	 * @Precondition:Node exist
	 * @Postcondition: return color
	 * @return
	 */
	public int getColor() {
		return color;
	}

	/**
	 * This method is called on node to get its left node
	 * 
	 * @Precondition: Node exists
	 * @Postcondition: Return left node
	 * @return
	 */
	public RedBlackNode getLc() {
		return left;
	}

	/**
	 * This method is called on node to get its left node
	 * 
	 * @Precondition: Node exists
	 * @Postcondition: return right node
	 * @return
	 */
	public RedBlackNode getRc() {
		return right;
	}

	/**
	 * This method returns parent of the called node
	 * 
	 * @Precondition: Node exists
	 * @Postcondition: return parent node
	 * @return
	 */
	public RedBlackNode geP() {
		return parent;
	}

	/**
	 * This method sets color on node
	 * 
	 * @Precondition: Node exists
	 * @Postcondition: set color of the node
	 * @param clr
	 */
	public void setColor(int clr) {
		this.color = clr;
	}

	/**
	 * This method sets data on the node
	 * 
	 * @Precondition:node exist
	 * @Postcondition:data is set on node
	 * @param k
	 * @param v
	 */
	public void setData(String k, BigInteger v) {
		this.key = k;
		this.val = v;

	}

	/**
	 * This method sets left node
	 * 
	 * @Precondition:node exists
	 * @Postcondition: left node set
	 * @param lc
	 */
	public void setLc(RedBlackNode lc) {
		this.left = lc;
	}

	/**
	 * This method sets right node
	 * 
	 * @Precondition:node exist
	 * @Postcondition:right node set
	 * @param rc
	 */
	public void setRc(RedBlackNode rc) {
		this.right = rc;

	}

	/**
	 * this method sets parent
	 * 
	 * @Precondition:node exist
	 * @Postcondition: parent is set
	 * @param p
	 */
	public void setP(RedBlackNode p) {
		this.parent = p;
	}
}
