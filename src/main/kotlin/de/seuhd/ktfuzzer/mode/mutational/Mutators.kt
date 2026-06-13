package de.seuhd.ktfuzzer.mode.mutational

import kotlin.random.Random

/**
 * Character-level mutators from The Fuzzing Book's MutationFuzzer
 * (<https://www.fuzzingbook.org/html/MutationFuzzer.html>), plus [repeatRandomCharacter]. Each
 * function applies one small edit to an input string and returns the result.
 */
internal object Mutators {
    /** Deletes one randomly chosen character. */
    fun deleteRandomCharacter(input: String, random: Random): String {
        val length = input.length
        if (length == 0) {
            return ""
        }

        val index = random.nextInt(input.length)
        return input.removeRange(index, index + 1)
    }

    /** Inserts one character drawn uniformly from [alphabet] at a random position. */
    fun insertRandomCharacter(input: String, alphabet: List<Char>, random: Random): String {
        val result = input.toMutableList()
        val randomCharacter = alphabet[random.nextInt(alphabet.size)]
        val index = random.nextInt(input.length + 1)

        result.add(index, randomCharacter)
        return result.joinToString("")
    }

    /** Flips one randomly chosen low bit of one randomly chosen character. */
    fun flipRandomCharacter(input: String, random: Random): String {
        if (input.isEmpty()) return ""

        val result = input.toMutableList()
        val index = random.nextInt(input.length)
        val bit = 1 shl random.nextInt(8)

        result[index] = (result[index].code xor bit).toChar()
        return result.joinToString("")
    }

    /** Repeats one randomly chosen character a random number of times in place. */
    fun repeatRandomCharacter(input: String, random: Random): String {
        if (input.isEmpty()) return ""

        val result = input.toMutableList()
        val index = random.nextInt(input.length)
        val counter = random.nextInt(1, 1000)
        val repeatedCharacter = result[index]

        repeat(counter) {
            result.add(index, repeatedCharacter)
        }

        return result.joinToString("")
    }
}
