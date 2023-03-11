package com.mutkuensert.fitnesspursuit.ui.bodysizes

import androidx.lifecycle.ViewModel
import com.mutkuensert.fitnesspursuit.data.source.BodySizesDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BodySizesViewModel @Inject constructor(private val bodySizesDao: BodySizesDao) : ViewModel()
