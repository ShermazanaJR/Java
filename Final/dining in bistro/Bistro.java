public class Bistro {
    private int seats;
    private Thread currentOrder;
    private Thread currentMeal;
    private final Thread server;
    public Object o = new Object();

    public Bistro (int n) {
        seats = n;
        server = new Thread(this::serve);
        server.start();
    }

    public synchronized void dine(int price) {
        while (seats == 0) {
            try {o.wait();}
            catch (InterruptedException e) {e.printStackTrace();}
        }
        seats--;

        while (currentOrder != null) {
            try {o.wait();}
            catch (InterruptedException e) {e.printStackTrace();
            }
        }
        currentOrder = Thread.currentThread();
        System.out.println("Guest " + currentOrder.getId() + " orders for " + price + " Lari");
        o.notifyAll();


        while (currentMeal != Thread.currentThread()) {
            try {o.wait();   }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        currentMeal = null;
        System.out.println("Guest " + Thread.currentThread().getId() + " eats");
        seats++;
        o.notifyAll();
    }

    private synchronized void serve() {
        while (true) {
            while (currentOrder == null) {
                try {o.wait();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Thread customer = currentOrder;
            currentOrder = null;
            while (currentMeal != null) {
                try {o.wait();}
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            currentMeal = customer;
            System.out.println("Enjoy!");
            o.notifyAll();
        }
    }


    public void shutdown() {
        server.interrupt();
        System.out.println("the erver finished working.");
    }
}

    