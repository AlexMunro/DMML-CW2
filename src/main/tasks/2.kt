import java.io.File
import java.util.*

fun createRandomisedCopy(source: String, output: String){
    println("Creating randomised copy of $source")
    val instances = loadArff(source)
    instances.randomize(Random())
    saveArff(output, instances)
}

fun main(args: Array<String>){
    val csvDir = File("fer2017/")
    for (f in csvDir.list{ _, name -> name.endsWith(".arff")}){
        createRandomisedCopy("$csvDir/$f", "$csvDir/randomised/$f")
    }
    println("Randomised copying completed")
}