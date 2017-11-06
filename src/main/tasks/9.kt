import weka.clusterers.SimpleKMeans

/**
9. Cluster the data sets fer2017.arff, fer2017EmotionX.arff (apply required filters and/or
attribute selections if needed):
9.1. first excluding the class attribute. This will emulate the situation when the learning of digit
shapes is performed in unsupervised manner.
9.2. then including the class attribute. This will emulate the general data analysis scenario.
9.3. Make conclusions about the results, compare with classification results obtained in items
(4-8).
 */


fun main(args: Array<String>){
    val resultsDir = "results/9"
    val classlessClusterer = SimpleKMeans()
    classlessClusterer.numClusters = emotions().size
    val classClusterer = SimpleKMeans()
    classClusterer.numClusters = emotions().size
    clustererExperiment(classlessClusterer, classClusterer, resultsDir)
}