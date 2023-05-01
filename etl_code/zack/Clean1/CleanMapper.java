import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.*;

public class CleanMapper extends Mapper<LongWritable, Text, LongWritable, Text> {

    private static final String[] INDICATORS = {"PM2.5", "PM10", "O3", "NO2", "SO2"};

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] columns = splitCSVLine(value.toString());

        String indicator = columns[2];
        boolean containsIndicator = false;
        for (String i : INDICATORS) {
            if (indicator.contains(i)) {
                containsIndicator = true;
                break;
            }
        }

        if (containsIndicator) {
            String startDate = columns[9];
            int year = Integer.parseInt(startDate.substring(startDate.length() - 4));
            if (year >= 2005 && year <= 2018) {
                columns[8] = Integer.toString(year);
		columns[0] = columns[0].substring(columns[0].indexOf(' ') + 1);
                String[] cleanedColumns = new String[]{columns[0], columns[1], columns[2], columns[3], columns[4], columns[5], columns[6], columns[7], columns[8]};
                String cleanedRow = String.join(",", cleanedColumns);
                context.write(key, new Text(cleanedRow));
            }
        }
    }

    private static String[] splitCSVLine(String line) {
    List<String> columnsList = new ArrayList<String>();
    int start = 0;
    boolean inQuotes = false;
    for (int i = 0; i < line.length(); i++) {
        char c = line.charAt(i);
        if (c == ',') {
            if (!inQuotes) {
                String column = line.substring(start, i).trim();
                columnsList.add(column);
                start = i + 1;
            }
        } else if (c == '\"') {
            inQuotes = !inQuotes;
        }
    }
    columnsList.add(line.substring(start).trim());
    return columnsList.toArray(new String[0]);
}

}

