package com.mutkuensert.fitnesspursuit.ui.bodysizes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mutkuensert.fitnesspursuit.R
import java.time.LocalDateTime

private const val VERTICAL_SPACE = 15

@Composable
fun BodySizes() {
    val context = LocalContext.current
    val (leftBicep, onLeftBicepChange) = remember { mutableStateOf("") }
    val (rightBicep, onRightBicepChange) = remember { mutableStateOf("") }
    val (leftForearm, onLeftForearmChange) = remember { mutableStateOf("") }
    val (rightForearm, onRightForearmChange) = remember { mutableStateOf("") }
    val (leftCalf, onLeftCalfChange) = remember { mutableStateOf("") }
    val (rightCalf, onRightCalfChange) = remember { mutableStateOf("") }
    val (leftThigh, onLeftThighChange) = remember { mutableStateOf("") }
    val (rightThigh, onRightThighChange) = remember { mutableStateOf("") }
    val (chest, onChestChange) = remember { mutableStateOf("") }
    val (hips, onHipsChange) = remember { mutableStateOf("") }
    val (neck, onNeckChange) = remember { mutableStateOf("") }
    val (shoulders, onShouldersChange) = remember { mutableStateOf("") }
    val (waist, onWaistChange) = remember { mutableStateOf("") }
    val time = LocalDateTime.now()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(VERTICAL_SPACE.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(VERTICAL_SPACE.dp))

        LeftRightTextField(
            title = context.getString(R.string.bicep),
            left = leftBicep,
            onLeftChange = onLeftBicepChange,
            right = rightBicep,
            onRightChange = onRightBicepChange
        )

        LeftRightTextField(
            title = context.getString(R.string.forearm),
            left = leftForearm,
            onLeftChange = onLeftForearmChange,
            right = rightForearm,
            onRightChange = onRightForearmChange
        )

        LeftRightTextField(
            title = context.getString(R.string.calf),
            left = leftCalf,
            onLeftChange = onLeftCalfChange,
            right = rightCalf,
            onRightChange = onRightCalfChange
        )

        LeftRightTextField(
            title = context.getString(R.string.thigh),
            left = leftThigh,
            onLeftChange = onLeftThighChange,
            right = rightThigh,
            onRightChange = onRightThighChange
        )

        Column(
            modifier = Modifier.width(120.dp),
            verticalArrangement = Arrangement.spacedBy(VERTICAL_SPACE.dp)
        ) {
            OutlinedTextField(
                value = chest,
                onValueChange = onChestChange,
                placeholder = {
                    Text(
                        text = context.getString(R.string.chest)
                    )
                },
                singleLine = true
            )

            OutlinedTextField(
                value = hips,
                onValueChange = onHipsChange,
                placeholder = {
                    Text(
                        text = context.getString(R.string.hips)
                    )
                },
                singleLine = true
            )

            OutlinedTextField(
                value = neck,
                onValueChange = onNeckChange,
                placeholder = {
                    Text(
                        text = context.getString(R.string.neck)
                    )
                },
                singleLine = true
            )

            OutlinedTextField(
                value = shoulders,
                onValueChange = onShouldersChange,
                placeholder = {
                    Text(
                        text = context.getString(R.string.shoulders)
                    )
                },
                singleLine = true
            )

            OutlinedTextField(
                value = waist,
                onValueChange = onWaistChange,
                placeholder = {
                    Text(
                        text = context.getString(R.string.shoulders)
                    )
                },
                singleLine = true
            )
        }

        with(time) { Text(text = "$dayOfMonth.$monthValue.$year $hour:$minute") }

        Text(
            modifier = Modifier.clickable { },
            text = context.getString(R.string.save)
        )

        Spacer(modifier = Modifier.height(VERTICAL_SPACE.dp))
    }
}

@Composable
fun LeftRightTextField(
    modifier: Modifier = Modifier,
    title: String,
    left: String,
    onLeftChange: (String) -> Unit,
    right: String,
    onRightChange: (String) -> Unit
) {
    val context = LocalContext.current

    Column(modifier = modifier) {
        Text(text = title)

        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .requiredWidth(120.dp),
                value = left,
                onValueChange = onLeftChange,
                singleLine = true,
                placeholder = { Text(text = context.getString(R.string.left)) }
            )

            OutlinedTextField(
                modifier = Modifier
                    .requiredWidth(120.dp),
                value = right,
                onValueChange = onRightChange,
                singleLine = true,
                placeholder = { Text(text = context.getString(R.string.right)) }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewBodySizes() {
    BodySizes()
}
