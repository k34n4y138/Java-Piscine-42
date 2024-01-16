
public class Program {
    static int count = 5;
    Object lock = new Object();

    static void setArg(String[] arg) {
        if (arg.length == 1 && arg[0].startsWith("--count=")) {
            try {
                count = Integer.parseInt(arg[0].substring(8));
            } catch (NumberFormatException e) {
                System.out.println("Invalid argument");
                System.exit(1);
            }
        } else {
            System.out.println("Usage: java Program --count=X");
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        // first argoument is --count=10
        setArg(args);
        Worker hen = new Worker("Hen");
        Worker egg = new Worker("Egg");

        egg.start();
        hen.start();

        try {
            hen.join();
            egg.join();
        } catch (Exception e) {
            System.out.println("Interrupted");
            e.printStackTrace();
        }
    }
}

class Worker extends Thread {
    static Object lock = new Object();
    int count = 5;
    String name;

    Worker(String name) {
        this.name = name;
    }

    public void run() {
        synchronized (lock) {
            do {
                System.out.println(name);
                lock.notify();
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    System.out.println("Interrupted");
                    e.printStackTrace();
                    return;
                }
            } while (count-- > 0);
            lock.notify();
        }
    }
}
