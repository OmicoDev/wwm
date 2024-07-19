/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
@file:Suppress("UnusedReceiverParameter")

import me.omico.gradm.dependency.Circuit
import me.omico.gradm.dependency.Delusion
import me.omico.gradm.dependency.Jetbrains
import me.omico.gradm.dependency.Kotlinx
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

inline val KotlinDependencyHandler.jetbrains: Jetbrains get() = Jetbrains
inline val KotlinDependencyHandler.circuit: Circuit get() = Circuit
inline val KotlinDependencyHandler.delusion: Delusion get() = Delusion
inline val KotlinDependencyHandler.kotlinx: Kotlinx get() = Kotlinx
