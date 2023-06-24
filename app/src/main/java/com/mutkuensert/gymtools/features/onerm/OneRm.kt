package com.mutkuensert.gymtools.features.onerm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mutkuensert.gymtools.components.AppTextField
import com.mutkuensert.gymtools.components.ShadowedButton
import com.mutkuensert.gymtools.components.ShadowedCard
import com.mutkuensert.gymtools.core.convertToDouble
import com.mutkuensert.gymtools.core.getStringRes
import com.mutkuensert.gymtools.resources.TextResKeys

@Composable
fun OneRm(viewModel: OneRmViewModel = hiltViewModel()) {
    val (weightInput, onWeightInputChange) = remember { mutableStateOf("") }
    val repsInput by viewModel.repsInput.collectAsStateWithLifecycle()
    val averageRm by viewModel.averageRm.collectAsStateWithLifecycle()

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(200.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    ShadowedCard { Text(text = getStringRes(TextResKeys.WEIGHT)) }

                    Spacer(modifier = Modifier.weight(1f))

                    AppTextField(
                        value = weightInput,
                        onValueChange = { onWeightInputChange(it.convertToDouble().toString()) },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.width(100.dp)
                    )
                }

                Spacer(Modifier.height(20.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    ShadowedCard { Text(text = getStringRes(TextResKeys.REPS)) }

                    Spacer(modifier = Modifier.weight(1f))

                    AppTextField(
                        value = repsInput,
                        onValueChange = { viewModel.dontAllowDotOrComma(it) },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.width(100.dp)
                    )
                }

                Text(
                    text = getStringRes(TextResKeys.ONE_RM_INFO),
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.padding(top = 10.dp),
                    fontSize = 14.sp
                )

                Spacer(Modifier.height(15.dp))

                ShadowedButton(
                    onClick = {
                        if (weightInput.isNotEmpty() && repsInput.isNotEmpty()) {
                            viewModel.calculateOneRmValues(
                                weightInput.toDouble(),
                                repsInput.toInt()
                            )
                        }
                    }
                ) {
                    Text(text = getStringRes(TextResKeys.CALCULATE))
                }

                Spacer(Modifier.height(20.dp))

                Text(text = "1 RM: $averageRm")
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewOneRmScreen() {
    OneRm()
}
