import java.util.Iterator;
import java.util.Random;
import java.util.Stack;

public class BinaryTree implements Iterable<Integer> {

    public Iterator<Integer> iterator() {
        return new TreeIterator();
    }

    private class Node {
        public Integer key;
        public Integer value;
        public Node left, right;

        public Node(Integer key, Integer value) {
            this.key = key;
            this.value = value;
            this.left = this.right = null;
        }
        public Integer lookupNode(Integer searchKey){
            if(key.equals(searchKey)){
                return value;
            }
            else if(key > searchKey){
                if(left != null)
                    return left.lookupNode(searchKey);
            }
            else if (key < searchKey){
                if(right != null)
                    return right.lookupNode(searchKey);
            }

            return null;
        }
        public void addNode(Integer newKey, Integer value){
            if (key == newKey){
                this.value = value;
            }
            else{
                if(key > newKey){
                    if(left != null){
                       left.addNode(newKey,value);
                    }
                    else {
                        left = new Node(newKey, value);
                    }
                    return;
                }
                if ((key < newKey)){
                    if(right != null){
                        right.addNode(newKey,value);
                    }
                    else {
                        right = new Node(newKey,value);
                    }
                }

            }

        }
        public void print() {
            if(left != null)
                left.print();
            System.out.println(" key: " + key + "\tvalue: " + value);
            if(right != null)
                right.print();
        }

    }
    public class TreeIterator implements Iterator<Integer> {
        private Node next;
        private Stack<Node> stack;
        public TreeIterator() {
            this.stack = new Stack<>();
            this.next = root;
            // pushar i konstruktorn för att hasNext inte ska returnera false när vi har ett träd med noder i.
            while(next != null){
                stack.push(next);
                next = next.left;
            }
        }
        @Override
        public boolean hasNext() {
            return next != null;
        }
        @Override
        public Integer next() {
            while (next != null){
                Node savePop = stack.pop();
                next = next.right; // checks for right child
                while (next != null){
                    stack.push(next);
                    next = next.left;
                }
                return savePop.value;
            }
            return null; // returnerar null om next == null
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    Node root;
    public BinaryTree() {
        this.root = null;
    }

    public void add(Integer k, Integer val){

        if(root == null){
            root = new Node(k,val);
        }
        else root.addNode(k,val);

    }


    public Integer lookup(Integer k){

      return root.lookupNode(k);
    }




}
