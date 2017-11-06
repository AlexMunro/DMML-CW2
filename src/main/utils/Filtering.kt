/**
 * Util functions for applying discretization to datasets
 */

import weka.core.Instances
import weka.filters.Filter
import weka.filters.supervised.attribute.Discretize
import weka.filters.unsupervised.attribute.Remove

fun discretize(dataset: Instances): Instances {

    val discFilter = Discretize()
    discFilter.setInputFormat(dataset)
    val result = Filter.useFilter(dataset, discFilter)
    saveArff("fer2017/discretized/${dataset.relationName()}.csv", result)
    return result
}

/**
 * Filters out all attributes from a dataset excluding those mentioned in attrsToKeep
 */
fun filterAttrs(dataset: Instances, attrsToKeep: IntArray): Instances{
    val removeFilter = Remove()
    removeFilter.invertSelection = true
    removeFilter.setAttributeIndicesArray(attrsToKeep)
    removeFilter.setInputFormat(dataset)
    return Filter.useFilter(dataset, removeFilter)
}
