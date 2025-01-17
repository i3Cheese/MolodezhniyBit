package com.nux.studio.studtourism.ui.components.molecules

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.nux.studio.studtourism.data.local.models.CancelBooking
import com.nux.studio.studtourism.data.local.models.DormitoryBooked
import com.nux.studio.studtourism.data.local.models.Room
import com.nux.studio.studtourism.ui.components.atoms.*
import com.nux.studio.studtourism.ui.components.atoms.texts.Body1
import com.nux.studio.studtourism.ui.viewmodels.MainViewModel

@Composable
fun CardDormitoryBooked(
    dormitoryBooked: DormitoryBooked,
    navController: NavController,
    viewModel: MainViewModel
) {
    val dormitory = viewModel.state.dormitoriesList.find { it.id == dormitoryBooked.dormitoryId }
    val room: Room? = dormitory?.rooms?.get(dormitoryBooked.roomId)
    val price: String? = room?.details?.price;
    val dates: String? = dormitoryBooked.dates?.let { "${it.from} - ${it.to}" };

    Card(
        elevation = 10.dp,
        modifier = Modifier
            .padding(start = 4.dp, top = 8.dp, end = 4.dp, bottom = 0.dp)
            .fillMaxSize()
            .clip(RoundedCornerShape(20.dp))

    ) {
        Box(
//            contentAlignment = Alignment.CenterVertically
            modifier = Modifier.height(300.dp)
        ) {
            AsyncImage(
                model = dormitory?.details?.mainInfo?.photos?.get(0),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Crop,
//                colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0.9f) })
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent, Color.Black
                            ),
                            startY = 300f
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp),
//                        contentAlignment = Alignment.BottomStart,
                verticalArrangement = Arrangement.Bottom,
            ) {
                dormitory?.details?.mainInfo?.name?.let { name ->
                    Body1(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 5.dp)
                            .padding(bottom = 10.dp),
                        text = name,
                        color = MaterialTheme.colors.onSecondary,
                        textAlign = TextAlign.Left,
                        fontWeight = FontWeight.Bold,
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp)
                        .padding(horizontal = 5.dp)
                ) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)) {
                        dormitoryBooked.quantity?.let { quantity ->
                            People(
                                quantity.toString(),
                                tint = MaterialTheme.colors.background,
                                modifier = Modifier.padding(end = 15.dp)
                            )
                        }
                        if (dates != null) {
                            Dates(
                                dates,
                                tint = MaterialTheme.colors.background,
                            )
                        }
                    }
                    if (price != null) {
                        Price(
                            formatPrice(price), tint = MaterialTheme.colors.background,
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    Button(
                        modifier = Modifier.weight(1f),
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Transparent,
                            contentColor = MaterialTheme.colors.error,
                        ),
                        onClick = {
                            viewModel.cancelDormitoryBooking(
                                CancelBooking(
                                    id = dormitoryBooked.dormitoryId ?: "0",
                                    dates = dormitoryBooked.dates
                                )
                            )
                        },
                    ) {
                        Text(
                            text = "Отменить Заявку",
                            modifier = Modifier.padding(10.dp),
                            color = MaterialTheme.colors.error,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    Button(
                        modifier = Modifier.weight(1f),
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Transparent,
                            contentColor = MaterialTheme.colors.background,
                        ),
                        border = BorderStroke(1.dp, MaterialTheme.colors.background),
                        onClick = {/* TODO */ },
                    ) {
                        Text(
                            text = "Изменить",
                            modifier = Modifier.padding(10.dp),
                            color = MaterialTheme.colors.background,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
            }
            dormitory?.details?.mainInfo?.city?.let { city ->
                Pill(
                    text = city,
                    modifier = Modifier.align(Alignment.TopStart),
                    variant = PillVariant.BACKGROUND,
                )
            }
        }
    }
}