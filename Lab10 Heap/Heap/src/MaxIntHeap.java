
public class MaxIntHeap extends Heap {
	public void add(Object element) {
		size++;
		if (size == mData.length) {
			Object[] newHeap = new Object[mData.length * 2];
			System.arraycopy(mData, 0, newHeap, 0, size);
			mData = newHeap;
		}
		mData[size - 1] = element;
		int parentIdx, childIdx = size - 1;
		Comparable temp;
		while (childIdx > 0) {
			parentIdx = (childIdx - 1) / 2;
			if (((Comparable) mData[parentIdx]).compareTo(mData[childIdx]) >= 0)
				break;
			temp = (Comparable) mData[parentIdx];
			mData[parentIdx] = mData[childIdx];
			mData[childIdx] = temp;
			childIdx = parentIdx;
		}
	}

	public Object pop() throws Exception {
		if (size == 0)
			throw new Exception("Priority queue empty.");
		Object max = mData[0];
		mData[0] = mData[size - 1];
		size--;
		int parentIdx = 0, childIdx = 1;
		Object temp;
		while (childIdx < size) {
			if (childIdx < size - 1 && ((Comparable) mData[childIdx]).compareTo(mData[childIdx + 1]) < 0)
				childIdx++;
			if (((Comparable) mData[parentIdx]).compareTo(mData[childIdx]) >= 0)
				break;
			temp = mData[childIdx];
			mData[childIdx] = mData[parentIdx];
			mData[parentIdx] = temp;
			parentIdx = childIdx;
			childIdx = parentIdx * 2 + 1;
		}
		return max;
	}
}
