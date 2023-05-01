
To run our code, first we will explain how to run each of our data cleans. In /etl_code you will find our subdirectories. /etl_code/zack handles the air quality dataset. /etl_code/sabina handles the asthma emergency department visit rates dataset.
     For Sabina's code, first navigate to the etl_code directory and then to the sabina directory. There, you can find the original uncleaned data source called Asthma_ED_visits.csv. If you then navigate to the MR_Job2 folder, you can find all of the java, class, and jar files necessary for running the first data cleaning conducted in MapReduce. You can run this code with the original dataset, Asthma_ED_visits.csv. Then, I converted the output to csv format, called cleaned.csv. I have shared access with the graders, so they should be able to access these files (if there is an issue, please let me know immediately). After that, you can go into the profiling_code directory, where you can find the initial_code_analysis file within the sabina directory which contains all of the commands for the first import to Hive. Note that this file includes some profiling of the data as well. This code can be copy and pasted into Hive as this is what we did for our project or executed on Hive if the graders have an alternative way. It creates a table called first_code_drop. After that, you can exit out of this directory and return back to the etl_code directory where you will open sabina's directory to find the Code_cleaning file. Again, this code can be copy and pasted into Hive as this is what we did for our project or executed on Hive if the graders have an alternative way. It creates a table called code_cleaning, which is the final Hive table I have before our join. I also include a screenshot called code_cleaning_tableOutput of my results from this clean. Also, there is a readME in this directory as well called README_final_code_cleaning_before_join.txt that provides further documentation.
     For Zack's code, order of clean jobs are by the numbers in the folders, so "Clean1" was our first clean on the original dataset (Air_Quality.csv) which outputted "ZACK_CLEAN_1.csv", "Clean2", cleaned from the cleaned csv from "ZACK_CLEAN_1.csv" and outputted "ZACK_CLEAN_2.csv", then lastly after "Clean3" we have our final csv which is "ZACK_CLEAN_FINAL.csv". Overall, the cleaning is done by MapReduce jobs that get rid of rows with null values, pollutants that we don't need to analyze, and reformats the dates for easy joining in Hive. Unlike Sabina's cleaning, there is no cleaning done in Hive, so you can upload Zack's final cleaned csv to Hive. The next step will tell you where to run this to upload Zack's final cleaned csv to Hive. 

Then, you can navigate to data_ingest to run the join tables of both of our final Hive tables. The Hive_table_creation_for_join.txt file holds all of the commands for our Hive tables prior to the join and the join itself. First, run zack's create external table command that creates zack_working_dataset to upload his final cleaned csv to Hive. Then, only run the create external table command that joins Sabina's code_cleaning table and Zack's zack_working_dataset instead of running the entire file OR ELSE you will run it an error because those other tables should have already been created.

Now, go to /ana_code and you will find our analytics. "analytic_1_source_code.txt" and "analytic_2_source_code.txt" get run off of the same table called "analytic_1". You can run each text file again by copy and pasting the code or executing the files themselves. Please do this in order, however. First run the code in analytic_1_source_code.txt, then the code in analytic_2_source_code.txt and finally, the code in analytic_3_source_code.txt.

In the screenshots subdirectory, you can find screenshots of all analytics running and their outputs for every step as well. They are labeled in order, so it is simple to follow.

In the /profiling_code directory, you can also find some profiling we performed on our join table in the analytic3_tables_profiling.txt file which you can either copy and paste the commands from or run the file. You can also find some profiling we performed on our tables that we created to perform analytic3 in the Profiling_of_Join_Table_Commands.txt file, which you can either copy and paste the commands from or run the file. You can find the screenshots of both of those profilings, called Profiling_After_Join and Profiling_Analytic3. You will also notice there are subdirectories for each team member.
     In Sabina's subdirectory, you can find the initial_code_analysis which should have been run (the CREATE EXTERNAL TABLE first_code_drop command) as mentioned previously using that cleaned.csv first produced by MapReduce (location is under hdfs://nyu-dataproc-m/user/sp5642/HW7/cleaned_data/), but note that this file also includes profiling code that analyzes distinct values as well as statistical analyses. Note access was shared to this in a previous homework. Then, you can also find MR_Job1 which counts the number of records in a dataset. You can do this on the original dataset that you can also find in this subdirectory titled Asthma_ED_visits.csv and then after the initial MapReduce clean of MR_Job2 in /etl_code/sabina. MR_Job1 provides all of the necessary jar, class, and java files you need for execution. This was what was expected for HW7, where access was provided to the graders in HDFS as well. I also include screenshots of MR_Job1 run on the original dataset which is called MR_Job1_Run as well as its run on the cleaned.csv generated from original MapReduce cleaning, of which the screenshot is called Rerun_of_MRJOB1_on_cleaned_data. Other than that, in the screenshots subdirectory in this folder, you can find 5 screenshots that correspond to the profiling conducted in the Initial_Code_Analysis text file consisting of Hive commands for creating the first_code_drop table (to recall, the table before the final table used for the join) as well as some profiling / getting to know the table code.
     In Zack's subdirectory, you will find a MapReduce job. Stats will find the Mean, Median, and Mode of the final cleaned dataset. This profiling was done on the second csv labeled as "ZACK_CLEAN_2.csv". It is also in the subdirectory. There are screenshots of the result from the Stats job labeled as "Mean Median and Mode". "Distinct Commands" will run off the previously created "zack_working_dataset" in Hive. They will display our distinct values. There are also screenshots of results from the Distinct Commands in the folder.

