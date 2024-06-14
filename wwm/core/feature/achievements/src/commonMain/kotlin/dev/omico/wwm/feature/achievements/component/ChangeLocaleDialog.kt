/*
 * Copyright 2024 Omico. All Rights Reserved.
 */
package dev.omico.wwm.feature.achievements.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.omico.wwm.resources.WwmIcons
import dev.omico.wwm.resources.model.game.WwLocale
import dev.omico.wwm.ui.DialogWithIconButton
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun ChangeLocaleDialog(
    currentLocale: WwLocale,
    onLocaleChange: (WwLocale) -> Unit,
) {
    DialogWithIconButton(
        painter = painterResource(resource = WwmIcons.Translate),
        title = { Text(text = "Locale") },
        modifier = Modifier.fillMaxHeight(fraction = 0.7f),
        content = {
            LazyColumn {
                items(
                    items = WwLocale.entries,
                    key = WwLocale::name,
                    itemContent = { locale ->
                        ListItem(
                            modifier = Modifier.clickable { onLocaleChange(locale) },
                            headlineContent = { Text(text = locale.toString()) },
                            trailingContent = {
                                if (currentLocale == locale) {
                                    Icon(
                                        imageVector = Icons.Rounded.Check,
                                        contentDescription = "Check",
                                    )
                                }
                            },
                        )
                    },
                )
            }
        },
    )
}
