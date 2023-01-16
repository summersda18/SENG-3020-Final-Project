import java.util.Scanner;
// packages for currency format
import java.util.Locale;
import java.text.NumberFormat;

class Photo{
    private int numPics;
    private int[] sizes;
    private int[] finishes;
    private int[] deliveryTimes;
    private String discountCode;

    // load all data into the object
    Photo(int fourBySix, int fiveBySeven, int eightByTen, int fourMatte, int fiveMatte, int eightMatte, int today, int tomorrow, String discount){

        // total number of pictures
        numPics = fourBySix + fiveBySeven + eightByTen;

        // sizes[x] = # of each image size
        // | 0 = 4x6 | 1 = 5x7 | 2 = 8x10 |
        sizes = new int[3];
        sizes[0] = fourBySix;
        sizes[1] = fiveBySeven;
        sizes[2] = eightByTen;

        // finishes[x] = # of matte fisnishes. # of glossy is sizes[x] - finishes[x]
        // | 0 = 4x6 | 1 = 5x7 | 2 = 8x10 |
        finishes = new int[3];
        finishes[0] = fourMatte;
        finishes[1] = fiveMatte;
        finishes[2] = eightMatte;

        // deliveryTimes[x] = # of images ordered in 1 hour(today) || 1 day(tomorrow)
        deliveryTimes = new int[2];
        deliveryTimes[0] = today;
        deliveryTimes[1] = tomorrow;

        // discount code
        discountCode = discount;

    }

    public double Calc(){
        double cost = -1;
        int deliveryTotal = deliveryTimes[0] + deliveryTimes[1];
        // I hate myself for writing this
        if(sizes[0] < 0 || sizes[0] > 100 || sizes[1] < 0 || sizes[1] > 100 || sizes[2] < 0 || sizes[2] > 100 || numPics != deliveryTotal || finishes[0] < 0 || finishes[0] > sizes[0] || finishes[1] < 0 || finishes[1] > sizes[1] || finishes[2] < 0 || finishes[2] > sizes[2]){ // Congrats you scrolled all the way over here
            return cost;
        }

        // Calculations for 4x6 all the same
        if(sizes[0] == numPics){
            if(finishes[0] == 0 || finishes[0] == numPics){
                if(deliveryTimes[0] == numPics || deliveryTimes[1] == numPics){
                    if (sizes[0] > 75){
                        cost = sizes[0] * 0.10;
                    } else if(sizes[0] > 50){
                        cost = sizes[0] * 0.12;
                    } else{
                        cost = sizes[0] * 0.14;
                    }
                    if(finishes[0] != 0){cost = cost + numPics * 0.02;}
                    if(deliveryTimes[0] != 0){
                        if(deliveryTimes[0] <= 60){cost = cost + 1;}
                        else{cost = cost + 1.50;}
                    }
                    cost = Discount(cost);
                    return cost;
                }
            }
        }
        // Calculations for 5x7 all the same
        else if(sizes[1] == numPics){
            if(finishes[1] == 0 || finishes[1] == numPics){
                if(deliveryTimes[0] == numPics || deliveryTimes[1] == numPics){
                    if (sizes[1] > 75){
                        cost = sizes[1] * 0.28;
                    } else if(sizes[1] > 50){
                        cost = sizes[1] * 0.31;
                    } else{
                        cost = sizes[1] * 0.34;
                    }
                    if(finishes[1] != 0){cost = cost + numPics * 0.03;}
                    if(deliveryTimes[0] != 0){
                        if(deliveryTimes[0] <= 60){cost = cost + 1;}
                        else{cost = cost + 1.50;}
                    }
                    cost = Discount(cost);
                    return cost;
                }
            }
        }
        // Calculations for 8x10 all the same
        else if(sizes[2] == numPics){
            if(finishes[2] == 0 || finishes[2] == numPics){
                if(deliveryTimes[0] == numPics || deliveryTimes[1] == numPics){
                    if (sizes[2] > 75){
                        cost = sizes[2] * 0.60;
                    } else if(sizes[2] > 50){
                        cost = sizes[2] * 0.64;
                    } else{
                        cost = sizes[2] * 0.68;
                    }
                    if(finishes[2] != 0){cost = cost + numPics * 0.04;}
                    if(deliveryTimes[0] != 0){
                        if(deliveryTimes[0] <= 60){cost = cost + 1;}
                        else{cost = cost + 1.50;}
                    }
                    cost = Discount(cost);
                    return cost;

                }
            }
        }
        // Calculations for all different
        cost = (sizes[0] * 0.19) + (sizes[1] * 0.39) + (sizes[2] * 0.79) + (finishes[0] * 0.04) + (finishes[1] * 0.06) + (finishes[2] * 0.08);
        cost = Delivery(cost);
        cost = Discount(cost);
        return cost;
    }

