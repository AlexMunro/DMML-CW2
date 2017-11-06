import weka.clusterers.ClusterEvaluation
import weka.clusterers.Clusterer
import weka.clusterers.SimpleKMeans
import weka.core.Instances

fun evalClusterer(dataset: Instances, clusterer: Clusterer): ClusterEvaluation {
    val eval = ClusterEvaluation()

    eval.setClusterer(clusterer)
    eval.evaluateClusterer(dataset)
    return eval
}

/**
 * Evaluates the performance
 */
fun clustererExperiment(classlessClusterer: Clusterer, classClusterer: Clusterer, resultsDir: String){

    // 9.1 first excluding the class attribute
    val dataset = filterAttrs(fer2017(), getTopEmotionAttrs(10))
    val testSet = Instances(dataset) // saving a clone which still has class set
    val classlessDataset = Instances(dataset)

    classlessDataset.setClassIndex(-1)
    classlessDataset.deleteAttributeAt(0)
    dataset.setClassIndex(-1)
    classlessClusterer.buildClusterer(classlessDataset)

    val classlessEval = evalClusterer(testSet, classlessClusterer)
    saveClustererEvaluation("Classless evaluation", "$resultsDir/${classClusterer.javaClass.name}classlessEval.txt", classlessEval)
    // 9.2 then including the class attribute

    classClusterer.buildClusterer(dataset)
    val classEval = evalClusterer(testSet, classClusterer)
    saveClustererEvaluation("Class evaluation", "$resultsDir/${classClusterer.javaClass.name}classEval.txt", classEval)
}