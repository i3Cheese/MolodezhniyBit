package com.nux.studio.studtourism.ui.components.atoms

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nux.studio.studtourism.R

@Composable
fun Dates(
    dates: String,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.icon_calendar),
            contentDescription = "Dates",
            modifier = Modifier
                .size(30.dp)
                .align(Alignment.CenterVertically)
        )
        Text(
            text = dates,
            fontSize = 20.sp,
            modifier = Modifier.padding(15.dp),
        )
    }
}