
public class StudentList extends CDLinkedList {
	// you can write additional methods.

	// implement this method
	public void swapNode(DListIterator i1, DListIterator i2) throws Exception {
		DListIterator nextI1 = new DListIterator(i1.currentNode.nextNode);
		DListIterator nextI2 = new DListIterator(i2.currentNode.nextNode);
		DListIterator previousI1 = (DListIterator) findPrevious((Iterator) i1);
		DListIterator previousI2 = (DListIterator) findPrevious((Iterator) i2);
		previousI1.currentNode.nextNode = i2.currentNode;
		i2.currentNode.previousNode = previousI1.currentNode;
		i2.currentNode.nextNode = nextI1.currentNode;
		previousI2.currentNode.nextNode = i1.currentNode;
		i1.currentNode.previousNode = previousI2.currentNode;
		i1.currentNode.nextNode = nextI2.currentNode;
		nextI2.currentNode.previousNode = i1.currentNode;
		nextI1.currentNode.previousNode = i2.currentNode;

	}

	// implement this method
	public void insertList(DListIterator i1, CDLinkedList lst) throws Exception {
		if (lst.isEmpty())
			return;
		DListIterator itr1 = new DListIterator(lst.header);
		itr1.next(); // First Position
		DListIterator itr2 = new DListIterator(lst.header); // Last Position
		while (itr2.hasNext() && itr2.currentNode.nextNode != lst.header)
			itr2.next();
		DListIterator nextI1 = new DListIterator(i1.currentNode.nextNode);
		itr2.currentNode.nextNode = nextI1.currentNode;
		nextI1.currentNode.previousNode = itr2.currentNode;
		itr1.currentNode.previousNode = i1.currentNode;
		i1.currentNode.nextNode = itr1.currentNode;

	}

	// implement this method
	public void gender(String g) throws Exception {
		Iterator itr = new DListIterator(header);
		DListIterator insertItr = new DListIterator(header);
		while (itr.hasNext()) {
			Object data = itr.next();
			Student std = (Student) data;
			DListIterator itr2 = (DListIterator) itr;
			if (itr2.currentNode == header)
				return;
			if (std.getSex().equals(g)) {
				insert((Object) std, insertItr);
				removeAt(itr);
				insertItr.next();
			}
		}
	}

}
