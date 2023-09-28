import java.util.Random;

public class BinaryTree {
    public class Node {
        public Integer key;
        public Integer value;
        public Node left, right;

        public Node(Integer key, Integer value) {
            this.key = key;
            this.value = value;
            this.left = this.right = null;
        }
        public Integer lookupNode(Integer searchKey){
            if(key == searchKey){
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
