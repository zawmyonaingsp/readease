package com.sevenpeaks.zawmyonaing.readease.presentation.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import com.gowtham.ratingbar.StepSize
import com.sevenpeaks.zawmyonaing.readease.R
import com.sevenpeaks.zawmyonaing.readease.ui.theme.spacingLarge

@Composable
fun RatingDialog(
    modifier: Modifier = Modifier,
    initialRating: Float = 0f,
    onRatingApplied: (Float) -> Unit,
    onDismissRequest: () -> Unit,
) {
    var rating by remember { mutableFloatStateOf(initialRating) }

    Dialog(
        onDismissRequest = onDismissRequest,
    ) {

        Card(
            modifier = modifier.sizeIn(
                minWidth = 280.dp,
                maxWidth = 560.dp,
            ),
            shape = MaterialTheme.shapes.extraLarge,
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            RatingBar(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                value = rating,
                numOfStars = 5,
                style = RatingBarStyle.Fill(
                    activeColor = MaterialTheme.colorScheme.primary,
                    inActiveColor = MaterialTheme.colorScheme.inversePrimary
                ),
                stepSize = StepSize.HALF,
                onValueChange = {

                },
                onRatingChanged = {
                    rating = it
                }
            )

            Spacer(modifier = Modifier.height(spacingLarge))

            OutlinedButton(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = {
                    onRatingApplied(rating)
                    onDismissRequest()
                }
            ) {
                Text(text = stringResource(id = R.string.lbl_apply))
            }

            Spacer(modifier = Modifier.height(spacingLarge))

        }
    }

}