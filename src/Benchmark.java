import java.util.Random;

public class Benchmark {

    public static int[] unSortedArray(int n) {
        Random rand = new Random();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = rand.nextInt(n * 5)+1;
        }
        return array;
    }
    public static BinaryTree unSortedTree(int n) {
        Random rand = new Random();
        BinaryTree newTree = new BinaryTree();
        newTree.add(n/2, 0); // Hittar mellersta värdet som läggs till som root för att undvika obalanserade träd
        for (int i = 1; i < n; i++) {
            newTree.add(rand.nextInt(n),i);
        }
        return newTree;
    }
    public static int[] keys(int loop, int n) {
        Random rnd = new Random();
        int[] indx = new int[loop];
        for (int i = 0; i < loop; i++) {
            indx[i] = rnd.nextInt(n * 5);
        }
        return indx;
    }
    private  static void findKeys(BinaryTree tree, int[] keys){
        for (int i = 0; i < keys.length ; i++) {
            tree.lookup(keys[i]);
        }
    }

    public static void main(String[] arg) {



        int[] sizes = {1,2,3}; //1600, 3200, 6400, 10000, 15000, 25000, 50000, 100000
        int numberOfKeys = 10;
        int rounds = 1000;
        System.out.printf("# Lookup benchmarking\n");
        System.out.printf("#%7s%9s\n", "n", "Lookup");
        for ( int n : sizes) {
            int loop = 10000;

            System.out.printf("%8d", n);

            BinaryTree tree = unSortedTree(n);
            int[] keys = keys(numberOfKeys,n);


            double min = Double.POSITIVE_INFINITY;
                for (int i = 0; i < rounds; i++) {
                    long t0 = System.nanoTime();
                    findKeys(tree,keys);
                    long t1 = System.nanoTime();
                    double t = (t1 - t0)/keys.length;
                    if (t < min)
                        min = t;
                }

                System.out.printf("%10.0f\n" , (min));

        }
    }

}
