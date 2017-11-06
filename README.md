# DMML-CW2
My entry for Coursework 2 of the 2017 Data Mining and Machine Learning Class, involving the use of Bayes networks and clustering with Weka. The experiments performed for the report can be repeated by running the `main` functions in each of the task classes located in src/main/tasks.


## Requirements
This is a Kotlin project, so you will need to use a compatible IDE or the Kotlin compiler to run the various files - please see <http://kotlinlang.org/> for details. Dependency management is done through Gradle but the only requirement is Weka so feel free to add it in another way if you prefer. If you want Gradle to handle it, run
`gradle build`.

<b>You will need to separately download the [fer2017 archive](https://drive.google.com/open?id=110qU6uMiBk4nNp9N8EdsNP7tIhHtLlPb), unzip it and place it in the top level.</b> This was too large to upload to GitHub due to the large file sizes, but the archive contains all the original data as well as intermediates generated for the tasks. Alternately, you can download a copy of only the CSVs and you should be able to repeat the entire process (although folders may need to be recreated as shown below).

## Structure
```
|
|---results - contains per-task results used in the report
|   |   1
|   |   2..
|
|---fer2017 - extract the zip file here!
|   |---attributeRankings - per dataset orderings of attributes by correlation to class
|   |---discretized - discretized versions of datasets
|   |---randomised - datasets which have been ordered randomly
|   |---curated - datasets that have had attributes filtered based on class correlation
|   supplied csv files
|   derived arff files
|
|---src
|   |---main
|       |---tasks - per task files with main functions that can be run to repeat the experiments
|       |       1.kt
|       |       2.kt...
|       |---utils - helper functions for the above tasks grouped logically into separate files
```