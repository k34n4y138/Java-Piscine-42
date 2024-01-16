
public class Program {
    static int count = 5;
    static Runnable hen = () -> {
        for (int i = 0; i < count; i++) {
            System.out.println("Hen");
        }
    };
    static Runnable egg = () -> {
        for (int i = 0; i < count; i++) {
            System.out.println("Egg");
        }
    };
    static Runnable human = () -> {
        for (int i = 0; i < count; i++) {
            System.out.println("Human");
        }
    };

    public static void main(String[] args) {
        // first argoument is --count=10
        if (args.length == 1 && args[0].startsWith("--count=")) {
            try {
                count = Integer.parseInt(args[0].substring(8));
            } catch (NumberFormatException e) {
                System.out.println("Invalid argument");
                return;
            }
        }
        else {
            System.out.println("Usage: java Program --count=X");
            return;
        }
        Thread henThread = new Thread(hen);
        Thread eggThread = new Thread(egg);

        henThread.start();
        eggThread.start();
        human.run();
        try
        {
            henThread.join();
            eggThread.join();
        }
        catch (InterruptedException e)
        {
            System.out.println("Interrupted");
            e.printStackTrace();
        }
    }
}
