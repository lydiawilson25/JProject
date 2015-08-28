package solved;



public class Trie {
	private Node head;

	public Trie() {
		head = new Node('#');  // Root indicated by Hash
	}

	// Function to insert the word in a Trie
	public void insert(String s) {
		Node current = head;
		if (s.length() == 0) // For string which is empty
			current.endmarker = true;
		//Loop over the length of string
		for (int i = 0; i < s.length(); i++) {
			Node child = current.subNode(s.charAt(i));
			// if character already present just increment its prefix 
			if (child != null) {
				current = child;
				current.prefix++;
			} else {   // if character not present add a new character node 
				current.childNode.add(new Node(s.charAt(i)));
				current = current.subNode(s.charAt(i));
			}
			// Set marker to indicate end of the word
			if (i == s.length() - 1)
				current.endmarker = true;
		}
	}

	public boolean search(String s) {
		Node current = head;
		while (current != null) {
			//Loop over the length of string
			for (int i = 0; i < s.length(); i++) {
				if (current.subNode(s.charAt(i)) == null)
					return false;
				else
					current = current.subNode(s.charAt(i));
			}
			
			// It means that string is in the Trie but is it really a word?
			if (current.endmarker == true)
				return true;
			else
				return false;
		}
		return false;
	}
	
	public boolean delete(String s) {
		if(search(s))   // Check for string is already present, if not it cannot be deleted
		{
			Node current = head;
			Node temp;  // To keep track of parent
			while (current != null) {
				//Loop over the length of string
				for(int i=0; i < s.length(); i++)
				{
					temp = current;
					//System.out.println(s.charAt(i));
					current = current.subNode(s.charAt(i));
					//System.out.println(current.prefix);
					current.prefix--;
					if(current.prefix == 0)
					{
						//System.out.println(current.content);
						temp.childNode.remove(current);
						break;
					}
				}
				// remover the endmarker to indicate the word removal
				current.endmarker = false;
				return true;
			}
		}
		return false;
	}
}