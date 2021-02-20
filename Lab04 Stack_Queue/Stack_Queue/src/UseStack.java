
public class UseStack {

	// implement this method.
	public static Stack sort(Stack s) throws Exception {
		ArrayListStack answer = new ArrayListStack();
		while (!s.isEmpty()) {
			int sTop = s.top();
			if (answer.isEmpty() || sTop <= answer.top())
				answer.push(sTop);
			else {
				ArrayListStack tempStack = new ArrayListStack();
				while (!answer.isEmpty() && sTop > answer.top()) {
					tempStack.push(answer.top());
					answer.pop();
				}
				answer.push(sTop);
				while (!tempStack.isEmpty()) {
					answer.push(tempStack.top());
					tempStack.pop();
				}
			}
			s.pop();
		}
		return answer;
	}

}
