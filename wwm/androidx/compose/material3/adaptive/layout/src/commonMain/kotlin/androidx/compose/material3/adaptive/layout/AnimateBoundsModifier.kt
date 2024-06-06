/*
 * Copyright 2023 The Android Open Source Project
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

package androidx.compose.material3.adaptive.layout

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector
import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.spring
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.layout.LookaheadScope
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.intermediateLayout
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.round
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
internal fun Modifier.animateBounds(
    modifier: Modifier = Modifier,
    sizeAnimationSpec: FiniteAnimationSpec<IntSize> = spring(
        Spring.DampingRatioNoBouncy,
        Spring.StiffnessMediumLow
    ),
    positionAnimationSpec: FiniteAnimationSpec<IntOffset> = spring(
        Spring.DampingRatioNoBouncy,
        Spring.StiffnessMediumLow
    ),
    debug: Boolean = false,
    lookaheadScope: (closestLookaheadScope: LookaheadScope) -> LookaheadScope = { it },
) = composed {

    val outerOffsetAnimation = remember { DeferredAnimation(IntOffset.VectorConverter) }
    val outerSizeAnimation = remember { DeferredAnimation(IntSize.VectorConverter) }

    val offsetAnimation = remember { DeferredAnimation(IntOffset.VectorConverter) }
    val sizeAnimation = remember { DeferredAnimation(IntSize.VectorConverter) }

    // The measure logic in `intermediateLayout` is skipped in the lookahead pass, as
    // intermediateLayout is expected to produce intermediate stages of a layout transform.
    // When the measure block is invoked after lookahead pass, the lookahead size of the
    // child will be accessible as a parameter to the measure block.
    @Suppress("DEPRECATION") // TODO: Update intermediateLayout usage to approachLayout
    this
        .drawWithContent {
            drawContent()
            if (debug) {
                val offset = outerOffsetAnimation.target!! - outerOffsetAnimation.value!!
                translate(
                    offset.x.toFloat(), offset.y.toFloat()
                ) {
                    drawRect(Color.Black.copy(alpha = 0.5f), style = Stroke(10f))
                }
            }
        }
        .intermediateLayout { measurable, constraints ->
            val (w, h) = outerSizeAnimation.updateTarget(
                lookaheadSize,
                sizeAnimationSpec,
            )
            measurable
                .measure(constraints)
                .run {
                    layout(w, h) {
                        val (x, y) = outerOffsetAnimation.updateTargetBasedOnCoordinates(
                            positionAnimationSpec
                        )
                        place(x, y)
                    }
                }
        }
        .then(modifier)
        .drawWithContent {
            drawContent()
            if (debug) {
                val offset = offsetAnimation.target!! - offsetAnimation.value!!
                translate(
                    offset.x.toFloat(), offset.y.toFloat()
                ) {
                    drawRect(Color.Green.copy(alpha = 0.5f), style = Stroke(10f))
                }
            }
        }
        .intermediateLayout { measurable, _ ->
            // When layout changes, the lookahead pass will calculate a new final size for the
            // child modifier. This lookahead size can be used to animate the size
            // change, such that the animation starts from the current size and gradually
            // change towards `lookaheadSize`.
            val (width, height) = sizeAnimation.updateTarget(
                lookaheadSize,
                sizeAnimationSpec,
            )
            // Creates a fixed set of constraints using the animated size
            val animatedConstraints = Constraints.fixed(width, height)
            // Measure child/children with animated constraints.
            val placeable = measurable.measure(animatedConstraints)
            layout(placeable.width, placeable.height) {
                val (x, y) = with(lookaheadScope(this@intermediateLayout)) {
                    offsetAnimation.updateTargetBasedOnCoordinates(
                        positionAnimationSpec,
                    )
                }
                placeable.place(x, y)
            }
        }
}

context(LookaheadScope, Placeable.PlacementScope, CoroutineScope)
@OptIn(ExperimentalComposeUiApi::class)
internal fun DeferredAnimation<IntOffset, AnimationVector2D>.updateTargetBasedOnCoordinates(
    animationSpec: FiniteAnimationSpec<IntOffset>,
): IntOffset {
    coordinates?.let { coordinates ->
        with(this@PlacementScope) {
            val targetOffset = lookaheadScopeCoordinates.localLookaheadPositionOf(coordinates)
            val animOffset = updateTarget(
                targetOffset.round(),
                animationSpec,
            )
            val current = lookaheadScopeCoordinates.localPositionOf(
                coordinates,
                Offset.Zero
            ).round()
            return (animOffset - current)
        }
    }

    return IntOffset.Zero
}

// Experimenting with a way to initialize animation during measurement && only take the last target
// change in a frame (if the target was changed multiple times in the same frame) as the
// animation target.
internal class DeferredAnimation<T, V : AnimationVector>(
    private val vectorConverter: TwoWayConverter<T, V>,
) {
    val value: T?
        get() = animatable?.value ?: target
    var target: T? by mutableStateOf(null)
        private set
    private var animatable: Animatable<T, V>? = null

    internal val isActive: Boolean
        get() = target != animatable?.targetValue || animatable?.isRunning == true

    context (CoroutineScope)
    fun updateTarget(
        targetValue: T,
        animationSpec: FiniteAnimationSpec<T>,
    ): T {
        target = targetValue
        if (target != null && target != animatable?.targetValue) {
            animatable?.run {
                launch {
                    animateTo(
                        targetValue,
                        animationSpec
                    )
                }
            } ?: Animatable(targetValue, vectorConverter).let {
                animatable = it
            }
        }
        return animatable?.value ?: targetValue
    }
}
