package gay.vzt.uaparser

import gay.vzt.uaparser.regexes.Regexes
import kotlinx.serialization.Serializable

@Serializable
public data class OS(
    val family: String,
    val major: String?,
    val minor: String?,
    val patch: String?,
    val patchMinor: String?,
) {
    public companion object {
        public val Other: OS = OS("Other", null, null, null, null)

        public fun List<Regexes.OS>.match(agent: String): OS =
            firstNotNullOfOrNull { it.match(agent) } ?: Other

        public fun Regexes.OS.match(agent: String): OS? {
            val result = compiled.find(agent)
                ?: return null

            return OS(
                (result repl osReplacement) ?: osReplacement,
                result repl osV1Replacement,
                result repl osV2Replacement,
                result repl osV3Replacement,
                result repl osV4Replacement,
            )
        }
    }
}
