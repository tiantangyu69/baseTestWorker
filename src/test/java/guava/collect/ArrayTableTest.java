package guava.collect;

import com.google.common.collect.ArrayTable;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bjlizhitao on 2017/4/11.
 */
public class ArrayTableTest {

    @Test
    public void test(){
        List<String> rows = new ArrayList();
        rows.add("row1");
        rows.add("row2");
        rows.add("row3");

        List<String> columns = new ArrayList<String>();
        columns.add("col1");
        columns.add("col2");
        columns.add("col3");

        ArrayTable<String, String, String> table = ArrayTable.create(rows, columns);
        table.put("row1", "col1", "1");

        System.out.println(table.get("row1", "col1"));
        System.out.println(table.get("row1", "col2"));
    }
}
