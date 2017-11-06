import weka.clusterers.*

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
    classlessClusterer.numClusters = emotions().size
    val classClusterer = EM()
    classClusterer.numClusters = emotions().size
    clustererExperiment(classlessClusterer, classClusterer, resultsDir)

    val classlessClustererFF = FarthestFirst()
    classlessClustererFF.numClusters = emotions().size
    val classClustererFF = FarthestFirst()
    classClustererFF.numClusters = emotions().size
    clustererExperiment(classlessClustererFF, classClustererFF, resultsDir)

    val classlessCobweb = Cobweb()
    val classCobweb = Cobweb()
    clustererExperiment(classlessCobweb, classCobweb, resultsDir)

}