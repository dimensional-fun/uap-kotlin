package gay.vzt.uaparser

import gay.vzt.uaparser.regexes.Regexes
import kotlinx.serialization.Serializable

@Serializable
public data class Device(val family: String, val brand: String?, val model: String?) {
    public companion object {
        public val Other: Device = Device("Other", null, null)

        public fun List<Regexes.Device>.match(agent: String): Device =
            firstNotNullOfOrNull { it.match(agent) } ?: Other

        public fun Regexes.Device.match(agent: String): Device? {
            val result = compiled.find(agent)
                ?: return null

            return Device(
                (result repl deviceReplacement) ?: deviceReplacement,
                brandReplacement?.let { result repl it },
                result repl modelReplacement,
            )
        }
    }
}
