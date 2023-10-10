package com.sevenpeaks.zawmyonaing.readease.data.reposiory

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.sevenpeaks.zawmyonaing.readease.domain.model.User
import com.sevenpeaks.zawmyonaing.readease.domain.repository.PreferenceRepository
import com.sevenpeaks.zawmyonaing.readease.utils.android.getValueOrNull
import com.sevenpeaks.zawmyonaing.readease.utils.android.setValue
import com.sevenpeaks.zawmyonaing.readease.utils.android.watchValueWithMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import timber.log.Timber
import javax.inject.Inject

class PreferenceRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val json: Json
) : PreferenceRepository {

    override val user: Flow<User?>
        get() = keyUser.watchValueWithMapper(dataStore, ::mapUser)

    override suspend fun setUser(user: User?) {
        Timber.d("setUser() called with: user = [$user]")
        val userJson = user?.let { json.encodeToString(it) }
        Timber.d("setUser: User Json => $userJson")
        keyUser.setValue(dataStore, userJson)
    }

    override suspend fun getUser(): User? {
        return mapUser(keyUser.getValueOrNull(dataStore))
    }

    private fun mapUser(userJson: String?): User? {
        if (userJson.isNullOrBlank()) {
            return null
        }
        return runCatching { json.decodeFromString<User>(userJson) }.getOrNull().also {
            Timber.d("getUser() returned: $it")
        }
    }

    companion object {
        private val keyUser = stringPreferencesKey("USER_PROFILE")
    }
}