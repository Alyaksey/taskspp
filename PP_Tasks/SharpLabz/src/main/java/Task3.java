public class Task3 {
    public static double calcX(int m, int n, int p, int a, int b, int c, int d, int e){
        double z = 52.7 * Math.pow(a,n)/Math.pow(b,2.5);
        double numerator = Math.pow(2, 1/n) * (12.3 + z)*(12.3 - z);
        double denominator = Math.exp(Math.sqrt(132.5+Math.sin(Math.pow(e,m)))) - Math.cos(Math.pow(d,p)*c);
        double x = numerator/denominator;
        return x;
    }
}
