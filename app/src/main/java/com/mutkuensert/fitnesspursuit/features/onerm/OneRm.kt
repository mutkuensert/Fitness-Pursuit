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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mutkuensert.fitnesspursuit.R
import com.mutkuensert.fitnesspursuit.components.ShadowedButton
import com.mutkuensert.fitnesspursuit.components.ShadowedCard

@Composable
fun OneRm(viewModel: OneRmViewModel = viewModel()) {
    val weightInput by viewModel.weightInput.collectAsStateWithLifecycle()
    val repsInput by viewModel.repsInput.collectAsStateWithLifecycle()
    val epleyRM by viewModel.epleyRM.collectAsStateWithLifecycle()
    val brzyckiRM by viewModel.brzyckiRM.collectAsStateWithLifecycle()
    val mcglothinRM by viewModel.mcglothinRM.collectAsStateWithLifecycle()
    val lombardiRM by viewModel.lombardiRM.collectAsStateWithLifecycle()
    val mayhewRM by viewModel.mayhewRM.collectAsStateWithLifecycle()
    val oconnerRM by viewModel.oconnerRM.collectAsStateWithLifecycle()
    val wathenRM by viewModel.wathenRM.collectAsStateWithLifecycle()
    val context = LocalContext.current

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
                    ShadowedCard { Text(text = context.getString(R.string.weight_you_can_lift)) }

                    Spacer(modifier = Modifier.weight(1f))

                    OutlinedTextField(
                        value = weightInput,
                        onValueChange = { viewModel.replaceCommaWithDotAndAllowOnlyOneDot(it) },
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
                    ShadowedCard { Text(text = context.getString(R.string.number_of_reps)) }

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
                    Text(text = context.getString(R.string.calculate))
                }

                Spacer(Modifier.height(20.dp))

                ShadowedCard { Text(text = "Epley: $epleyRM") }

                Spacer(Modifier.height(15.dp))

                ShadowedCard { Text(text = "Brzycki: $brzyckiRM") }

                Spacer(Modifier.height(15.dp))

                ShadowedCard { Text(text = "McGlothin: $mcglothinRM") }

                Spacer(Modifier.height(15.dp))

                ShadowedCard { Text(text = "Lombardi: $lombardiRM") }

                Spacer(Modifier.height(15.dp))

                ShadowedCard { Text(text = "Mayhew ${context.getString(R.string.et_al)}.: $mayhewRM") }

                Spacer(Modifier.height(15.dp))

                ShadowedCard { Text(text = "O'Conner ${context.getString(R.string.et_al)}. $oconnerRM") }

                Spacer(Modifier.height(15.dp))

                ShadowedCard { Text(text = "Wathen: $wathenRM") }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewOneRmScreen() {
    OneRm()
}
