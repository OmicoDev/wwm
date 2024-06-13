/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList

// TODO Waiting for Delusion 0.10.0
@Composable
fun <T> rememberUpdatedListState(newValues: Iterable<T>): SnapshotStateList<T> =
    remember<SnapshotStateList<T>>(::mutableStateListOf)
        .apply(SnapshotStateList<T>::clear)
        .apply { addAll(newValues) }
