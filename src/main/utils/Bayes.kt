import weka.classifiers.Evaluation
import weka.classifiers.bayes.NaiveBayes
import weka.core.Instances
import java.util.*

fun runBayes(dataset: Instances): Evaluation{
    val eval = Evaluation(dataset)
    val classifier = NaiveBayes()
    //classifier.useSupervisedDiscretization = true
    classifier.buildClassifier(dataset)
    eval.evaluateModel(classifier, dataset)
    return eval
}

fun crossValidateBayes(dataset: Instances): Evaluation{
    val eval = Evaluation(dataset)
    val classifier = NaiveBayes()
    eval.crossValidateModel(classifier, dataset, 10, Random(10))
    return eval
}