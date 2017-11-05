import weka.core.Instances

/**
6. Using the information about the top correlating features obtained in item (5), transform the
full data set fer2017.arff in such a way as to keep the following attributes:
6.1. Using only the top 2 non-class fields from each fer2017EmotionX.arff.
6.2. Using only the top 5 non-class fields from each fer2017EmotionX.arff.
6.3. Using only the top 10 non-class fields from each fer2017EmotionX.arff.
6.4. You will have three data sets, with 14, 35 and 70 non-class attributes respectively. Repeat
experiment described in item (4) on these three data sets.
 */

/**
 * Goes backwards
 */
fun pruneDataset(dataset: Instances, retainAttrs: Set<String>): Instances{
    val clonedDataset = Instances(dataset)
    // deleting all non-class attributes not included in the top n
    (clonedDataset.numAttributes() -1 downTo 1)
            .filterNot { retainAttrs.contains(clonedDataset.attribute(it).name()) }
            .forEach { clonedDataset.deleteAttributeAt(it) }
    return clonedDataset
}

fun main(args: Array<String>){
    val top2Attrs = getTopEmotionAttrs(2)
    val top5Attrs = getTopEmotionAttrs(5)
    val top10Attrs = getTopEmotionAttrs(10)

    val resultDir = "results/6"

    val fullDataset = fer2017()

    val top10Set = pruneDataset(fer2017(), top10Attrs)
    val top5Set = pruneDataset(top10Set, top5Attrs)
    val top2Set = pruneDataset(top5Set, top2Attrs)

    val top2Eval = runBayes(top2Set)
    val top5Eval = runBayes(top5Set)
    val top10Eval = runBayes(top10Set)

    println("Running classifier for 2")
    saveEvaluation("Bayesian classifier run on top 2 attributes per emotion", "$resultDir/2", top2Eval)
    println("Running classifier for 5")
    saveEvaluation("Bayesian classifier run on top 5 attributes per emotion", "$resultDir/5", top5Eval)
    println("Running classifier for 10")
    saveEvaluation("Bayesian classifier run on top 10 attributes per emotion", "$resultDir/10", top10Eval)

}