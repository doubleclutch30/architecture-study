package com.lutawav.architecturestudy.data.mapper

interface Mapper<T, H> {
    fun map(input: T): H

    fun reverseMap(output: H): T
}