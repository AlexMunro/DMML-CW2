import weka.core.Instances
import java.io.File

/**
 * Functions for loading the randomised arff files
 */

/**
 * Returns the main fer2017 randomised file
 */
fun fer2017(): Instances{
    return loadArff("fer2017/randomised/fer2017.arff")
}

fun ferEmotions() : List<Instances>{
    val randomisedDir = File("fer2017/randomised/")
    val files = randomisedDir.list{_, name -> name.endsWith(".arff") }
    return files.map{loadArff("$randomisedDir/$it")}
}

fun ferEmotion(emotion: String): Instances{
    return loadArff("fer2017/randomised/fer2017$emotion.arff")
}

/**
 * Returns the first 50% of instances from an emotion dataset
 */
fun ferEmotionHalved(emotion: String): Instances {
    val fullDataset = ferEmotion(emotion)
    return Instances(fullDataset, 0, fullDataset.numInstances() / 2)
}