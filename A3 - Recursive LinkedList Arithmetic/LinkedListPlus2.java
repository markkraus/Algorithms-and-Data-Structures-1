
public class LinkedListPlus2<T> extends A2LList2<T> {
    // Default constructor simply calls super()
    public LinkedListPlus2() {
        super();
    }
    
    public LinkedListPlus2(LinkedListPlus2<T> oldList) {
        super();
        //if oldList > 0 ...
        if (oldList.getLength() > 0) {
            //create the first Node for the new List
            //... for loop/while loop *may need a count variable* - append a new Node in the new List for each Node in the old List
            // make the new List circular (mod?)*check the end of the copy constructor*
        }
        {
            // Special case for first Node since we need to set the
            // firstNode instance variable.
            Node temp = oldList.firstNode;
            Node newNode = new Node(temp.data);
            firstNode = newNode;
            // Now we traverse the old list, appending a new Node with
            // the correct data to the end of the new list for each Node
            // in the old list.  Note how the loop is done and how the
            // Nodes are linked.
            Node currNode = firstNode;
            temp = temp.next;
            int count = 1;
            Node lastValue = new Node(null);
            //Base case - if count is bigger than or equal to length of oldList, oldList is empty
                if(count < oldList.getLength()) {
                    lastValue =  rec_LinkedListPlus2(oldList, currNode, newNode, temp, count);
            }
            lastValue.next = firstNode;  // currNode is now at the end of the list.
            firstNode.prev = lastValue;    // link to make the list circular *!!!!!!!!!!*
            numberOfEntries = oldList.numberOfEntries;
        }
    }

    private Node rec_LinkedListPlus2(LinkedListPlus2<T> oldList, Node currNode, Node newNode, Node temp, int count){
        if (count < oldList.getLength()){
            //the newNode is now the next Node in the list
            newNode = new Node(temp.data);
            //the current node in newList is the first node in newList
            currNode.next = newNode;
            //the previous node is the first node
            newNode.prev = currNode;
            //the old first node is assigned to the next node
            temp = temp.next;
            //the current node in the new List is assigned to next node in the new List
            currNode = currNode.next;
            //increment count
            count++;
            return rec_LinkedListPlus2(oldList, currNode, newNode, temp, count);
        }
        return currNode;
    }
    // Make a StringBuilder then traverse the nodes of the list, appending the
    // toString() of the data for each node to the end of the StringBuilder.
    // Finally, return the StringBuilder as a String.  Note that since the list
    // is circular, we cannot look for null.  Rather we must count the Nodes as
    // we progress down the list.
    @Override
    public String toString() {
        //Create new stringbuilder, and node that is set to the first node of our list
        StringBuilder b = new StringBuilder();
        Node curr = firstNode;
        //if our list is not empty, append the items in the list to our string builder. Note the use of our recursive
        //private toString. (see below)
        if(curr != null){
            b.append(curr.getData());
            b.append(" ");
            return rec_toString(curr.getNextNode(), b);
        }
        else return b.toString();
    }
        //Recursive toString
    private String rec_toString(Node curr, StringBuilder b){
        //our current node will be the next node now. We will recursively run through the entire list and append each value to the
        //StringBuilder until our curr equals the firstNode in the list
        if(curr != firstNode){
            b.append(curr.getData());
            b.append(" ");
            return rec_toString(curr.getNextNode(), b);
        }
        //Base case - means that there is only one entry within the list.
        else{
            return b.toString();
        }
    }
    // Remove num items from the front of the list
    public void leftShift(int num) {
        //if the num is greater than the length of the list then the list will be cleared completely
        if(num <= 0){
        }else if(num >= getLength()){
            clear();
        }else{
        //base case
            firstNode = firstNode.next;
            numberOfEntries--;
            firstNode.setPrevNode(firstNode.prev.prev);
            firstNode.prev.setNextNode(firstNode);
            leftShift(num - 1);
        }
    }
    // Remove num items from the end of the list
    public void rightShift(int num) {
        if(num <= 0){
        }else if(num >= getLength()){
            clear();
        }else{
        //base case
            numberOfEntries--;
            firstNode.setPrevNode(firstNode.prev.prev);
            firstNode.prev.setNextNode(firstNode);
            rightShift(num - 1);
        }
    }
    // Rotate to the left num locations in the list.  No Nodes
    // should be created or destroyed.
    public void leftRotate (int num)
    {
        if(num == 0){
        }else if(num < 0) {
            firstNode = firstNode.prev;
            leftRotate(num + 1);
        }else if(num > getLength()){
            num %= getLength();
            firstNode = firstNode.next;
            leftRotate(num - 1);
        }else{
            //base case
            firstNode = firstNode.next;
            leftRotate(num - 1);
        }
    }
    // Rotate to the right num locations in the list.  No Nodes
    // should be created or destroyed.
    public void rightRotate (int num)
    {
        if(num == 0){
        }else if(num < 0) {
            firstNode = firstNode.next;
            rightRotate(num + 1);
        }else if(num > getLength()){
            num %= getLength();
            firstNode = firstNode.prev;
            rightRotate(num - 1);
        }else{
            //base case
            firstNode = firstNode.prev;
            rightRotate(num - 1);
        }
    }
}
