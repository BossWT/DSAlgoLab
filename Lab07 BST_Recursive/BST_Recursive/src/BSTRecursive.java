import java.util.Arrays;

public class BSTRecursive {

	BSTNode root;
	int size;

	public BSTRecursive(BSTNode root, int size) {
		this.root = root;
		this.size = size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void makeEmpty() {
		root = null;
		size = 0;
	}

	public Iterator findMin() {
		return findMin(root);
	}

	public Iterator findMin(BSTNode n) {
		if (n == null)
			return null;
		if (n.left == null) {
			Iterator itr = new TreeIterator(n);
			return itr;
		}
		return findMin(n.left);
	}

	public Iterator findMax() {
		return findMax(root);
	}

	public Iterator findMax(BSTNode n) {
		if (n == null)
			return null;
		if (n.right == null) {
			Iterator itr = new TreeIterator(n);
			return itr;
		}
		return findMax(n.right);
	}

	public Iterator find(int v) {
		return find(v, root);
	}

	public Iterator find(int v, BSTNode n) {
		if (n == null)
			return null;
		if (v == n.data)
			return new TreeIterator(n);
		if (v < n.data)
			return find(v, n.left);
		else
			return find(v, n.right);
	}

	public BSTNode insert(int v) {
		return insert(v, root, null);
	}

	// return the node n after v was added into the tree
	public BSTNode insert(int v, BSTNode n, BSTNode parent) {
		if (n == null) {
			n = new BSTNode(v, null, null, parent);
			size++;
		} else if (v < n.data) {
			n.left = insert(v, n.left, n);
		} else if (v > n.data) {
			n.right = insert(v, n.right, n);
		}
		return n;
	}

	public BSTNode remove(int v) {
		return remove(v, root, null);
	}

	// return the node n after v was removed from the tree
	public BSTNode remove(int v, BSTNode n, BSTNode parent) {
		if (n == null)
			; // do nothing, there is nothing to be removed
		else if (v < n.data) {
			n.left = remove(v, n.left, n);
		} else if (v > n.data) {
			n.right = remove(v, n.right, n);
		} else {
			if (n.left == null && n.right == null) {
				n.parent = null; // disconnect from above
				n = null; // disconnect from below
				size--;
			} else if (n.left != null && n.right == null) {
				BSTNode n2 = n.left;
				n2.parent = parent;
				n.parent = null; // disconnect from above
				n.left = null; // disconnect from below
				n = n2; // change to the node below
						// to prepare for connection from parent
				size--;
			} else if (n.right != null && n.left == null) {
				BSTNode n2 = n.right;
				n2.parent = parent;
				n.parent = null; // disconnect from above
				n.right = null; // disconnect from below
				n = n2; // change to the node below
						// to prepare for connection from parent
				size--;
			} else {
				TreeIterator i = (TreeIterator) findMin(n.right);
				int minInRightSubtree = i.currentNode.data;
				n.data = minInRightSubtree;
				n.right = remove(minInRightSubtree, n.right, n);
			}
		}
		return n;
	}

	private int height(BSTNode n) {
		if (n == null)
			return -1;
		return 1 + Math.max(height(n.left), height(n.right));
	}

	/**************************************************************************************************/
	// Edit only the following methods.
	/**************************************************************************************************/

	/**
	 * Internal method to find the number of nodes in a given subtree using a
	 * recursive method.
	 * 
	 * @param n the root of a given subtree
	 * @param v the threshold
	 * @return the number of nodes in which the data is less than v in the subtree
	 */
	int numnodes = 0;

	public int numNodesLessThan(BSTNode n, int v) {
		int res = 0;
		if (n == null)
			return 0;
		else if (n.data < v) {
			res++;
			res += numNodesLessThan(n.left, v);
			res += numNodesLessThan(n.right, v);
		} else
			res += numNodesLessThan(n.left, v);
		return res;
	}

	/**
	 * Internal method to count the number of leaves in a given subtree
	 * (recursively).
	 * 
	 * @param n root node of a given subtree
	 * @param v the threshold
	 * @return number of leaves in which the data is less than v in the subtree,
	 */
	public int numLeavesLessThan(BSTNode n, int v) {
		if (n == null)
			return 0;
		if (n.data < v && n.left == null && n.right == null)
			return 1;
		else
			return numLeavesLessThan(n.left, v) + numLeavesLessThan(n.right, v);
	}

	/**
	 * In order insert all nodes from a BST that has n as the root node.
	 * 
	 * @param n the node that roots the subtree.
	 * 
	 */
	public void insertInOrder(BSTNode n) {
		if (n == null)
			return;
		insertInOrder(n.left);
		insert(n.data);
		insertInOrder(n.right);
	}

	/**
	 * Find the next data of v in the subtree which has n as the root node
	 * 
	 * @param n root node of a given subtree
	 * @param v the value v
	 * @return next data of v in the given subtree
	 */
	BSTNode res = new BSTNode(0);

	public int nextOf(BSTNode n, int v) {
		TreeIterator maxItr = (TreeIterator) findMax();
		int max = maxItr.currentNode.data;
		if (v >= max)
			return v;
		if (n.data == v) {
			if (n.right != null) {
				TreeIterator tItr = (TreeIterator) findMin(n.right);
				return tItr.currentNode.data;
			}
		} else if (n.data > v) {
			res = n;
			if (n.left != null)
				return nextOf(n.left, v);
		} else {
			if (n.right != null)
				return nextOf(n.right, v);
		}
		return res.data;
	}

}
