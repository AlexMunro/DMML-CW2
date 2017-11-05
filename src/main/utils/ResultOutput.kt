import weka.attributeSelection.Ranker
import weka.classifiers.Evaluation
import weka.clusterers.ClusterEvaluation
import java.io.File

fun saveEvaluation(title: String, outputFile: String, eval: Evaluation) {
    val writer = File(outputFile).bufferedWriter()
    writer.newLine()
    writer.write(title)
    writer.newLine()
    writer.write(eval.toSummaryString(true))
    writer.newLine()
    writer.write(eval.toClassDetailsString())
    writer.newLine()
    writer.write(eval.toMatrixString())
    writer.write("\n\n\n")
    writer.newLine()
    writer.close()
}

fun saveClustererEvaluation(title: String, outputFile: String, eval: ClusterEvaluation){
    val writer = File(outputFile).bufferedWriter()
    writer.newLine()
    writer.write(title)
    writer.newLine()
    writer.write(eval.clusterResultsToString())
    writer.write("\n\n\n")
    writer.newLine()
    writer.close()
}

/**
 * Save sorted attribute correlations with the class attribute in csv format
 */
fun saveCorrelations(outputFile: String, correlations: List<AttributeCorrelation>){
    val writer = File(outputFile).bufferedWriter()

    val ranker = Ranker()

    correlations.forEach{
        writer.write("${it.attribute.name()},${it.correlation}\n")
    }
}
