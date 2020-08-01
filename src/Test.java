import sample.Database.DatabaseHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Test {
    public static void main(String[] args) throws SQLException {

        ArrayList<Integer> mahdi = new ArrayList<Integer>();

        HashMap<Integer , Integer> itmes = new HashMap<>();


        mahdi.add(20);
        mahdi.add(20);
        mahdi.add(20);
        mahdi.add(20);
        mahdi.add(5);
        mahdi.add(48);
        mahdi.add(48);
        mahdi.add(48);
        mahdi.add(25);
        mahdi.add(78);
        mahdi.add(78);
        mahdi.add(5);


for(int i =  0  ; i < mahdi.size() ; i++){
    if(!itmes.containsKey(mahdi.get(i))){
        if(found(mahdi.get(i) , mahdi) > 1){
            itmes.put(mahdi.get(i) , found(mahdi.get(i) , mahdi));
        }
    }
}

for(int i : itmes.keySet())
    System.out.println(i+"  "+itmes.get(i));


    }


    private static int found(int number  , ArrayList<Integer> arrayList){
        int  j = 0 ;

        for(int i  = 0 ; i < arrayList.size() ; i++){
            if(arrayList.get(i) == number)
                j++;
        }


        return j;
    }
}
