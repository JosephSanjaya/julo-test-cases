package julotestcase.sanjaya.common.data.pref

import julotestcase.sanjaya.common.data.DataGetter

/**
 * A repository for accessing and modifying data stored in SharedPreferences.
 *
 * @constructor Creates a new instance of [PrefRepo].
 */
interface PrefRepo : DataGetter {
    /**
     * Sets a key-value pair in SharedPreferences.
     *
     * @param key The key to set.
     * @param value The value to set for the given key.
     */
    suspend fun setData(key: String, value: Any)

    /**
     * Clears all data stored in SharedPreferences.
     */
    suspend fun clear()

    /**
     * The name of the SharedPreferences file to access.
     */
    companion object {
        const val PREF_NAME = "common-pref"
    }
}
