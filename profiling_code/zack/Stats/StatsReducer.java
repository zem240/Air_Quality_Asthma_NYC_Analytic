import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class StatsReducer extends Reducer<Text, DoubleWritable, Text, Text> {

  @Override
  public void reduce(Text key, Iterable<DoubleWritable> values, Context context)
      throws IOException, InterruptedException {
    List<Double> data = new ArrayList<>();
    for (DoubleWritable value : values) {
      data.add(value.get());
    }
    Collections.sort(data);

    double sum = 0.0;
    for (double number : data) {
      sum += number;
    }
    double mean = sum / data.size();

    double median;
    int middle = data.size() / 2;
    if (data.size() % 2 == 0) {
      median = (data.get(middle - 1) + data.get(middle)) / 2;
    } else {
      median = data.get(middle);
    }

    double mode = getMode(data);

    String result = "Mean: " + Double.toString(mean) + ", Median: " + Double.toString(median) + ", Mode: " + Double.toString(mode);
    context.write(key, new Text(result));
  }

  private double getMode(List<Double> data) {
    double mode = 0.0;
    int maxCount = 0;
    for (int i = 0; i < data.size(); i++) {
      double number = data.get(i);
      int count = 1;
      for (int j = i + 1; j < data.size(); j++) {
        if (data.get(j) == number) {
          count++;
        }
      }
      if (count > maxCount) {
        maxCount = count;
        mode = number;
      }
    }
    return mode;
  }
}

