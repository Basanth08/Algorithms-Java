package unit08;

public class Euclid {
    public static int gcd(int a, int b){
        // as long as A is not equal to b
        while (a != b) {
            // if a is Gretaer than B
            if (a > b){
            // let A = A - B
                a = a - b;
            }
        // otherwise 
        // let B = B - A
        // return A
            else {
                b = b - a;
            }
        }
        return a;
    }
    public static void main(String[] args) {
        System.out.println(gcd(27, 6));
    }
    
}
