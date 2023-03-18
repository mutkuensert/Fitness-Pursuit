package com.mutkuensert.fitnesspursuit.features.bodysizeslist

import androidx.lifecycle.ViewModel
import com.mutkuensert.fitnesspursuit.data.source.BodySizesDao
import javax.inject.Inject

class BodySizeListViewModel @Inject constructor(private val bodySizeDao: BodySizesDao) :
    ViewModel() {

}