package com.mutkuensert.fitnesspursuit.features.warmupsets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mutkuensert.fitnesspursuit.components.FPTextField
import com.mutkuensert.fitnesspursuit.components.ShadowedButton
import com.mutkuensert.fitnesspursuit.components.ShadowedCard
import com.mutkuensert.fitnesspursuit.core.getStringRes
import com.mutkuensert.fitnesspursuit.resources.TextResKeys
import com.mutkuensert.fitnesspursuit.ui.theme.FitnessPursuitTheme

@Composable
fun WarmupSets(viewModel: WarmupSetsViewModel = viewModel()) {
    val weightInput by viewModel.weightInput.collectAsStateWithLifecycle()
    val set2 by viewModel.set2.collectAsStateWithLifecycle()
    val set3 by viewModel.set3.collectAsStateWithLifecycle()
    val set4 by viewModel.set4.collectAsStateWithLifecycle()
    val set5 by viewModel.set5.collectAsStateWithLifecycle()

    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ShadowedCard { Text(text = getStringRes(TextResKeys.ASK_WEIGHT)) }

            Spacer(Modifier.height(30.dp))

            Row {
                FPTextField(
                    value = weightInput,
                    onValueChange = { viewModel.replaceCommaWithDotAndAllowOnlyOneDot(it) },
                    singleLine = true,
                    shape = RoundedCornerShape(8.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.width(100.dp)
                )

                Spacer(modifier = Modifier.width(10.dp))

                ShadowedButton(onClick = {
                    if (weightInput.isNotEmpty()) {
                        viewModel.calculateWarmupSetsWeights(
                            weightInput.toDouble()
                        )
                    }
                }) {
                    Text(text = getStringRes(TextResKeys.CALCULATE))
                }
            }

            Spacer(modifier = Modifier.height(60.dp))

            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 45.dp)
            ) {
                ShadowedCard {
                    Row {
                        Text(text = "Set 1: ")
                        Text(text = getStringRes(TextResKeys.WARMUP_SET_1_DESCRIPTION))
                        Text(text = "15 " + getStringRes(TextResKeys.REPS))
                    }
                }

                Spacer(Modifier.height(30.dp))

                ShadowedCard(animateContentSize = true) {
                    Row {
                        Text(text = "Set 2 (55%): ")
                        Text(text = "$set2 kg 8 " + getStringRes(TextResKeys.REPS))
                    }
                }

                Spacer(Modifier.height(30.dp))

                ShadowedCard(animateContentSize = true) {
                    Row {
                        Text(text = "Set 3 (70%): ")
                        Text(text = "$set3 kg 5 " + getStringRes(TextResKeys.REPS))
                    }
                }

                Spacer(Modifier.height(30.dp))

                ShadowedCard(animateContentSize = true) {
                    Row {
                        Text(text = "Set 4 (80%): ")
                        Text(text = "$set4 kg 3 " + getStringRes(TextResKeys.REPS))
                    }
                }

                Spacer(Modifier.height(30.dp))

                ShadowedCard(animateContentSize = true) {
                    Row {
                        Text(text = "Set 5 (90%): ")
                        Text(text = "$set5 kg 1 " + getStringRes(TextResKeys.REP))
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewWarmupSetsScreen() {
    FitnessPursuitTheme {
        WarmupSets()
    }
}
