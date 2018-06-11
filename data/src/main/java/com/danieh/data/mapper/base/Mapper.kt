package com.danieh.data.mapper.base

/**
 * Created by danieh
 */
abstract class Mapper<From, To> {

    abstract fun map(from: From): To

    fun map(list: List<From>?): List<To> {
        if (list != null) {
            val result = ArrayList<To>(list.size)
            list.mapTo(result) {
                map(it)
            }
            return result
        }
        return emptyList()
    }
}