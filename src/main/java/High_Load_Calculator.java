public class High_Load_Calculator
{

    private Integer accounts   = 0   ;
    private Double  total_cost = 0.0 ;
    private String  items      = ""  ;



    High_Load_Calculator( Integer n ){ accounts = n; }



    public Double add_item( String item, Double cost )
    {
        items      += item + "\n" ;
        total_cost += cost        ;

        return total_cost;
    }

    public String get_items_string     (){ return items;                 }
    public Double get_cost_per_account (){ return total_cost / accounts; }
}
