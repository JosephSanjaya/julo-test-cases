package julotestcase.sanjaya.common.utils

import julotestcase.sanjaya.common.di.JsonEntryPoint

/**
 * Extension function to convert a JSON string to an object of type T.
 *
 * @return the object of type T corresponding to the JSON string.
 */
inline fun <reified T> String.toObject(): T {
    // Get a reference to the Gson object.
    val gson = getEntryPoint(JsonEntryPoint::class.java).getGson()
    // Convert the JSON string to an object of type T using Gson.
    return gson.fromJson(this, T::class.java)
}
