package com.lutawav.architecturestudy.util

inline fun Boolean.then(action: () -> Unit) =
    if (this) action() else {
    }
