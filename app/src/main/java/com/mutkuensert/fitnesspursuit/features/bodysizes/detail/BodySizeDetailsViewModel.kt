package com.mutkuensert.fitnesspursuit.features.bodysizes.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mutkuensert.fitnesspursuit.data.BodySizesModel
import com.mutkuensert.fitnesspursuit.data.source.BodySizesDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BodySizeDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val bodySizesDao: BodySizesDao
) : ViewModel() {
    private val userId: String? = savedStateHandle["userId"]

    private val _allList = MutableStateFlow(listOf<BodySizesModel>())
    val allList = _allList.asStateFlow()

    init {
        viewModelScope.launch { _allList.value = bodySizesDao.getAllBodySizes() }
    }

    fun saveData(bodySizes: BodySizesModel) {
        viewModelScope.launch {
            bodySizesDao.saveBodySize(bodySizes)
        }
    }
}
