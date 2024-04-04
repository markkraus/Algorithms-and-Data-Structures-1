
public class ReallyLongInt2 	extends LinkedListPlus2<Integer>
        implements Comparable<ReallyLongInt2>
{
    private ReallyLongInt2()
    {
        super();
    }


    public ReallyLongInt2(String s)
    {
        super();
        char c;
        int digit = -1;

        // Iterate through the String, getting each character and converting it into
        // an int.  Then make an Integer and add at the front of the list.
        int j = 0;
        if(s.length() == 0){

        }else{
        }
        int i = 0;

        if (i < s.length())
        {
            c = s.charAt(i);
            if (('0' <= c) && (c <= '9'))
            {
                digit = c - '0';
                // Do not add leading 0s
                if (!(digit == 0 && this.getLength() == 0))
                    this.add(1, Integer.valueOf(digit));
            }
            else {
                throw new NumberFormatException("Illegal digit " + c);
            }
            //RECURSIVE CALL
            rec_ReallyLongInt2(i + 1, digit, c, s);
        }

        // If number is all 0s, add a single 0 to represent it
        if (digit == 0 && this.getLength() == 0)
            this.add(1, Integer.valueOf(digit));
    }
    private void rec_ReallyLongInt2(int i, int digit, char c, String s){
        if(i < s.length()){
            c = s.charAt(i);
            if (('0' <= c) && (c <= '9'))
            {
                digit = c - '0';
                // Do not add leading 0s
                if (!(digit == 0 && this.getLength() == 0))
                    this.add(1, Integer.valueOf(digit));
                rec_ReallyLongInt2(i + 1, digit, c, s);
            }
            else {
                throw new NumberFormatException("Illegal digit " + c);
            }
        }
    }
    // Copy constructor can just call super()
    public ReallyLongInt2(ReallyLongInt2 rightOp)
    {
        super(rightOp);
    }

    // Constructor with a long argument.  You MUST create the ReallyLongInt
    // digits by parsing the long argument directly -- you cannot convert to a String
    // and call the constructor above.  As a hint consider the / and % operators to
    // extract digits from the long value.
    public ReallyLongInt2(long X)
    {
        int x = (int) X;
        if(X == 0 && numberOfEntries == 0) {
            add(1,Integer.valueOf(x));
        }
        if(X > 0){
            rec_RLI2(X);
        }
    }
    private void rec_RLI2(long X){
        if(X > 0) {
            int r = (int) (X % 10);
            if (!(X == 0 && numberOfEntries == 0)) {
                add(1, Integer.valueOf(r));
            }
            firstNode = firstNode.next;
            X = X /10;
            rec_RLI2(X);
        }
    }
    // Method to put digits of number into a String.  Note that toString()
    // has already been written for LinkedListPlus, but you need to
    // override it to show the numbers in the way they should appear.
    public String toString()
    {
        StringBuilder b = new StringBuilder();
        Node curr = firstNode.prev;
        int i = 0;
        if(i < numberOfEntries) {
            b.append(curr.data.toString());
            curr = curr.prev;
            return rec_toString(b, curr, i + 1);
        }
        return b.toString();
    }
    private String rec_toString(StringBuilder b, Node curr, int i){
        if(i < numberOfEntries){
            b.append(curr.data.toString());
            curr = curr.prev;
            return rec_toString(b, curr, i + 1);
        }
        return b.toString();
    }


    // Return new ReallyLongInt which is sum of current and argument
    public ReallyLongInt2 add(ReallyLongInt2 rightOp) {
        ReallyLongInt2 Z = new ReallyLongInt2();
        Node currT = firstNode;
        Node currR = rightOp.firstNode;
        int carry = 0;
        int count = 0;
        int grandCount = 0;
        if (numberOfEntries == rightOp.numberOfEntries) {
            if(count <= rightOp.numberOfEntries){
                rec_addEqual(count, carry, currT, currR, rightOp, Z);
            }
        } else if (numberOfEntries < rightOp.numberOfEntries) {
            count = numberOfEntries;
           carry = rec_addLT(count, carry, grandCount, currT, currR, rightOp, Z);
            if(carry == 1){
                Z.add(1,Integer.valueOf(carry));
                Z.leftRotate(1);
            }
        } else if (numberOfEntries > rightOp.numberOfEntries) {
            count = rightOp.numberOfEntries;
            carry = rec_addGT(count, carry, grandCount, currT, currR, rightOp, Z);
            if(carry == 1){
                Z.add(1,Integer.valueOf(carry));
                Z.leftRotate(1);
            }
        }
        return Z;
    }
    private void rec_addEqual(int count, int carry, Node currT, Node currR, ReallyLongInt2 rightOp, ReallyLongInt2 Z){
        if(count <= rightOp.numberOfEntries){
            int sum = currT.data + currR.data + carry;
            carry = 0;
            if (sum >= 10) {
                int temp = sum;
                sum = sum % 10;
                carry = (temp - sum) / 10;
            }
            if (!(sum == 0 && this.getLength() == 0)) {
                Z.add(1, Integer.valueOf(sum));
                Z.leftRotate(1);
            }
            currT.setNextNode(currT);
            currR.setNextNode(currR);
            rec_addEqual(count + 1, carry, currT, currR, rightOp, Z);
        }
    }
    private int rec_addLT(int count, int carry, int grandCount, Node currT, Node currR, ReallyLongInt2 rightOp, ReallyLongInt2 Z) {
        if (grandCount != rightOp.numberOfEntries) {
            if (count != 0) {
                int sum = currT.data + currR.data + carry;
                carry = 0;
                if (sum >= 10) {
                    carry = 1;
                    sum %= 10;
                }
                if (!(sum == 0 && this.getLength() == 0)) {
                    Z.add(1, Integer.valueOf(sum));
                    Z.leftRotate(1);
                }
                currT = currT.next;
                currR = currR.next;
            } else {
                int sum = currR.data + carry;
                carry = 0;
                if (sum >= 10) {
                    int temp = sum;
                    sum = sum % 10;
                    carry = (temp - sum) / 10;
                }
                if (!(sum == 0 && this.getLength() == 0)) {
                    Z.add(1, Integer.valueOf(sum));
                    Z.leftRotate(1);
                }
                currR.setNextNode(currR);
                count = 1;
            }
            rec_addLT(count - 1, carry, grandCount + 1,currT, currR, rightOp, Z);
        }
        return carry;
    }
    private int rec_addGT(int count, int carry, int grandCount, Node currT, Node currR, ReallyLongInt2 rightOp, ReallyLongInt2 Z){
        if (grandCount != numberOfEntries) {
            if (count != 0) {
                int sum = currT.data + currR.data + carry;
                carry = 0;
                if (sum >= 10) {
                    int temp = sum;
                    sum = sum % 10;
                    carry = (temp - sum) / 10;
                }
                if (!(sum == 0 && this.getLength() == 0)) {
                    Z.add(1, Integer.valueOf(sum));
                    Z.leftRotate(1);
                }
                currT.setNextNode(currT);
                currR.setNextNode(currR);
            } else {
                int sum = currT.data + carry;
                carry = 0;
                if (sum >= 10) {
                    carry = 1;
                    sum %= 10;
                }
                if (!(sum == 0 && this.getLength() == 0)) {
                    Z.add(1, Integer.valueOf(sum));
                    Z.leftRotate(1);
                }
                currT.setNextNode(currT);
                count = 1;
            }
            rec_addGT(count - 1, carry, grandCount + 1,currT, currR, rightOp, Z);
        }
        return carry;
    }


    public ReallyLongInt2 subtract(ReallyLongInt2 rightOp){
       ReallyLongInt2 answer = new ReallyLongInt2();
       Node currT = firstNode;
       Node currR = rightOp.firstNode;
       if(compareTo(rightOp) == -1){
           throw new ArithmeticException("Cannot subtract");
       }

        class recursiveMath {
            private void rec_subtract(Node currT, Node currR, int index, int pCarry){
                int value = currT.data;
                if(pCarry != 0){
                    value += pCarry;
                    pCarry = 0;
                }
                if(index < rightOp.numberOfEntries){
                    //check bottom number is within index
                    value -= currR.data;
                    //pass on carry if difference is negative. Reevaluate values
                    if(value < 0){
                        value += 10;
                        pCarry = -1;
                    }

                    answer.add(value);
                    index += 1;
                    currT = currT.next;
                    currR = currR.next;
                    rec_subtract(currT, currR, index, pCarry);
                }else if(numberOfEntries > index){
                    if(value < 0){
                        value += 10;
                        pCarry = -1;
                    }
                    if(value != 0){
                        answer.add(value);
                        currT = currT.next;
                        index += 1;
                        rec_subtract(currT, currR, index, pCarry);
                    }


                }

            }
        }
        recursiveMath math = new recursiveMath();
        math.rec_subtract(currT, currR, 0, 0);
        int answerValue = answer.firstNode.prev.data;
        Node answerfNode = answer.firstNode;

        removeZero(answerValue, answer, answerfNode);

        return answer;
    }

    private void removeZero(int answerValue, ReallyLongInt2 answer, Node answerfNode){
        if((answerValue == 0) && answer.numberOfEntries != 1){
            answer.rightShift(1);
            removeZero(answerValue, answer, answerfNode);
        }
    }



    // Return new ReallyLongInt which is product of current and argument
    //NOT FULLY FUNCTIONING
    public ReallyLongInt2 multiply(ReallyLongInt2 rightOp){
        ReallyLongInt2 small = new ReallyLongInt2();
        ReallyLongInt2 big = new ReallyLongInt2();

        int counter = 0;
        if(this.numberOfEntries > rightOp.numberOfEntries){
            small = rightOp;
            big = this;
        }else{
            small = this;
            big = rightOp;
        }
        Node currSmall = small.firstNode;
        ReallyLongInt2 answer = new ReallyLongInt2();
        answer.add(1,0);


        return rec_multiply(small, big, counter, 0, answer, currSmall);


//        int total = 0;
//        ReallyLongInt2 small = new ReallyLongInt2();
//        ReallyLongInt2 large = new ReallyLongInt2();
//        if(compareTo(rightOp) == 1){
//            large = this;
//            small = rightOp;
//        }else{
//            large = rightOp;
//            small = this;
//        }
//        Node curr1 = small.firstNode;
//        Node curr2 = large.firstNode;
//        int carry = 0;
//        int i = 0;
//        int j = 0;
//        rec_mulitply(i, j, small, large, carry, curr1, curr2, total);
//
//
//        return new ReallyLongInt2(Integer.toString(total));
    }

    private ReallyLongInt2 rec_multiply(ReallyLongInt2 small, ReallyLongInt2 big, int counter, int carry, ReallyLongInt2 answer, Node currSmall){
        if(counter == small.numberOfEntries){return answer;}
        ReallyLongInt2 product = singleMult(currSmall.data, big, counter);
        answer = answer.add(product);
        currSmall = currSmall.next;
        return rec_multiply(small, big, counter + 1, carry, answer, currSmall);

    }

    private ReallyLongInt2 singleMult(int digit, ReallyLongInt2 big, int trail){
        ReallyLongInt2 product = new ReallyLongInt2();
        if(trail != 0) trailMaker(trail, 0, product);
        Node current = big.firstNode;
        rec_singleMult(digit, 0, current, big, 0, product);
        return product;

    }
    private void trailMaker(int count, int current, ReallyLongInt2 output){
        if(current == count){return;}
        output.add(1, 0);
        trailMaker(count, current + 1, output);
    }

    private void rec_singleMult(int digit, int carry, Node current, ReallyLongInt2 big, int count, ReallyLongInt2 output){
        if(count == big.numberOfEntries) {
            if(carry != 0) output.add(output.numberOfEntries + 1,Integer.valueOf(carry));
            return;
        }
        int tempProduct = (current.data * digit) + carry;
        carry = 0;
        int value = tempProduct;
        if(tempProduct >= 10){
            value = tempProduct % 10;
            carry = (tempProduct - value) / 10;
        }
        output.add(output.numberOfEntries + 1,Integer.valueOf(value));
        current = current.next;
        rec_singleMult(digit, carry, current, big, count + 1, output);

    }





    // Return -1 if current ReallyLongInt is less than rOp
    // Return 0 if current ReallyLongInt is equal to rOp
    // Return 1 if current ReallyLongInt is greater than rOp
    public int compareTo(ReallyLongInt2 rOp) {
        Node currThis = firstNode.prev;
        Node currROp = rOp.firstNode.prev;
        int i = 0;

        if (numberOfEntries > rOp.numberOfEntries) {
            return 1;
        } else if(numberOfEntries < rOp.numberOfEntries){
            return -1;
        }else{
            if(i <= numberOfEntries){
              return rec_compareTo(i, currThis, currROp);
            }
        }
        return 0;
    }
    private int rec_compareTo(int i, Node currThis, Node currROp){
        if(i <= numberOfEntries) {
            if (currThis.data == currROp.data) {
                currThis = currThis.prev;
                currROp = currROp.prev;
                i++;

            }if (currThis.data != currROp.data) {
                if (currThis.data > currROp.data) {
                    return 1;
                }else  {
                    return -1;
                }
            }
            return rec_compareTo(i, currThis, currROp);
        }
        return 0;
    }


    public boolean equals(Object rightOp)
    {
        ReallyLongInt2 Z = (ReallyLongInt2) rightOp;
        if(compareTo(Z) == 0){

        }else{
            return false;
        }
        return true;
    }
}
