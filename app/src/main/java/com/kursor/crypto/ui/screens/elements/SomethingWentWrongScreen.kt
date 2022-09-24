package com.kursor.crypto.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kursor.crypto.R
import com.kursor.crypto.ui.theme.BitcoinColor

@Composable
fun SomethingWentWrongSceeen(
    modifier: Modifier = Modifier,
    onTryAgainButtonClick: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = stringResource(id = R.string.error),
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = stringResource(id = R.string.try_again_question),
            modifier = Modifier.padding(8.dp)
        )

        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = BitcoinColor),
            onClick = { onTryAgainButtonClick() }
        ) {
            Text(text = stringResource(id = R.string.try_again))
        }
    }
}

@Preview
@Composable
fun PreviewSomethingWentWrongScreen() {
    SomethingWentWrongSceeen() {

    }
}