package com.mutkuensert.fitnesspursuit.ui.warmupsetsscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mutkuensert.fitnesspursuit.R
import com.mutkuensert.fitnesspursuit.ui.theme.FitnessPursuitTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mutkuensert.fitnesspursuit.ui.MyButtonWithShadow
import com.mutkuensert.fitnesspursuit.ui.MyCardWithShadow

@Composable
fun WarmupSetsScreen(viewModel: WarmupSetsViewModel = viewModel()){
    val context = LocalContext.current

    val weightInput by viewModel.weightInput.observeAsState()

    val set2Kg by viewModel.set2Kg.observeAsState()
    val set3Kg by viewModel.set3Kg.observeAsState()
    val set4Kg by viewModel.set4Kg.observeAsState()
    val set5Kg by viewModel.set5Kg.observeAsState()

    Surface {

        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            MyCardWithShadow { Text(text = context.getString(R.string.ask_weight)) }

            HeightSpacerInWarmupSetsScreen()

            Row {

                OutlinedTextField(
                    value = weightInput!!,
                    onValueChange = { viewModel.replaceCommaWithDotAndAllowOnlyOneDot(it) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.width(150.dp)
                )
                
                Spacer(modifier = Modifier.width(10.dp))

                MyButtonWithShadow(onClick = { if(weightInput!!.isNotEmpty()) viewModel.calculateWarmupSetsWeights(weightInput!!.toDouble()) }) {
                    Text(text = context.getString(R.string.calculate))
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
                MyCardWithShadow {
                    Row {
                        Text(text = "Set 1: ")
                        Text(text = context.getString(R.string.warmup_set_1_description))
                        Text(text = "15 ${context.getString(R.string.reps)}")
                    }
                }

                HeightSpacerInWarmupSetsScreen()

                MyCardWithShadow {
                    Row {
                        Text(text = "Set 2 (55%): ")
                        set2Kg?.let { Text(text = "$it kg 8 ${context.getString(R.string.reps)}") }
                    }
                }

                HeightSpacerInWarmupSetsScreen()

                MyCardWithShadow {
                    Row {
                        Text(text = "Set 3 (70%): ")
                        set3Kg?.let { Text(text = "$it kg 5 ${context.getString(R.string.reps)}") }
                    }
                }


                HeightSpacerInWarmupSetsScreen()

                MyCardWithShadow {
                    Row {
                        Text(text = "Set 4 (80%): ")
                        set4Kg?.let { Text(text = "$it kg 3 ${context.getString(R.string.reps)}") }
                    }
                }

                HeightSpacerInWarmupSetsScreen()

                MyCardWithShadow {
                    Row {
                        Text(text = "Set 5 (90%): ")
                        set5Kg?.let { Text(text = "$it kg 1 ${context.getString(R.string.rep)}") }
                    }
                }
            }
        }
    }

}

@Composable
fun HeightSpacerInWarmupSetsScreen(){
    Spacer(modifier = Modifier.height(30.dp))
}


@Preview(showSystemUi = true)
@Composable
fun PreviewWarmupSetsScreen(){
    FitnessPursuitTheme {
        WarmupSetsScreen()
    }
}