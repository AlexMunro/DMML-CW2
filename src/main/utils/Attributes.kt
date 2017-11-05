import weka.attributeSelection.AttributeSelection
import weka.attributeSelection.CorrelationAttributeEval
import weka.attributeSelection.Ranker
import weka.core.Attribute
import weka.core.Instances
import java.io.File

data class AttributeCorrelation(val attribute: Attribute, val correlation: Double)

fun rankAttributeCorrelation(dataset: Instances): List<AttributeCorrelation>{

    val attributeCorrelations = ArrayList<AttributeCorrelation>()
    attributeCorrelations.ensureCapacity(48*48)
    val evaluator = CorrelationAttributeEval()
    evaluator.buildEvaluator(dataset)

    (0 until 48 * 48).mapTo(attributeCorrelations) {
        AttributeCorrelation(dataset.attribute(it + 1), evaluator.evaluateAttribute(it+ 1)) // Remember to skip the class attribute
    }

    return attributeCorrelations.sortedWith(compareBy{it.correlation}).reversed()

}

fun getTopEmotionAttrs(n: Int): Set<String>{
    val attributes: MutableSet<String> = mutableSetOf()
    emotions().forEach{
        val input = File("fer2017/attributeRankings/fer2017$it.csv").bufferedReader()
        (0 until n).forEach{attributes.add(input.readLine().split(",")[0]) }
    }
    return attributes
}

fun rankAttrs(dataset: Instances, n: Int): Instances{
    val eval = CorrelationAttributeEval()
    val selection = AttributeSelection()
    val searcher = Ranker()
    selection.setEvaluator(eval)
    selection.setSearch(searcher)
    println(selection.CVResultsString())
    selection.SelectAttributes(dataset)
    return selection.reduceDimensionality(dataset)
}
