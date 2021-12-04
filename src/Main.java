public class Main {
    public static void main(String[] args) {
        Object mon = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (mon) {
                    for (int i = 0; i < 5; i++) {
                        System.out.print("A");
                        try {
                            mon.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (mon) {
                    for (int i = 0; i < 5; i++) {
                        System.out.print("B");
                        try {
                            mon.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mon.notify();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (mon){
                    for (int i = 0; i < 5; i++) {
                        mon.notifyAll();
                        System.out.print("C");
                        try {
                            mon.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
}
