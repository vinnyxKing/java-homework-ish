public class Value {

    private int num;
    private boolean valueset = false;


    public Value() {
        num = 0;
    }

    public synchronized void set(int n){
        while (valueset){
            try {
                wait();
            }catch (InterruptedException e){}
        }

            num = n;
            System.out.println("Set: " + num);
            valueset = true;
            notify();
        }

    public synchronized void display(){
        while (!valueset){
            try {
                wait();
            }catch (InterruptedException e){}
        }

        System.out.println("Show: " + num);
        valueset = false;
        notify();

    }

}

class Update implements Runnable{

    Value val;

    public Update(Value val) {
        this.val = val;
    }


    @Override
    public void run() {
        int i = 0;
        while (true){
            val.set(i++);
            try {Thread.sleep(1000);}catch (InterruptedException e){}

        }
    }
}

class Read implements Runnable{

    Value val;

    public Read(Value val) {
        this.val = val;
    }

    @Override
    public void run() {
        while (true){
            val.display();
            try {Thread.sleep(1000);}catch (InterruptedException e){}

        }
    }
}
