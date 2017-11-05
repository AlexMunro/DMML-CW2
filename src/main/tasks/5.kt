
/**
For each fer2017EmotionX.arff file, run the naïve Bayes tool in Weka using only the first
50% of randomised instances, note the accuracy. Compare with your results in item (4).
Record the first 10 fields, in order of the absolute correlation value, for each emotion.
 */

fun main(args: Array<String>){
    val resultsDir = "results/5"
    emotions().forEach{
        val dataset = discretize(ferEmotionHalved(it))
        val results = runBayes(dataset)
        saveEvaluation("Naive bayes used on the first 50% of fer2017$it.arff",
                "$resultsDir/${it}Result.txt", results)
        saveCorrelations("fer2017/attributeRankings/fer2017$it.csv", rankAttributeCorrelation(dataset))
    }
}