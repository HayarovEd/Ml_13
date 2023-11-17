package com.investpro.presentation

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import coil.compose.AsyncImage
import com.investpro.R
import com.budgetwise.financial.presentation.MainEvent
import com.investpro.data.VALUE_ONE
import com.investpro.domain.model.ElementOffer
import com.investpro.domain.model.StatusApplication
import com.investpro.domain.model.basedto.BaseState
import com.investpro.ui.theme.baseBackground
import com.investpro.ui.theme.baseText
import com.investpro.ui.theme.blue
import com.investpro.ui.theme.grey
import com.investpro.ui.theme.lightGrey

@SuppressLint("ResourceAsColor")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OfferScreen(
    modifier: Modifier = Modifier,
    elementOffer: ElementOffer,
    baseState: BaseState,
    onEvent: (MainEvent) -> Unit,
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = baseBackground
                ),
                title = {
                    Row(
                        modifier = modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = {
                            onEvent(
                                MainEvent.OnChangeStatusApplication(
                                    StatusApplication.Connect(baseState)
                                )
                            )
                        }) {
                            Icon(
                                modifier = modifier.rotate(180f),
                                imageVector = ImageVector.vectorResource(id = R.drawable.left_arrow3),
                                tint = blue,
                                contentDescription = ""
                            )
                        }
                        Spacer(modifier = modifier.width(8.dp))
                        Text(
                            color = baseText,
                            fontStyle = FontStyle(R.font.poppins),
                            fontSize = 20.sp,
                            fontWeight = FontWeight(600),
                            text = elementOffer.name
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = baseBackground
            ) {
                Row (
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(
                        modifier = modifier.weight(1f),
                        onClick = {
                        onEvent(
                            MainEvent.OnChangeStatusApplication(
                                StatusApplication.Connect(baseState)
                            )
                        )
                    },
                        shape = RoundedCornerShape(5.dp),
                        border = BorderStroke(
                            width = 2.dp,
                            color = blue
                        ),
                        contentPadding = PaddingValues(
                            vertical = 16.dp,
                        ),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = baseBackground,
                            contentColor = blue,
                        ),
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.route_left),
                            tint = blue,
                            contentDescription = ""
                        )
                    }
                    Spacer(modifier = modifier.width(12.dp))
                    Button(
                        modifier = modifier
                            .weight(3f),
                            //.padding(horizontal = 15.dp)
                            //.fillMaxWidth(),
                        shape = RoundedCornerShape(5.dp),
                        contentPadding = PaddingValues(
                            vertical = 16.dp,
                        ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = blue,
                            contentColor = baseBackground,
                        ),
                        onClick = {
                            onEvent(
                                MainEvent.OnGoToWeb(
                                    urlOffer = elementOffer.order,
                                    nameOffer = elementOffer.name
                                )
                            )
                        }
                    ) {
                        Text(
                            text = stringResource(id = R.string.checkout),
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = FontFamily(Font(R.font.inter)),
                                fontWeight = FontWeight(600),
                            )
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
                .shadow(elevation = 10.dp, spotColor = grey, ambientColor = grey, shape = RoundedCornerShape(20.dp))
                .background(color = baseBackground)
                .clip(shape = RoundedCornerShape(20.dp))
                .background(color = lightGrey)
                .padding(16.dp),
        ) {
            AsyncImage(
                modifier = modifier
                    .fillMaxWidth(),
                model = elementOffer.pathImage,
                contentScale = ContentScale.FillWidth,
                contentDescription = ""
            )
            Spacer(modifier = modifier.height(26.dp))
            AndroidView(
                modifier = modifier
                    .fillMaxWidth()
                   // .clip(shape = RoundedCornerShape(5.dp))
                   // .background(color = white)
                    .padding(5.dp),
                factory = { context -> TextView(context) },
                update = {
                    it.setTextColor(R.color.white)
                    it.text = HtmlCompat.fromHtml(
                        elementOffer.description,
                        HtmlCompat.FROM_HTML_MODE_COMPACT
                    )
                }
            )
            Spacer(modifier = modifier.height(24.dp))
            /*Text(
                text = elementOffer.name,
                style = TextStyle(
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat)),
                    fontWeight = FontWeight(600),
                ),
                color = baseText,
            )
            Spacer(modifier = modifier.height(12.dp))*/
            RowData(
                title = stringResource(id = R.string.amount),
                content = elementOffer.amount,
            )
            if (elementOffer.showPercent == VALUE_ONE) {
                /*Divider(
                    thickness = 1.dp,
                    color = grey
                )*/
                //Spacer(modifier = modifier.height(8.dp))
                RowData(
                    title = stringResource(id = R.string.bet),
                    content = elementOffer.bet,
                )
            }
            if (elementOffer.showTerm == VALUE_ONE) {
                //Spacer(modifier = modifier.height(8.dp))
                /*Divider(
                    thickness = 1.dp,
                    color = grey
                )*/
                RowData(
                    title = stringResource(id = R.string.term),
                    content = elementOffer.term,
                )
            }
            Spacer(modifier = modifier.height(24.dp))
            RowCard(
                showVisa = elementOffer.showVisa,
                showMaster = elementOffer.showMaster,
                showYandex = elementOffer.showYandex,
                showMir = elementOffer.showMir,
                showQivi = elementOffer.showQiwi,
                showCache = elementOffer.showCache
            )
            /*Spacer(modifier = modifier.height(35.dp))
            Button(
                modifier = modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(25.dp),
                contentPadding = PaddingValues(
                    vertical = 7.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = blue,
                    contentColor = white,
                ),
                onClick = {
                    onEvent(
                        MainEvent.OnGoToWeb(
                            urlOffer = elementOffer.order,
                            nameOffer = elementOffer.name
                        )
                    )
                }
            ) {
                Text(
                    text = stringResource(id = R.string.checkout),
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(font.nunito)),
                        fontWeight = FontWeight(700),
                    )
                )
            }*/
        }
    }
}