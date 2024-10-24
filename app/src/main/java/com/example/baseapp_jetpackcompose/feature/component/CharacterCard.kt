package com.example.baseapp_jetpackcompose.feature.component

import android.hardware.TriggerEvent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.baseapp_jetpackcompose.feature.character.domain.model.CharacterDto

@Composable
fun CharacterCard(
    modifier: Modifier = Modifier,
    character: CharacterDto?,
    detailClick: () -> Unit,
    onTriggerEvent: (CharacterDto) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { detailClick() },
        shape = RoundedCornerShape(size = 8.dp),
        elevation = 8.dp,
    ) {
        Row(
            modifier = Modifier
                .size(100.dp)
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 10.dp),
            ) {
                NetworkImage(
                    imageUrl = character?.image,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(80.dp)
                        .clip(shape = RoundedCornerShape(15)),
                    contentScale = ContentScale.Crop,
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceAround) {
                    Text(
                        text = character?.name.orEmpty(),
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.primary,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = character?.species.orEmpty(),
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.primary,
                    )
                }
            }
            Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.CenterEnd){
                character?.let {
                    //favorite button
                }
            }
        }

    }
}

@Preview
@Composable
private fun BodyPreview() {
    CharacterCard(
        detailClick = {},
        onTriggerEvent = {},
        character = CharacterDto(
            id = 1,
            name = "Rick Sanchez",
            type = "Human",
            created = "2017-11-04T18:48:46.250Z",
            episode = listOf("https://rickandmortyapi.com/api/episode/1"),
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            gender = "",
            location = null,
            origin = null,
            species = "dfdfdfdfdfdfdf",
            url = "",
            isFavorite = true
        ),
    )
}