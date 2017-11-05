
/**
 * Run the naïve Bayes tool in Weka on the resulting version of fer2017.arff. To be able to do
this, you may need to apply several Weka “Filters”. Explain the reason for choosing and
using these filters. Once you can run the algorithm, record, compare and analyse the
classifier’s accuracy on different classes.
 */

fun main(args: Array<String>){
    val resultsDir = "results/4"
    val dataset = discretize(fer2017())
    val eval = runBayes(dataset)
    //saveEvaluation("Bayesian classifier on fer2017.arff", "$resultsDir/classifierResult.txt", eval)
    //saveCorrelations("fer2017/attributeRankings/fer2017.csv", rankAttributeCorrelation(dataset))
//            val rankedDataset = rankAttrs(dataset)
}