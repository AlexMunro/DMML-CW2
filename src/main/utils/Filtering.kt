/**
 * Util functions for applying discretization to datasets
 */

import weka.core.Instances
import weka.filters.Filter
import weka.filters.supervised.attribute.Discretize
import java.io.File
import java.nio.file.Files

fun discretize(dataset: Instances): Instances {

    val discFilter = Discretize()
    discFilter.setInputFormat(dataset)
    val result = Filter.useFilter(dataset, discFilter)
    saveArff("fer2017/discretized/${dataset.relationName()}.csv", result)
    return result
}