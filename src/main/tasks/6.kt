import weka.core.Instances
import java.util.*

/**
6. Using the information about the top correlating features obtained in item (5), transform the
full data set fer2017.arff in such a way as to keep the following attributes:
6.1. Using only the top 2 non-class fields from each fer2017EmotionX.arff.
6.2. Using only the top 5 non-class fields from each fer2017EmotionX.arff.
6.3. Using only the top 10 non-class fields from each fer2017EmotionX.arff.
6.4. You will have three data sets, with 14, 35 and 70 non-class attributes respectively. Repeat
experiment described in item (4) on these three data sets.
 */


fun main(args: Array<String>){
    val top2Attrs = getTopEmotionAttrs(2)
    println(Arrays.toString(top2Attrs))
    val top5Attrs = getTopEmotionAttrs(5)
    println(Arrays.toString(top5Attrs))
    val top10Attrs = getTopEmotionAttrs(10)
    println(Arrays.toString(top10Attrs))

    val resultDir = "results/6"

    val top10Set = filterAttrs(fer2017(), top10Attrs)
    val top5Set = filterAttrs(fer2017(), top5Attrs)
    val top2Set = filterAttrs(fer2017(), top2Attrs)

    println("Top 10 set has ${top10Set.numAttributes()}")
    println("Top 5 set has ${top5Set.numAttributes()}")
    println("Top 2 set has ${top2Set.numAttributes()}")

    println("Running classifier for 2")
    val top2Eval = runBayes(top2Set)
    saveEvaluation("Bayesian classifier run on top 2 attributes per emotion", "$resultDir/top2.txt", top2Eval)
    println("Running classifier for 5")
    val top5Eval = runBayes(top5Set)
    saveEvaluation("Bayesian classifier run on top 5 attributes per emotion", "$resultDir/top5.txt", top5Eval)
    println("Running classifier for 10")
    val top10Eval = runBayes(top10Set)
    saveEvaluation("Bayesian classifier run on top 10 attributes per emotion", "$resultDir/top10.txt", top10Eval)

}