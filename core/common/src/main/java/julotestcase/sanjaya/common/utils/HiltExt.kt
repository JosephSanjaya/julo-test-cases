package julotestcase.sanjaya.common.utils

import dagger.hilt.android.EntryPointAccessors
import julotestcase.sanjaya.common.utils.initializer.appContext

/**
 * Retrieves the entry point of the specified class for dependency injection.
 *
 * @param entryPoint: Class object representing the entry point interface.
 * @return T object representing the entry point.
 */
inline fun<reified T> getEntryPoint(entryPoint: Class<T>): T = EntryPointAccessors.fromApplication(
    appContext,
    entryPoint
)
