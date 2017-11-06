import weka.clusterers.EM
import weka.core.Instances

/**
10. Try different clustering algorithms. Try also to vary the number of clusters manually and
then use Weka’s facilities to compute the optimal number of clusters. Explore various
options in Weka that help to improve clustering results. Use the visualisation tool for
clustering to analyse the results. Make conclusions on the obtained improvements to
clustering results.
 */

fun main(args: Array<String>){
    val resultsDir = "results/10"

    val classlessClusterer = EM()

    // 9.1 first excluding the class attribute
    val dataset = fer2017()

    val classlessDataset = Instances(dataset)
    classlessDataset.setClassIndex(-1)
    classlessDataset.deleteAttributeAt(0)

    classlessClusterer.numClusters = emotions().size
    classlessClusterer.buildClusterer(classlessDataset)

    val classlessEval = evalClusterer(classlessDataset, classlessClusterer)
    saveClustererEvaluation("Classless evaluation", "$resultsDir/classlessEval.txt", classlessEval)

    // 9.2 then including the class attribute
    val classClusterer = EM()
    classClusterer.numClusters = emotions().size
    classClusterer.buildClusterer(dataset)
    classlessDataset.setClassIndex(-1) // We're still including the class but not highlighting that it is the class

    val classEval = evalClusterer(dataset, classClusterer)
    saveClustererEvaluation("Class evaluation", "$resultsDir/classEval.txt", classEval)
}