    // method to calculate the delivery
    private double Delivery(double total){
        if(deliveryTimes[0] != 0){
            if(deliveryTimes[0] <= 60){return total + 2;}
            else{return total + 2.50;}
        }
        return total;
    }

    // method to calculate the discounts
    private double Discount(double total){
        if(total < 40 && discountCode.equalsIgnoreCase("N56M2") && numPics <= 100){total = total - 2;}
        else if(total >= 35){total = total * 0.95;}
        else if(discountCode.equalsIgnoreCase("N56M2") && numPics <= 100){total = total - 2;}
        // set total to 0 if total < 0 after the discounts
        if(total < 0){total = 0;}
        return total;
    }

    // Main method
    public static void main(String[] args){
        int picCount, fourBySix, fiveBySeven, eightByTen, fourMatte, fiveMatte, eightMatte, fourToday, fiveToday, eightToday, today, tomorrow;
        double total;
        String dicountCode;
        Photo photo;
        Scanner in = new Scanner(System.in);

        System.out.println("How many 4x6 pictures do you want?");
        fourBySix = Integer.parseInt(in.nextLine());
        System.out.println("Enter the number of matte finished 4x6 pictures you want. Enter 0 if you want all glossy finishes.");
        fourMatte = Integer.parseInt(in.nextLine());
        System.out.println("Enter the amount you want to pick up in 1 hour. Enter 0 if you want to pick up all tomorrow.");
        fourToday = Integer.parseInt(in.nextLine());
        System.out.println("How many 5x7 pictures do you want?");
        fiveBySeven = Integer.parseInt(in.nextLine());
        System.out.println("Enter the number of matte finished 5x7 pictures you want. Enter 0 if you want all glossy finishes.");
        fiveMatte = Integer.parseInt(in.nextLine());
        System.out.println("Enter the amount you want to pick up in 1 hour. Enter 0 if you want to pick up all tomorrow.");
        fiveToday = Integer.parseInt(in.nextLine());
        System.out.println("How many 8x10 pictures do you want?");
        eightByTen = Integer.parseInt(in.nextLine());
        System.out.println("Enter the number of matte finished 8x10 pictures you want. Enter 0 if you want all glossy finishes.");
        eightMatte = Integer.parseInt(in.nextLine());
        System.out.println("Enter the amount you want to pick up in 1 hour. Enter 0 if you want to pick up all tomorrow.");
        eightToday = Integer.parseInt(in.nextLine());
        System.out.println("Enter a discount code if you have one.");
        if(in.hasNextLine()){dicountCode = in.nextLine();}
        else{dicountCode = "";}
        in.close();

        picCount = fourBySix + fiveBySeven + eightByTen;
        today = fourToday + fiveToday + eightToday;
        tomorrow = picCount - today;
        photo = new Photo(fourBySix, fiveBySeven, eightByTen, fourMatte, fiveMatte, eightMatte, today, tomorrow, dicountCode);
        total = photo.Calc();

        // format to US currency
        String fmt = NumberFormat.getCurrencyInstance(Locale.US).format(total);
        System.out.println("Your total is " + fmt);
    }
}
