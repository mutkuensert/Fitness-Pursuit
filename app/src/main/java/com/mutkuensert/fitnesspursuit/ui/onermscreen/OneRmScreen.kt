package com.mutkuensert.fitnesspursuit.ui.onermscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mutkuensert.fitnesspursuit.R
import com.mutkuensert.fitnesspursuit.ui.HeightSpacer
import com.mutkuensert.fitnesspursuit.ui.MyButtonWithShadow
import com.mutkuensert.fitnesspursuit.ui.MyCardWithShadow


@Composable
fun OneRmScreen(viewModel: OneRmViewModel = viewModel()){
    val context = LocalContext.current

    val weightInput by viewModel.weightInput.observeAsState()
    val repsInput by viewModel.repsInput.observeAsState()

    val epleyRM by viewModel.epleyRM.observeAsState()
    val brzyckiRM by viewModel.brzyckiRM.observeAsState()
    val mcglothinRM by viewModel.mcglothinRM.observeAsState()
    val lombardiRM by viewModel.lombardiRM.observeAsState()
    val mayhewRM by viewModel.mayhewRM.observeAsState()
    val oconnerRM by viewModel.oconnerRM.observeAsState()
    val wathenRM by viewModel.wathenRM.observeAsState()

    Surface {
        Column(modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
            Column(modifier = Modifier
                .fillMaxHeight()
                .width(280.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center) {

                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {

                    MyCardWithShadow { Text(text = context.getString(R.string.weight_you_can_lift)) }

                    Spacer(modifier = Modifier.weight(1f))

                    OutlinedTextField(value = weightInput!!, onValueChange = {viewModel.replaceCommaWithDotAndAllowOnlyOneDot(it)},
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.width(150.dp))

                }


                HeightSpacer(20.dp)

                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {

                    MyCardWithShadow { Text(text = context.getString(R.string.number_of_reps)) }

                    Spacer(modifier = Modifier.weight(1f))

                    OutlinedTextField(value = repsInput!!, onValueChange = {viewModel.dontAllowDotOrComma(it)},
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.width(150.dp))

                }


                HeightSpacer(15.dp)

                MyButtonWithShadow(
                    onClick = {
                        if(weightInput!!.isNotEmpty() && repsInput!!.isNotEmpty()) viewModel.calculateOneRmValues(weightInput!!.toDouble(), repsInput!!.toInt()) }) {

                    Text(text = context.getString(R.string.calculate))
                }

                HeightSpacer(20.dp)

                MyCardWithShadow { Text(text = "Epley: $epleyRM")}

                HeightSpacer(15.dp)

                MyCardWithShadow { Text(text = "Brzycki: $brzyckiRM")}

                HeightSpacer(15.dp)

                MyCardWithShadow { Text(text = "McGlothin: $mcglothinRM")}

                HeightSpacer(15.dp)

                MyCardWithShadow { Text(text = "Lombardi: $lombardiRM")}

                HeightSpacer(15.dp)

                MyCardWithShadow { Text(text = "Mayhew ${context.getString(R.string.et_al)}.: $mayhewRM")}

                HeightSpacer(15.dp)

                MyCardWithShadow { Text(text = "O'Conner ${context.getString(R.string.et_al)}. $oconnerRM")}

                HeightSpacer(15.dp)

                MyCardWithShadow { Text(text = "Wathen: $wathenRM")}

            }
        }
        
    }

}

@Preview(showSystemUi = true)
@Composable
fun PreviewOneRmScreen(){
    OneRmScreen()
}