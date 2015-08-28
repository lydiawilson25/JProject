package stack;

public class stack1 {
	plate top;
	
	int pop(){
		if(top!=null){
		plate current = top;
		top=top.getnext();
		return current.getData();
		
		}
		return 0;
	}
	
	void push(int data){
		plate newplate = new plate(data);
		newplate.setnext(top);
		top=newplate;
	}
	
	int peek(){
		return top.getData();
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		stack1 s1 = new stack1();
		s1.push(3);
		s1.push(7);
		s1.push(9);
		System.out.println(s1.pop());
		System.out.println(s1.peek());
		System.out.println(s1.pop());
		System.out.println(s1.pop());

	}

}

class plate {
	int data;
	plate next=null;

	public plate(int data) {
		this.data = data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public void setnext(plate newplate) {
		this.next = newplate;
	}

	public int getData() {
		return this.data;
	}

	public plate getnext() {
		return this.next;
	}
}
