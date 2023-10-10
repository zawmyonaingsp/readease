package com.sevenpeaks.zawmyonaing.readease.presentation.common

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.sevenpeaks.zawmyonaing.readease.R

@Composable
fun SubscriptionDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
    onSubscribe: () -> Unit,
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        title = {
            Text(text = stringResource(id = R.string.lbl_premium_subscription))
        },
        text = {
            Text(text = stringResource(id = R.string.msg_premium_subscription))
        },
        confirmButton = {
            TextButton(onClick = onSubscribe) {
                Text(text = stringResource(id = R.string.lbl_subscribe))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text(text = stringResource(id = R.string.lbl_cancel))
            }
        },
    )
}
