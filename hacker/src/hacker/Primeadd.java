package hacker;


public class Primeadd {

    public static void findallprimesum(int num){
        int sum=0;
        for(int i=2;i<=num;i++){
            if(isPrime(i)) {
                sum = sum + i;
                System.out.print(i + " ");
            }
        }
        System.out.println(sum);

    }

    public static boolean isPrime(int num){
        if(num<=1)
            return false;
        if(num==2)
            return true;
        if(num%2==0)
            return false;

        for(int i=3;i<=Math.sqrt(num);i++){
            if(num%i==0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
       // Scanner in = new Scanner(System.in);
       // int testcase=in.nextInt();
       // int[] num = new int[testcase];
        int testcase = 2;
        int[] num = {5,10};
        int sum =0;
       // for(int i=0;i<testcase;i++){
       //     num[i]=in.nextInt();
      //  }
        for(int i=0;i<testcase;i++){
            findallprimesum(num[i]);
        }
    }
}