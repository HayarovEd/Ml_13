package com.budgetwise.financial.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.budgetwise.financial.R
import com.budgetwise.financial.R.string
import com.investpro.data.VALUE_ONE
import com.investpro.domain.model.basedto.BaseDto
import com.investpro.domain.model.basedto.BaseState
import com.investpro.domain.model.basedto.BaseState.Cards
import com.investpro.domain.model.basedto.BaseState.Credits
import com.investpro.domain.model.basedto.BaseState.Loans
import com.investpro.domain.model.basedto.CardsCredit
import com.investpro.domain.model.basedto.CardsDebit
import com.investpro.domain.model.basedto.CardsInstallment
import com.budgetwise.financial.ui.theme.blue
import com.budgetwise.financial.ui.theme.grey
import com.budgetwise.financial.ui.theme.white
import com.investpro.presentation.WebViewScreenPrimary


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConnectScreen(
    modifier: Modifier = Modifier,
    db: BaseDto,
    baseState: BaseState,
    creditCards: List<CardsCredit>,
    debitCards: List<CardsDebit>,
    installmentCards: List<CardsInstallment>,
    onEvent: (MainEvent) -> Unit,
    onClickPrimary: () -> Unit,
    onClickLoans: () -> Unit,
    onClickCards: () -> Unit,
    onClickCredits: () -> Unit,
    onClickRules: () -> Unit,
    loanLazyState: LazyListState,
    creditLazyState: LazyListState,
    creditCardloanLazyState: LazyListState,
    debitCardLazyState: LazyListState,
    instalmentCardLazyState: LazyListState,
) {
    val title = when (baseState) {
        is Cards -> stringResource(id = string.cards)
        Credits -> stringResource(id = string.credits)
        Loans -> stringResource(id = string.loans)
        is BaseState.WebPrimary -> db.appConfig.namePrimary?: ""
    }
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        /*topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            color = white,
                            fontStyle = FontStyle(R.font.gotham),
                            fontSize = 20.sp,
                            fontWeight = FontWeight(400),
                            text = title
                        )
                        *//*IconButton(onClick = onClickRules) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.info),
                                tint = black,
                                contentDescription = "")
                        }*//*
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = baseBackground
                )
            )
        },*/
        bottomBar = {
            BottomAppBar(
                containerColor = white,
                modifier = modifier
                    //.clip(shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    if (!db.appConfig.showedIconPrimary.isNullOrEmpty()
                        && db.appConfig.showedIconPrimary == VALUE_ONE
                        && !db.appConfig.namePrimary.isNullOrEmpty()
                        && !db.appConfig.urlPrimary.isNullOrEmpty()) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            IconButton(
                                onClick = onClickPrimary) {
                                Image(
                                    imageVector = ImageVector.vectorResource(drawable.money_1),
                                    contentDescription = "")
                            }
                            /*Text(
                                color = if (baseState is Loans) green else lightGray,
                                fontStyle = FontStyle(R.font.onest_400),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Normal,
                                text = db.appConfig.namePrimary
                            )*/
                        }
                    }
                    if (!db.loans.isNullOrEmpty()) {
                        ItemBottomBar(
                            color = if (baseState is Loans) blue else grey,
                            content = stringResource(id = string.loans),
                            icon = ImageVector.vectorResource(id = drawable.credits),
                            onClick = onClickLoans
                        )
                    }
                    if (!db.cards.isNullOrEmpty()) {
                        ItemBottomBar(
                            color = if (baseState is Cards) blue else grey,
                            content = stringResource(id = string.cards),
                            icon = ImageVector.vectorResource(id = drawable.cards),
                            onClick = onClickCards
                        )
                    }
                    if (!db.credits.isNullOrEmpty()) {
                        ItemBottomBar(
                            color = if (baseState is Credits) blue else grey,
                            content = stringResource(id = string.credits),
                            icon = ImageVector.vectorResource(id = drawable.credits),
                            onClick = onClickCredits
                        )
                    }
                }

            }
        }
    ) { valuePaddings ->
        when (baseState) {
            is Cards -> {
                CardsScreen(
                    valuePaddings = valuePaddings,
                    creditCards = creditCards,
                    debitCards = debitCards,
                    installmentCards = installmentCards,
                    typeCard = baseState.typeCard,
                    onEvent = onEvent,
                    baseState = baseState,
                    creditCardloanLazyState = creditCardloanLazyState,
                    debitCardLazyState = debitCardLazyState,
                    instalmentCardLazyState = instalmentCardLazyState,
                )
            }

            Credits -> {
                Credits(
                    valuePaddings = valuePaddings,
                    credits = db.credits,
                    onEvent = onEvent,
                    baseState = baseState,
                    creditLazyState = creditLazyState
                )
            }

            Loans -> {
                Loans(
                    valuePaddings = valuePaddings,
                    loans = db.loans,
                    onEvent = onEvent,
                    baseState = baseState,
                    loanLazyState = loanLazyState
                )
            }
            is BaseState.WebPrimary -> {
                WebViewScreenPrimary(
                    url = db.appConfig.urlPrimary?:"",
                    offerName = db.appConfig.namePrimary?:"",
                    valuePaddings = valuePaddings,
                    onEvent = onEvent)
            }
        }
    }
}

@Composable
fun ItemBottomBar(
    color: Color,
    icon: ImageVector,
    content: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = color
            ),
            onClick = onClick) {
            Icon(imageVector = icon, contentDescription = "")
        }
        Text(
            color = color,
            fontStyle = FontStyle(R.font.montserrat),
            fontSize = 11.sp,
            fontWeight = FontWeight(700),
            text = content
        )
    }
}
