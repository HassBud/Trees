import java.util.Random;

public class Benchmark {
    public static int[] sorted(int n) {
        Random rnd = new Random();
        int[] array = new int[n];
        int nxt = rnd.nextInt(10);

        for (int i = 0; i < n; i++) {
            array[i] = nxt;
            nxt += rnd.nextInt(10) + 1;
        }
        return array;
    }

    public static int[] unSortedArray(int n) {
        Random rand = new Random();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = rand.nextInt(n * 5)+1;
        }
        return array;
    }
    public static void binary(int[] array, int[] indx) {
        for (int i = 0; i < indx.length; i++) {
            Binary.binarySearch(array, indx[i]);
        }
    }
    public static BinaryTree unSortedTree(int n) {
        Random rand = new Random();
        BinaryTree newTree = new BinaryTree();
        newTree.add(n/2, 0); // Hittar mellersta värdet som läggs till som root för att undvika obalanserade träd
        for (int i = 1; i < n; i++) {
            newTree.add(rand.nextInt(n)+1,i);
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
        
        int[] sizes = {500,1000,2000,4000,8000,16000}; //
        int numberOfKeys = 10000;
        int rounds = 10000;
        System.out.printf("# Lookup benchmarking\n");
        System.out.printf("#%10s%9s%10s\n", "n", "Binary tree", "Binary Array" );
        for ( int n : sizes) {
            int loop = 10000;

            System.out.printf("%8d", n);

            // warm up phase
            for (int i = 0; i <loop/10 ; i++) {
                BinaryTree warmup = unSortedTree(n);
                int[] warmupKeys = keys(numberOfKeys,n);
                findKeys(warmup,warmupKeys);
            }

            System.gc();

            BinaryTree tree = unSortedTree(n);
            int[] arrayUnSort = sorted(n);

            int[] keys = keys(numberOfKeys,n);
            int[] keysArray = keys(numberOfKeys,n);


            double min = Double.POSITIVE_INFINITY;

                for (int i = 0; i < rounds; i++) {
                    long t0 = System.nanoTime();
                    findKeys(tree,keys);
                    long t1 = System.nanoTime();
                    double t = (t1 - t0)/keys.length;
                    if (t < min)
                        min = t;
                }

                System.out.printf("%10.0f" , (min));

            min = Double.POSITIVE_INFINITY;
            for (int i = 0; i < rounds; i++) {
                long t0 = System.nanoTime();
                binary(arrayUnSort, keysArray);
                long t1 = System.nanoTime();
                double t = (t1 - t0)/keys.length;
                if (t < min)
                    min = t;
            }

            System.out.printf("%9.0f\n", (min));

        }
    }

}
