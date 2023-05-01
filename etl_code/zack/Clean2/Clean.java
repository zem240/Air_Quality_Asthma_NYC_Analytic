import java.io.IOException;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Clean {

  public static void main(String[] args)
      throws IOException, ClassNotFoundException, InterruptedException {
    if (args.length != 2) {
      System.err.println("Usage: Clean <input path> <output path>");
      System.exit(-1);
    }
    
    // Creating a Hadoop job and setting the main class
    Job job = Job.getInstance();
    job.setJarByClass(Clean.class);
    job.setJobName("Clean");
    
    // Setting the mapper and reducer classes
    job.setMapperClass(CleanMapper.class);
    job.setReducerClass(CleanReducer.class);
    
    // Setting the output key and value classes
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);
    
    // Setting the input and output paths
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    
    // Running the job and waiting for its completion
    boolean success = job.waitForCompletion(true);
    System.exit(success ? 0 : 1);
  }
}

