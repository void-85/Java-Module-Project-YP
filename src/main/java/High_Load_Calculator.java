public class High_Load_Calculator
{
    private int    accounts   = 0   ;
    private double total_cost = 0.0 ;

    private StringBuilder items = new StringBuilder();

    High_Load_Calculator( int n ){ accounts = n; }

    public double add_item( String item, double cost )
    {
        items.append(item).append("\n");
        total_cost += cost;

        return total_cost;
    }

    public String get_items_string     (){ return items.toString();      }
    public double get_cost_per_account (){ return total_cost / accounts; }
}
