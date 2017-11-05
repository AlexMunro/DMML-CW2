import java.io.File

/**
 * Performs the data preparation as specified in question 1.
 *
 * Notes:
 * Each pixel of the 48*48 array is saved as a separate attribute.
 * In fer2017.csv, the int specifying emotion is converted to a nominal attribute over the emotion names.
 * In fer2017<emotion>.csv the 1/0 attribute specifying whether the emotion is present is changed to {true,false}
 */

fun main(args: Array<String>){
    val csvDir = File("fer2017/")
    // fer2017.csv is different from the emotion specific files, so handling it separately
    println("Converting fer2017.csv")
    val reader = File("$csvDir/fer2017.csv").bufferedReader()
    val writer = File("$csvDir/fer2017.arff").bufferedWriter()

    val lines = reader.lines().skip(1)

    // Header
    writer.write("@relation fer2017")
    writer.newLine()
    writer.write("@attribute emotion\t{Angry, Disgust, Fear, Happy, Sad, Surprise, Neutral}")

    val emotionMap: Map<Int, String> = mapOf(0 to "Angry", 1 to "Disgust", 2 to "Fear", 3 to "Happy", 4 to "Sad", 5 to "Surprise", 6 to "Neutral")

    for (i in 0 until (48*48)) {
        writer.newLine()
        writer.write("@attribute pixel$i\tNUMERIC")
    }

    writer.newLine()
    writer.write("@data")

    for (line in lines) {
        writer.newLine()
        val attrs = line.split(',')
        writer.write(emotionMap[attrs[0].toInt()] + "," + attrs[1].replace(" ", ","))
    }

    reader.close()
    writer.close()

    // For each csv file except fer2017.csv, create a new corresponding arff file in the same directory
    for (emotion in emotions()) {
        println("Converting fer2017$emotion.csv")
        // Doing this manually because I have no idea how weka handles array type attributes
        val source = File("$csvDir/fer2017$emotion.csv")
        val lines = source.bufferedReader().lines().skip(1)
        //  discard the first line, we're doing the headers manually
        val writer = File("$csvDir/fer2017$emotion.arff").bufferedWriter()
        // Dealing with the header
        writer.write("% Auto-generated from fer2017$emotion.csv by running Q1.kt")
        writer.newLine()
        writer.write("@relation fer2017$emotion")
        writer.newLine()
        writer.write("@attribute emotion\t{$emotion,not_$emotion}")
        for (i in 0 until (48 * 48)) {
            writer.newLine()
            writer.write("@attribute pixel$i\tNUMERIC")
        }
        writer.newLine()
        writer.write("@data")

        for (line in lines) {
            writer.newLine()
            val attrs = line.split(',')
            val emotionStr = if (attrs[0] == "1") emotion else "not_$emotion"
            writer.write("$emotionStr," + attrs[1].replace(" ", ","))
        }

        // Closing file handles at the end
        writer.close()

    }

    println("Finished converting all CSV files")
}