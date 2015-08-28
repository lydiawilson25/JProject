package stack;
/**
 * Stack which, in addition to push and pop, 
 * also has a function min which returns the minimum element.
 * 
 * push(), pop() and min() all operate in O(1) time
 * 
 * @author while - mydevelopedworld.wordpress.com
 *
 */
public class StackMin {

	private Element top;
	private MinElement currentMin;

	public void push(Element item){
		/* Empty Stack = item is the new minimum */
		if(this.currentMin == null){ 			
			this.currentMin = new MinElement(item.getValue(), null); /* lastMin = null */ 
			this.top = this.currentMin;
			return;
		}
		/* Found a new minimum */
		if(currentMin.getValue() > item.getValue()){ /* Item pushed < currentMin */
			MinElement newMin = new MinElement(item.getValue(), this.currentMin);
			newMin.setBelow(this.top);
			this.currentMin = newMin;	
			this.top = this.currentMin;
			return;
		}
		/* Normal push case */
		item.setBelow(this.top);
		this.top = item; 
	}

	public Element pop(){
		/* Empty Stack */
		if(this.currentMin == null){ 
			return null;
		}
		
		Element ret = this.top;	
		
		/* Current minimum on the top */
		if(this.top == this.currentMin){ /* Pop of the currentMin */
			this.currentMin = this.currentMin.getLastMin(); /* currentMin = lastMin */
			this.top = this.top.getBelow();
			return ret;
		}					
		/* Normal pop case */
		this.top = ret.getBelow();
		return ret;					
	}

	public Element min(){
		return currentMin; /* Return null if Empty Stack */
	}

	public static void main(String args[]){
		StackMin stack = new StackMin();		

		stack.push(new Element(10));
		stack.push(new Element(100));
		stack.push(new Element(5));
		stack.push(new Element(17));
		stack.push(new Element(20));
		
		System.out.println(stack.min()); // >> 5

		stack.push(new Element(1));

		System.out.println(stack.min()); // >> 1
		System.out.println(stack.pop()); // >> 1
		System.out.println(stack.pop()); // >> 20
		System.out.println(stack.pop()); // >> 17
		System.out.println(stack.min()); // >> 5
		System.out.println(stack.pop()); // >> 5
		System.out.println(stack.min()); // >> 10
		System.out.println(stack.pop()); // >> 100
		System.out.println(stack.min()); // >> 10
		System.out.println(stack.pop()); // >> 10
		System.out.println(stack.pop()); // >> null
		System.out.println(stack.min()); // >> null
		
	}



}

class Element{
	private int value;
	private Element below = null;

	public Element(int value){
		this.value = value;
	}

	public int getValue(){ return this.value; }
	public Element getBelow(){ return this.below; }

	public void setBelow(Element e){
		this.below = e;
	}

	public String toString(){
		return this.value+"";
	}
}

class MinElement extends Element{
	private MinElement lastMin;

	public MinElement(int value, MinElement lastMin){
		super(value);
		this.lastMin = lastMin;
	}

	public MinElement getLastMin(){ return this.lastMin; }
}