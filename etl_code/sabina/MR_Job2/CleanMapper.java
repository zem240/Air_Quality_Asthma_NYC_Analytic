import java.io.IOException;
import java.io.StringReader;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class CleanMapper extends Mapper<LongWritable, Text, Text, Text> {

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        try {
            CSVReader reader = new CSVReader(new StringReader(value.toString()));
            String[] line = reader.readNext();

            // line with 2015 is missing value in Number column so let's skip it
            if (line[0].equals("2015")) {
                return;
            }

            String time = line[0];
            String geoType = line[1];
            String geoID = line[2];
            String geoRank = line[3];
            String geography= line[4];
            String age_adjusted = line[5];
            String annual_rate = line[6];
            String number = line[7].replaceAll(",","");

            // remove geo rank column
            context.write(new Text(time+","+geoType+","+geoID+","+geography+","+age_adjusted+","+annual_rate+","+number), new Text()); 
        } catch (CsvValidationException e) {
            e.printStackTrace();
            return;
        }
    }
}

