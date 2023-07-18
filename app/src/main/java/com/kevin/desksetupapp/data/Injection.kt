package com.kevin.desksetupapp.data


object Injection {
    fun provideRepository(): DeskSetupRepository {
        return DeskSetupRepository.getInstance()
    }
}
