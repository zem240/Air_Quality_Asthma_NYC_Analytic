import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CleanReducer extends Reducer<Text, Text, Text, Text> {

  @Override
  public void reduce(Text key, Iterable<Text> values, Context context)
      throws IOException, InterruptedException {
    // Looping over the rows received from the mapper
    for (Text value : values) {
      // Checking if the row has any null value
      if (!value.toString().contains("null")) {
        // Emitting the non-null row as output
        context.write(new Text(""), value);
      }
    }
  }
}

