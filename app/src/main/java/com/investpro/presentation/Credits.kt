
package com.investpro.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.budgetwise.financial.presentation.MainEvent
import com.investpro.domain.model.basedto.BaseState
import com.investpro.domain.model.basedto.Credit
import com.investpro.ui.theme.baseBackground

@Composable
fun Credits(
    modifier: Modifier = Modifier,
    valuePaddings: PaddingValues,
    credits: List<Credit>,
    onEvent: (MainEvent) -> Unit,
    baseState: BaseState,
    creditLazyState: LazyListState,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = baseBackground)
            .padding(valuePaddings),
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            state = creditLazyState
        ) {
            items(credits) { credit ->
                ItemCredit(
                    credit = credit,
                    onEvent = onEvent,
                    baseState = baseState
                )
            }
        }
    }
}
