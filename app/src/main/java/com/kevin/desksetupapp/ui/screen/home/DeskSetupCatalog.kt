package com.kevin.desksetupapp.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.kevin.desksetupapp.ui.theme.SubmissionApplicationTheme

@Composable
fun DeskSetupsCatalog(
        name: String,
        description: String,
        image: String,
        modifier: Modifier = Modifier
) {
    Card(modifier = modifier.fillMaxWidth().padding(8.dp), elevation = 4.dp) {
        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                    painter = rememberImagePainter(data = image),
                    contentDescription = "setup_image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(120.dp).clip(shape = RoundedCornerShape(4.dp))
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                        text = name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                        text = description,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DeskSetupCatalogPreview() {
    SubmissionApplicationTheme {
        DeskSetupsCatalog(name = "SETUP NAME", description = "laptop description", image = "")
    }
}
