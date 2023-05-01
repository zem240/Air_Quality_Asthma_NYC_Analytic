import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CleanMapper extends Mapper<LongWritable, Text, Text, Text> {

  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
    // Splitting the CSV row into columns using comma as delimiter
    String[] columns = value.toString().split(",");
    
    // Checking if column 2 contains any of the specified strings
    boolean containsPM25 = columns[2].contains("PM2.5");
    boolean containsPM10 = columns[2].contains("PM10");
    boolean containsO3 = columns[2].contains("O3");
    boolean containsNO2 = columns[2].contains("NO2");
    boolean containsSO2 = columns[2].contains("SO2");
    
    // Replacing the value of column 2 if it contains any of the specified strings
    if (containsPM25) {
      columns[2] = "PM2.5";
    } else if (containsPM10) {
      columns[2] = "PM10";
    } else if (containsO3) {
      columns[2] = "O3";
    } else if (containsNO2) {
      columns[2] = "NO2";
    } else if (containsSO2) {
      columns[2] = "SO2";
    }
    
    // Joining the columns back to a comma-separated string
    String cleanedRow = String.join(",", columns);
    
    // Emitting the cleaned row as output
    context.write(new Text(""), new Text(cleanedRow));
  }
}

