/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.window.core.layout

import kotlin.jvm.JvmStatic

/**
 * [WindowSizeClass] provides breakpoints for a viewport. Designers should design around the
 * different combinations of width and height buckets. Developers should use the different buckets
 * to specify the layouts. Ideally apps will work well in each bucket and by extension work well
 * across multiple devices. If two devices are in similar buckets they should behave similarly.
 *
 * This class is meant to be a common definition that can be shared across different device types.
 * Application developers can use WindowSizeClass to have standard window buckets and design the UI
 * around those buckets. Library developers can use these buckets to create different UI with
 * respect to each bucket. This will help with consistency across multiple device types.
 *
 * A library developer use-case can be creating some navigation UI library. For a size
 * class with the [WindowWidthSizeClass.EXPANDED] width it might be more reasonable to have a side
 * navigation. For a [WindowWidthSizeClass.COMPACT] width, a bottom navigation might be a better
 * fit.
 *
 * An application use-case can be applied for apps that use a list-detail pattern. The app can use
 * the [WindowWidthSizeClass.MEDIUM] to determine if there is enough space to show the list and the
 * detail side by side. If all apps follow this guidance then it will present a very consistent user
 * experience.
 *
 * @see WindowWidthSizeClass
 * @see WindowHeightSizeClass
 */
class WindowSizeClass private constructor(
    val windowWidthSizeClass: WindowWidthSizeClass,
    val windowHeightSizeClass: WindowHeightSizeClass,
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        if (this::class != other::class) return false

        val that = other as WindowSizeClass

        if (windowWidthSizeClass != that.windowWidthSizeClass) return false
        if (windowHeightSizeClass != that.windowHeightSizeClass) return false

        return true
    }

    override fun hashCode(): Int {
        var result = windowWidthSizeClass.hashCode()
        result = 31 * result + windowHeightSizeClass.hashCode()
        return result
    }

    override fun toString(): String {
        return "SizeClass { widthSizeClass: $windowWidthSizeClass," +
            " heightSizeClass: $windowHeightSizeClass }"
    }

    companion object {
        /**
         * Computes the [WindowSizeClass] for the given width and height in DP.
         * @param dpWidth width of a window in DP.
         * @param dpHeight height of a window in DP.
         * @return [WindowSizeClass] that is recommended for the given dimensions.
         * @throws IllegalArgumentException if [dpWidth] or [dpHeight] is
         * negative.
         */
        @JvmStatic
        fun compute(dpWidth: Float, dpHeight: Float): WindowSizeClass {
            val windowWidthSizeClass = WindowWidthSizeClass.compute(dpWidth)
            val windowHeightSizeClass = WindowHeightSizeClass.compute(dpHeight)
            return WindowSizeClass(windowWidthSizeClass, windowHeightSizeClass)
        }
    }
}
