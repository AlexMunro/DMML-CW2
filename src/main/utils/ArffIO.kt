/**
 * Util functions for ARFF file handling
 */
import weka.core.Instances
import weka.core.converters.ArffLoader
import weka.core.converters.ArffSaver
import java.io.File

/**
 * Loads an arff datamodel from file
 * @param source relative path of the source arff file
 * @return Weka Instances file of the dataset to load
 */
fun loadArff(source: String): Instances {
    val loader = ArffLoader()
    loader.setFile(File(source))
    val dataSet = loader.dataSet
    dataSet.setClassIndex(0)
    return dataSet
}

/**
 * Saves an arff model to file
 * @param outFile relative path of destination file (including extension)
 * @param dataset instances file
 */
fun saveArff(outFile: String, dataset: Instances){
    val saver = ArffSaver()
    saver.setFile(File(outFile))
    saver.instances = dataset
    saver.writeBatch()
}

fun loadDiscretized(name: String): Instances?{
    if (File(name).exists())
        return loadArff("discretized/$name.arff")
    return null
}