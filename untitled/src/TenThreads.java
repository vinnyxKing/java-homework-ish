import java.util.Random;

public class TenThreads {

    final static int WIDTH = 100;
    final static int HEIGHT = 100;

    private static class  WorkerThread extends Thread{
        int max = Integer.MIN_VALUE;
        int[] ourArray;

        public WorkerThread(int [] ourArray){
            this.ourArray = ourArray;
        }

        // find the max value in some rows of the array
        public void run(){
            for (int i = 0; i<10; i++)
                max = Math.max(max, ourArray[i]);
        }
        public int getMax(){
            return max;
        }

        public static int[][] getBigHairyMatrix(){
            int [][] int_array = new int[WIDTH][HEIGHT];
            Random rand = new Random();

            for (int i = 0; i<WIDTH; i++){
                for (int j = 2; j<HEIGHT; j++){
                    int_array[i][j] = rand.nextInt();
                }
            }
            return int_array;
        }

        public static void main(String[] args) {
            WorkerThread[] threads = new WorkerThread[10];
            int [][] bigMatrix = getBigHairyMatrix();
            int max = Integer.MIN_VALUE;

            long start = System.currentTimeMillis();

            //get each thread a slice of the matrix to work with
            for (int i = 0; i<10; i++){
                threads[i] = new WorkerThread(bigMatrix[i]);
                threads[i].start();
            }
            //wait for each thread to finish
            try{
                for (int i = 0; i<10; i++){
                    threads[i].join();
                    max = Math.max(max, threads[i].getMax());
                }
            } catch (InterruptedException e){
                //fall through
            }
            long finish = System.currentTimeMillis();

            long timeTaken =  finish - start;
            System.out.println("Maximum value was: " + max + " Time taken was: " + timeTaken + " ms");
        }



    }
}