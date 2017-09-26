package gq.gouq;

public class NotMain {
	
	static long start = System.currentTimeMillis();
	
    public static void main(String[] args){
    	System.out.println("Start Successfull!");
        Main realmain = new Main();
        realmain.mmain(args);
        System.out.println("Seconds since Start : " + (System.currentTimeMillis() - start) / 1000.0 + "!");
        System.out.println("Exit Successfull!");
    }
}
