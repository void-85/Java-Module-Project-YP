import java.util.Scanner;



public class Main
{
    private static Scanner input = new Scanner( System.in );

    private static boolean number_of_accounts_entered = false ;
    private static boolean items_entered              = false ;
    private static int     number_of_accounts         = 0     ;

    private static final String EXIT_COMMAND = "Завершить";
    //static final String EXIT_COMMAND = "777";

    private static final String ANSI_RED    = "\u001B[31m" ;
    private static final String ANSI_GREEN  = "\u001B[32m" ;
    private static final String ANSI_YELLOW = "\u001B[33m" ;
    private static final String ANSI_BLUE   = "\u001B[34m" ;
    private static final String ANSI_PURPLE = "\u001B[35m" ;
    private static final String ANSI_CYAN   = "\u001B[36m" ;

    public static void main(String[] args)
    {
        String s = "";
        System.out.println( ANSI_PURPLE+ "------------------------------------------------");


        while (!number_of_accounts_entered) {
            System.out.println(ANSI_YELLOW + "На скольких человек необходимо разделить счёт?" + ANSI_BLUE);

            s = input.nextLine();
            if (s.matches("\\d{1,9}")) if (Integer.parseInt(s) > 1) {
                number_of_accounts_entered = true;
            }

            if (!number_of_accounts_entered) {
                System.out.println(ANSI_RED + "Ошибка! Вводите целое число больше единицы.");
            }
        }
        number_of_accounts = Integer.parseInt(s);
        High_Load_Calculator calculator = new High_Load_Calculator(number_of_accounts);


        while ( !items_entered )
        {
            System.out.println(ANSI_YELLOW + "\nВведите название товара и его стоимость в формате \""+
                               ANSI_GREEN  + "Товар = Стоимость"+
                               ANSI_YELLOW + "\" или \""+
                               ANSI_GREEN  + EXIT_COMMAND +
                               ANSI_YELLOW + "\" для расчёта");

            s = input.nextLine();
            if (s.equalsIgnoreCase(EXIT_COMMAND)) items_entered = true;
            else if (s.contains("=")) {
                String[] array_of_s = s.split("=");
                if (array_of_s.length == 2)
                    if (array_of_s[0].length() > 0 && array_of_s[1].length() > 0)
                        try {
                            double d = Double.parseDouble(array_of_s[1]);

                            if (d <= 0) System.out.println(ANSI_RED + "Отрицательное значение в стоимости товара!");
                            else {
                                array_of_s[0] = array_of_s[0].trim();
                                double total_cost = calculator.add_item(array_of_s[0], d);

                                System.out.printf(ANSI_BLUE + "товар \"%s\" добавлен ( итого: %.2f руб.)%n\n",
                                        array_of_s[0], total_cost);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(ANSI_RED + "Ошибка в стоимости товара!");
                        }
            }
        }

        System.out.println( ANSI_CYAN+ "\nДобавленные товары:\n"+ calculator.get_items_string());
        System.out.printf( "Каждый человек должен заплатить %.2f рубл", calculator.get_cost_per_account() );

        int n = (int) Math.floor( calculator.get_cost_per_account() );

        if( 5 <= n%100 && n%100 <= 20 ) System.out.println("ей"); else
        if(               n%10  ==  1 ) System.out.println("ь" ); else
        if( 2 <=  n%10 && n%10  <=  4 ) System.out.println("я" ); else
                                        System.out.println("ей");

        System.out.println( ANSI_PURPLE+ "------------------------------------------------");
    }
}