We also include a test_code directory where you can find some test code we used. We did not use this for any analysis, so there is no need to run it from your end or you may run into errors. There is an analytic we decided against using, an incorrect group by that resulted in incorrect / NULL results, and a failed select statement when we were attempting to clean up the joined_table further.

Please note that we have also contacted HPC regarding shared access and were informed that we can grant Professor Ann and the grader's Task read-only access for my Hive tables. 
These are the commands that were run for your reference,
SABINA'S:
hdfs dfs -setfacl -R -m user:adm209_nyu_edu:r-x /user/hive/warehouse/sp5642_nyu_edu.db
hdfs dfs -setfacl -R -m user:cr3152_nyu_edu:r-x /user/hive/warehouse/sp5642_nyu_edu.db
hdfs dfs -setfacl -R -m user:cl6405_nyu_edu:r-x /user/hive/warehouse/sp5642_nyu_edu.db
ZACK's:
hdfs dfs -setfacl -R -m user:adm209_nyu_edu:r-x /user/hive/warehouse/zem240_nyu_edu.db
hdfs dfs -setfacl -R -m user:cr3152_nyu_edu:r-x /user/hive/warehouse/zem240_nyu_edu.db
hdfs dfs -setfacl -R -m user:cl6405_nyu_edu:r-x /user/hive/warehouse/zem240_nyu_edu.db

Zack's csv files are also shared in HDFS. They are labeled the same as in the clean folder:

hdfs dfs -setfacl -R -m user:adm209_nyu_edu:r-x submit/ZACK_CLEAN_1.csv
hdfs dfs -setfacl -R -m user:adm209_nyu_edu:r-x submit/ZACK_CLEAN_2.csv
hdfs dfs -setfacl -R -m user:adm209_nyu_edu:r-x submit/ZACK_CLEAN_FINAL.csv
hdfs dfs -setfacl -R -m user:adm209_nyu_edu:r-x submit/Air_Quality.csv

hdfs dfs -setfacl -R -m user:cr3152_nyu_edu:r-x submit/ZACK_CLEAN_1.csv
hdfs dfs -setfacl -R -m user:cr3152_nyu_edu:r-x submit/ZACK_CLEAN_2.csv
hdfs dfs -setfacl -R -m user:cr3152_nyu_edu:r-x submit/ZACK_CLEAN_FINAL.csv
hdfs dfs -setfacl -R -m user:cr3152_nyu_edu:r-x submit/Air_Quality.csv

hdfs dfs -setfacl -R -m user:cl6405_nyu_edu:r-x submit/ZACK_CLEAN_1.csv
hdfs dfs -setfacl -R -m user:cl6405_nyu_edu:r-x submit/ZACK_CLEAN_2.csv
hdfs dfs -setfacl -R -m user:cl6405_nyu_edu:r-x submit/ZACK_CLEAN_FINAL.csv
hdfs dfs -setfacl -R -m user:cl6405_nyu_edu:r-x submit/Air_Quality.csv

Sabina's csv files are shared in HDFS. You can find them in the HW7 directory.
hdfs dfs -setfacl -R -m user:adm209_nyu_edu:rwx HW7
hdfs dfs -setfacl -R -m user:cl6405_nyu_edu:rwx HW7
hdfs dfs -setfacl -R -m user:cr3152_nyu_edu:rwx HW7

That is all! Thank you. We enjoyed this project and put a lot of work into it. Please let us know in case anything is missing / something else you would like to see. 
