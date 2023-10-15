import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.buildCodeBlock
import net.mamoe.yamlkt.*

object Thing

fun main() {
    val yaml = Thing::class.java.classLoader.getResourceAsStream("regexes.yaml")
        .bufferedReader()
        .readText()
        .let(Yaml.Default::decodeYamlMapFromString)

    val code = buildCodeBlock {
        addStatement("Regexes(")
        addParsers("userAgentParsers", "UA", yaml["user_agent_parsers"])
        addParsers("osParsers", "OS", yaml["os_parsers"])
        addParsers("deviceParsers", "Device", yaml["device_parsers"])
        addStatement(")")
    }

    println(code)
}

fun CodeBlock.Builder.addParsers(key: String, storage: String, element: YamlElement?) {
    addStatement("$key = listOf(")
    element?.let { it as? YamlList }
        ?.mapNotNull { it as? YamlMap }
        ?.forEach { thing ->
            addStatement("Regexes.$storage(")
            thing.mapKeys { e ->
                e.key.literalContentOrNull!!
                    .split('_')
                    .joinToString("") { it.capitalize() }
                    .replaceFirstChar { it.lowercaseChar() }
            }.forEach {
                addStatement("${it.key} = %S,", it.value)
            }
            addStatement("),")
        }

    addStatement("),")
}
