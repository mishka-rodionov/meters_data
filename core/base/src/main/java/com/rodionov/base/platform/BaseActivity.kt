package com.rodionov.base.platform

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity(@LayoutRes val layout: Int): AppCompatActivity(layout) {
}