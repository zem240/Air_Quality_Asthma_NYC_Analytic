import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CsvMapper extends Mapper<LongWritable, Text, NullWritable, Text> {

    private static final String COMMA_DELIMITER_REGEX = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";

    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] columns = value.toString().split(COMMA_DELIMITER_REGEX);
        columns[4] = columns[4].replace(",", "");
        String modifiedRow = String.join(",", columns);
        context.write(NullWritable.get(), new Text(modifiedRow));
    }
}

