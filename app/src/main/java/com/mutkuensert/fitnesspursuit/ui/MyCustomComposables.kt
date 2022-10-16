package com.mutkuensert.fitnesspursuit.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MyCardWithShadow(content: @Composable () -> Unit){
    Card(modifier = Modifier
        .wrapContentSize()
        .shadow(elevation = 5.dp)
        .animateContentSize(),
        backgroundColor = MaterialTheme.colors.surface){
        Column(modifier = Modifier.padding(5.dp)) {
            content()
        }
    }
}


@Composable
fun MyButtonWithShadow(modifier: Modifier = Modifier.padding(5.dp), onClick: () -> Unit, content: @Composable () -> Unit){
    Button(onClick = onClick,
        elevation = ButtonDefaults.elevation(defaultElevation = 10.dp),
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface)) {
        content()
    }
}

@Preview(showSystemUi = true)
@Composable
fun MyButtonWithShadowPreview(){
    Surface {
        Column {
            MyButtonWithShadow(onClick = { }) {
                Text(text = "Text")
            }
        }

    }

}