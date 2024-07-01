package com.example.xstudy.domain.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

data class Line(
    val start: Offset,
    val end: Offset,
    val color: Color,
    val strokeWidth: Float = 5f
) : MutableList<Line> {
    override val size: Int
        get() = TODO("Not yet implemented")

    override fun add(element: Line): Boolean {
        TODO("Not yet implemented")
    }

    override fun add(index: Int, element: Line) {
        TODO("Not yet implemented")
    }

    override fun addAll(index: Int, elements: Collection<Line>): Boolean {
        TODO("Not yet implemented")
    }

    override fun addAll(elements: Collection<Line>): Boolean {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }

    override fun contains(element: Line): Boolean {
        TODO("Not yet implemented")
    }

    override fun containsAll(elements: Collection<Line>): Boolean {
        TODO("Not yet implemented")
    }

    override fun get(index: Int): Line {
        TODO("Not yet implemented")
    }

    override fun indexOf(element: Line): Int {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun iterator(): MutableIterator<Line> {
        TODO("Not yet implemented")
    }

    override fun lastIndexOf(element: Line): Int {
        TODO("Not yet implemented")
    }

    override fun listIterator(): MutableListIterator<Line> {
        TODO("Not yet implemented")
    }

    override fun listIterator(index: Int): MutableListIterator<Line> {
        TODO("Not yet implemented")
    }

    override fun remove(element: Line): Boolean {
        TODO("Not yet implemented")
    }

    override fun removeAll(elements: Collection<Line>): Boolean {
        TODO("Not yet implemented")
    }

    override fun removeAt(index: Int): Line {
        TODO("Not yet implemented")
    }

    override fun retainAll(elements: Collection<Line>): Boolean {
        TODO("Not yet implemented")
    }

    override fun set(index: Int, element: Line): Line {
        TODO("Not yet implemented")
    }

    override fun subList(fromIndex: Int, toIndex: Int): MutableList<Line> {
        TODO("Not yet implemented")
    }
}
