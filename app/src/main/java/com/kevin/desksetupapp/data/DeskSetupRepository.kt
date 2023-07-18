package com.kevin.desksetupapp.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class DeskSetupRepository {

    private val DeskSetup = mutableListOf<DeskSetupList>()

    init {
        if (DeskSetup.isEmpty()) {
            DeskSetupData.DeskSetup.forEach { DeskSetup.add(DeskSetupList(it, 0)) }
        }
    }

    fun getAllData(): Flow<List<DeskSetupList>> {
        return flowOf(DeskSetup)
    }

    fun getById(id: Long): DeskSetupList {
        return DeskSetup.first { it.DeskSetup.id == id }
    }

    companion object {
        @Volatile private var instance: DeskSetupRepository? = null

        fun getInstance(): DeskSetupRepository =
                instance ?: synchronized(this) { DeskSetupRepository().apply { instance = this } }
    }
}
