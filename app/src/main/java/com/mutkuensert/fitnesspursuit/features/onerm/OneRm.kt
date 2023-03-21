package com.mutkuensert.fitnesspursuit.features.onerm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mutkuensert.fitnesspursuit.components.ShadowedButton
import com.mutkuensert.fitnesspursuit.components.ShadowedCard
import com.mutkuensert.fitnesspursuit.core.convertToDouble
import com.mutkuensert.fitnesspursuit.core.getStringRes
import com.mutkuensert.fitnesspursuit.resources.TextResKeys

@Composable
fun OneRm(viewModel: OneRmViewModel = hiltViewModel()) {
    val (weightInput, onWeightInputChange) = remember { mutableStateOf("") }
    val repsInput by viewModel.repsInput.collectAsStateWithLifecycle()
    val epleyRM by viewModel.epleyRM.collectAsStateWithLifecycle()
    val brzyckiRM by viewModel.brzyckiRM.collectAsStateWithLifecycle()
    val mcglothinRM by viewModel.mcglothinRM.collectAsStateWithLifecycle()
    val lombardiRM by viewModel.lombardiRM.collectAsStateWithLifecycle()
    val mayhewRM by viewModel.mayhewRM.collectAsStateWithLifecycle()
    val oconnerRM by viewModel.oconnerRM.collectAsStateWithLifecycle()
    val wathenRM by viewModel.wathenRM.collectAsStateWithLifecycle()

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(300.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    ShadowedCard { Text(text = getStringRes(TextResKeys.WEIGHT_YOU_CAN_LIFT)) }

                    Spacer(modifier = Modifier.weight(1f))

                    OutlinedTextField(
                        value = weightInput,
                        onValueChange = { onWeightInputChange(it.convertToDouble().toString()) },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.width(150.dp)
                    )
                }

                Spacer(Modifier.height(20.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    ShadowedCard { Text(text = getStringRes(TextResKeys.NUMBER_OF_REPS)) }

                    Spacer(modifier = Modifier.weight(1f))

                    OutlinedTextField(
                        value = repsInput,
                        onValueChange = { viewModel.dontAllowDotOrComma(it) },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.width(150.dp)
                    )
                }

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

                ShadowedCard(animateContentSize = true) { Text(text = "Epley: $epleyRM") }

                Spacer(Modifier.height(15.dp))

                ShadowedCard(animateContentSize = true) { Text(text = "Brzycki: $brzyckiRM") }

                Spacer(Modifier.height(15.dp))

                ShadowedCard(animateContentSize = true) { Text(text = "McGlothin: $mcglothinRM") }

                Spacer(Modifier.height(15.dp))

                ShadowedCard(animateContentSize = true) { Text(text = "Lombardi: $lombardiRM") }

                Spacer(Modifier.height(15.dp))

                ShadowedCard(animateContentSize = true) {
                    Text(
                        text = "Mayhew ${
                        getStringRes(
                            TextResKeys.ET_AL
                        )
                        }.: $mayhewRM"
                    )
                }

                Spacer(Modifier.height(15.dp))

                ShadowedCard(animateContentSize = true) {
                    Text(
                        text = "O'Conner ${
                        getStringRes(
                            TextResKeys.ET_AL
                        )
                        }. $oconnerRM"
                    )
                }

                Spacer(Modifier.height(15.dp))

                ShadowedCard(animateContentSize = true) { Text(text = "Wathen: $wathenRM") }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewOneRmScreen() {
    OneRm()
}
