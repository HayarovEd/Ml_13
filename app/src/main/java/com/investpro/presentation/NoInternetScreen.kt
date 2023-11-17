package com.investpro.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.investpro.R
import com.budgetwise.financial.presentation.MainEvent
import com.budgetwise.financial.presentation.MainEvent.Reconnect
import com.investpro.ui.theme.baseBackground
import com.investpro.ui.theme.blue
import com.investpro.ui.theme.grey

@Composable
fun NoInternetScreen(
    modifier: Modifier = Modifier,
    onEvent: (MainEvent) -> Unit
) {
    Box (
        modifier = modifier
            .fillMaxSize()
            .background(color = baseBackground)
            .padding(24.dp)
    ) {
        Column (
            modifier = modifier.align(alignment = Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = modifier.size(110.dp),
                painter = painterResource(
                id = R.drawable.no_connection),
                contentDescription = "")
            Spacer(modifier = modifier.height(47.dp))
            Text(
                text = stringResource(id = R.string.not_connect),
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
                fontStyle = FontStyle(R.font.poppins),
                color = blue,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = modifier.height(1.dp))
            Text(
                text = stringResource(id = R.string.try_internet),
                fontSize = 14.sp,
                fontWeight = FontWeight(500),
                fontStyle = FontStyle(R.font.poppins),
                color = grey,
                textAlign = TextAlign.Center
            )
            /*Spacer(modifier = modifier.height(64.dp))
            Button(
                modifier = modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(25.dp),
                contentPadding = PaddingValues(
                    vertical = 9.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = blue,
                    contentColor = white,
                ),
                onClick = { onEvent(Reconnect) }
            ) {
                Text(
                    text = stringResource(id = R.string.reconnect),
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.nunito)),
                        fontWeight = FontWeight(700),
                    )
                )
            }*/
        }
        Button(
            modifier = modifier
                .fillMaxWidth()
                .align(alignment = Alignment.BottomCenter),
            shape = RoundedCornerShape(5.dp),
            /*border = BorderStroke(
                width = 1.dp,
                color = green
            ),*/
            contentPadding = PaddingValues(
                vertical = 16.dp
            ),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = blue,
                contentColor = baseBackground,
            ),
            onClick = { onEvent(Reconnect) }
        ) {
            Text(
                text = stringResource(id = R.string.reconnect),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(600),
                )
            )
        }
    }
}
@Preview
@Composable
fun SampleNoInternetScreen() {
    NoInternetScreen(onEvent = {})
}
