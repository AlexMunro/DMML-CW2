import weka.classifiers.Evaluation
import weka.clusterers.ClusterEvaluation
import weka.clusterers.Clusterer
import weka.clusterers.EM
import weka.clusterers.SimpleKMeans
import weka.core.Instances
import weka.gui.explorer.ClustererAssignmentsPlotInstances
import weka.gui.explorer.ClustererPanel
import weka.gui.visualize.VisualizePanel
import java.awt.BorderLayout
import java.text.SimpleDateFormat
import java.util.*
import javax.swing.JFrame

/**
9. Cluster the data sets fer2017.arff, fer2017EmotionX.arff (apply required filters and/or
attribute selections if needed):
9.1. first excluding the class attribute. This will emulate the situation when the learning of digit
shapes is performed in unsupervised manner.
9.2. then including the class attribute. This will emulate the general data analysis scenario.
9.3. Make conclusions about the results, compare with classification results obtained in items
(4-8).
 */

fun evalClusterer(dataset: Instances, clusterer: Clusterer): ClusterEvaluation {
    val eval = ClusterEvaluation()
    eval.setClusterer(clusterer)
    eval.evaluateClusterer(dataset)
    return eval
}

// This function adapted from https://weka.wikispaces.com/Visualizing+cluster+assignments
fun visualise(dataset: Instances, clusterer: Clusterer, eval: ClusterEvaluation){

    val name = SimpleDateFormat("HH:mm:ss - ").format(Date()) + clusterer.javaClass.name

    val plotInstances = ClustererAssignmentsPlotInstances()
    plotInstances.clusterer = clusterer
    plotInstances.instances = dataset
    plotInstances.clusterEvaluation = eval
    plotInstances.setUp()
    val vp = VisualizePanel()
    vp.name = name
    vp.addPlot(plotInstances.getPlotData(clusterer.javaClass.name))

    val jf = JFrame("Weka Clusterer Visualize: " + vp.name)
    jf.defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE
    jf.setSize(500, 400)
    jf.contentPane.layout = BorderLayout()
    jf.contentPane.add(vp, BorderLayout.CENTER)
    jf.isVisible = true
}

fun main(args: Array<String>){
    val resultsDir = "results/9"

    val dataset = fer2017()

    // 9.1 first excluding the class attribute

    val classlessDataset = Instances(dataset)

    classlessDataset.setClassIndex(-1)
    classlessDataset.deleteAttributeAt(0)

    val clusterer = SimpleKMeans()
    val em_Clusterer = EM() // maybe later?
    clusterer.numClusters = emotions().size
    clusterer.buildClusterer(classlessDataset)

    val classlessEval = evalClusterer(classlessDataset, clusterer)
    saveClustererEvaluation("Classless evaluation", "$resultsDir/classlessEval.txt", classlessEval)
    visualise(dataset, clusterer, eval)
    // 9.2 then including the class attribute



}