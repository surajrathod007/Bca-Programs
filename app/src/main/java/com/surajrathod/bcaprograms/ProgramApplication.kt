package com.surajrathod.bcaprograms

import android.app.Application
import com.surajrathod.bcaprograms.data.ProgramDatabase

class ProgramApplication : Application() {

    val database : ProgramDatabase by lazy {ProgramDatabase.invoke(this)}
}