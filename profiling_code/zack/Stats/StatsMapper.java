import java.io.IOException;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class StatsMapper extends Mapper<Object, Text, Text, DoubleWritable> {

  private static final int[] COLUMNS = {1, 6, 8, 9};

  @Override
  public void map(Object key, Text value, Context context)
      throws IOException, InterruptedException {
    String[] fields = value.toString().split(",");
    for (int column : COLUMNS) {
      if (column >= fields.length) {
        continue;
      }
      try {
        Double number = Double.parseDouble(fields[column].trim());
        context.write(new Text(Integer.toString(column)), new DoubleWritable(number));
      } catch (NumberFormatException e) {
        // Ignore non-numeric values
      }
    }
  }
}

