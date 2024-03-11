import java.util.Date;
public class Main {

    public static void main(String[] args) {
        Date d1 = new Date(2021, 10, 10);
        Date d2 = new Date(2021, 10, 11);
        Atelier a1 = new Atelier(1, d1, d2);
        System.out.println(a1);


    }
    
}
