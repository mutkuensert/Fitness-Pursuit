package com.mutkuensert.fitnesspursuit.features.bodysizes.bodysizelist

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
class BodySizeListViewModel @Inject constructor(private val bodySizeDao: BodySizesDao) :
    ViewModel() {
    private val _bodySizeList = MutableStateFlow(listOf<BodySizesModel>())
    val bodySizeList = _bodySizeList.asStateFlow()

    private val _allList = MutableStateFlow(listOf<BodySizesModel>())
    val allList = _allList.asStateFlow()

    init {
        viewModelScope.launch {
            _allList.value = bodySizeDao.getAllBodySizes()
            _bodySizeList.value = allList.value
        }
    }
}
