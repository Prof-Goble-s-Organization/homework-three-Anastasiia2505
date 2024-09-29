import java.util.ArrayList;

public class CS232ArrayStack <E>  extends CS232ArrayList<E> implements CS232Stack<E>{

    public CS232ArrayStack(){
        super();
    }


	@Override
	public void push(E element) {
		add(element);
	}

	@Override
	public E pop() {
		return remove(size()-1);
	}

	@Override
	public E peek() {
		return get(size()-1);
	}

	@Override
	public int size() {
		return super.size();
	}

    
   public static void main(String[] args){
    CS232ArrayStack<Integer> stack = new CS232ArrayStack<>();
    stack.push(4);
    stack.push(8);
    stack.push(14);
    System.out.println(stack.get(2));
    System.out.println(stack.pop());
    stack.peek();
    System.out.println(stack.get(1));
    System.out.println(stack.size());
   }

    
}
