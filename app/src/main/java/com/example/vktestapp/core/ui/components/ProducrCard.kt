package com.example.vktestapp.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.vktestapp.core.model.ProductShortCard
import com.example.vktestapp.core.ui.theme.VkTestAppTheme

@Composable
fun ProductCard(
    model: ProductShortCard,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(16.dp)
            .clickable(
                onClick = onClick
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(160.dp)
                .clip(shape = RoundedCornerShape(16.dp)),
            painter = rememberAsyncImagePainter(model.thumbnail),
            contentScale = ContentScale.FillHeight,
            contentDescription = "My content description",
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier,
            text = model.title
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row {
            Text(
                text = "${model.price}$",
            )
            Text(
                text = "${(model.price * 100 / (100 - model.discountPercentage)).toInt()}$",
                style = TextStyle(textDecoration = TextDecoration.LineThrough),
                color = Color.Gray
            )
        }
    }
}

@Preview
@Composable
fun ProductCardPreview() {
    VkTestAppTheme {
        ProductCard(
            model = ProductShortCard(
                id = 1,
                title = "iPhone",
                price = 10,
                discountPercentage = 17.94,
                thumbnail = "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg"
            )
        )
    }
}
