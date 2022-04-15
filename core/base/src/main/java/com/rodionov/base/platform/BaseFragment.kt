package com.rodionov.base.platform

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

open class BaseFragment(@LayoutRes val layout: Int): Fragment(layout) {

    val screenViewModel: BaseViewModel

}