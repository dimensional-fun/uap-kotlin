package gay.vzt.uaparser

import gay.vzt.uaparser.regexes.Regexes
import kotlinx.serialization.Serializable

@Serializable
public data class Agent(val family: String, val major: String?, val minor: String?, val patch: String?) {
    public companion object {
        public val Other: Agent = Agent("Other", null, null, null)

        public fun List<Regexes.UA>.match(agent: String): Agent =
            firstNotNullOfOrNull { it.match(agent) } ?: Other

        public fun Regexes.UA.match(agent: String): Agent? {
            val result = compiled.find(agent)
                ?: return null

            return Agent(
                (result repl familyReplacement) ?: familyReplacement,
                result repl v1Replacement,
                result repl v2Replacement,
                result repl v3Replacement,
            )
        }
    }
}
