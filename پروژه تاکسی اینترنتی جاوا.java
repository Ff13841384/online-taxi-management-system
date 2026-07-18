import java.util.Scanner;
import java.util.Random;
import java.math.*;
public class First {
	public static void main(String[] args) {
		 Passenger ps = new Passenger("Fatemeh Shafiei", 2, 3, 5, 4);
		 Driver d = new Driver();
		 TaxiManager manager = new TaxiManager();
		 manager.start(ps,d);
		 
		}}
class Passenger{
	private String name;
	private int xstart;
	private int ystart;
	private int xstop;
	private int ystop;
	
	public Passenger(String name, int xstart, int ystart, int xstop, int ystop) {
		this.name = name;
		this.xstart = xstart;
		this.ystart = ystart;
		this.xstop = xstop;
		this.ystop = ystop;}
	
	public String getname() {
		return name;
	}
	public int getxstart() {
		return xstart;
	}
	public int getystart() {
		return ystart;
	}
	public int getxstop() {
		return xstop;
	}
	public int getystop() {
		return ystop;
	}
	public double distance() {
		return Math.sqrt(Math.pow(xstop - xstart, 2) + Math.pow(ystop - ystart, 2));
	}}
	
class Driver{
	   private int[][] place = new int[3][2];  
	    Random rand = new Random();

	    public void loadplace() {
	        for (int i = 0; i < 3; i++) {
	            int randomX = rand.nextInt(10);
	            int randomY = rand.nextInt(10);
	            
	            place[i][0] = randomX;
	            place[i][1] = randomY;
	            
	            
	            System.out.printf("Place of driver %d: (%d, %d)\n", i + 1, place[i][0], place[i][1]);
	        }
	    }

	    public int getdriverx(int i) { return place[i][0]; }
	    public int getdrivery(int i) { return place[i][1]; }
	    public int getcountofdriver() { return 3; }

	    public double calculateprice(int i, double distance) {
	        double[] rate = {1, 1.3, 1.5};
	        return 3.3 + (distance * rate[i - 1]);
	    }}
class TaxiManager{
	Scanner sc = new Scanner(System.in);

    public void start(Passenger ps, Driver d) {

        System.out.println("Do you need a taxi? (yes/no)");
        String request = sc.next();

        if (request.equalsIgnoreCase("yes")) {

            System.out.println("Welcome to our online taxi *" + ps.getname() + "*");

            System.out.println("What is the number of passengers?");
            int countofpassenger = sc.nextInt();

            if (countofpassenger >= 5 || countofpassenger <= 0) {

                System.out.println("Passenger number is invalid.\n");
            }

            else {

                d.loadplace();
                
    	 
                System.out.println("Distance of each driver to passenger:");

                for (int i = 0; i < d.getcountofdriver(); i++) {

                    double dist = Math.sqrt(Math.pow(ps.getxstart() - d.getdriverx(i), 2) + Math.pow(ps.getystart() - d.getdrivery(i), 2));

                    System.out.printf("Driver %d is %.3f km to passenger\n",i + 1, dist);
                }

                System.out.println("\nWhich driver index do you choose? (1 to 3)");

                int driverindex = sc.nextInt();

                double distanceToStart = Math.sqrt(
                        Math.pow(ps.getxstart() - d.getdriverx(driverindex - 1), 2)
                                +
                                Math.pow(ps.getystart() - d.getdrivery(driverindex - 1), 2));

                System.out.printf("The distance from driver place to passenger is : %.3f km\n",
                        distanceToStart);

                double tripDistance = Math.sqrt(
                        Math.pow(ps.getxstop() - ps.getxstart(), 2) + Math.pow(ps.getystop() - ps.getystart(), 2));

                System.out.printf("The distance from start to stop is : %.3f km\n",
                        tripDistance);

                double totalDistance = distanceToStart + tripDistance;

                System.out.printf("The total distance is : %.3f km\n",
                        totalDistance);

                double finalPrice = d.calculateprice(driverindex, totalDistance);

                System.out.printf("Total price is : %.3f\n", finalPrice);

                System.out.println("What is the weather today? (sunny/cloudy/rainy/snowy)");

                String weather = sc.next();

                if (weather.equalsIgnoreCase("rainy")) {

                    finalPrice *= 1.3;
                }

                else if (weather.equalsIgnoreCase("snowy")) {

                    finalPrice *= 1.5;
                }

                System.out.printf("Price after weather effect : %.3f\n",
                        finalPrice);
                
     

                System.out.println("Do you have a discount code? (yes/no)");

                String code = sc.next();

                if (code.equalsIgnoreCase("yes")) {

                    System.out.println("Please enter your discount percentage:");

                    double discount = sc.nextDouble();

                    finalPrice = finalPrice * (1 - discount / 100);
                }

                System.out.printf("Final price is : %.3f$\n", finalPrice);

                System.out.println("Please enter a score to driver from 1 to 5:");

                int score = sc.nextInt();

                if (score >= 1 && score <= 5) {


                System.out.printf("Score is %d.\n", score);

                System.out.println("Thank you for your choice!");
                
            }else {System.out.println("This score is invalid.");}
            }}
    else {

        System.out.println("Passenger does not need a taxi.");
    }
        sc.close();
    }}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
