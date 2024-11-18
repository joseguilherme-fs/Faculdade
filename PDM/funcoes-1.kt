// Mapa mutável para armazenar as matérias e suas respectivas notas
val materiasENotas = mutableMapOf<String, MutableList<Double>>()

/**
 * Adiciona uma disciplina no dicionário mutável.
 * Recebe um array de notas (opcional).
 * Retorna true se conseguiu, false se não.
 */
fun adicionarDisciplina(materia: String, notas: MutableList<Double> = mutableListOf()): Boolean {
    return materiasENotas.put(materia, notas) != null
}

/**
 * Adiciona uma nota à lista de notas de uma determinada matéria.
 * Retorna true se conseguiu adicionar, false se não conseguiu.
 */
fun adicionarNota(materia: String, nota: Double): Boolean {
    val notasDaMateria = materiasENotas[materia]
    return if (notasDaMateria != null) {
        notasDaMateria.add(nota)
        true
    } else {
        false
    }
}

/**
 * Mostra na tela todas as notas presentes em uma matéria, junto com a média.
 */
fun mostrarNotas(materia: String) {
    if (!materiasENotas.containsKey(materia)) {
        println("Materia $materia não encontrada")
    } else {
        val listaNotas = materiasENotas[materia]
        if (listaNotas != null && listaNotas.isNotEmpty()) {
            println("Materia: $materia")
            var cont = 1
            for (nota in listaNotas) {
                println("Nota ${cont++}: $nota")
            }
            val media = calcularMedia(listaNotas)
            println("Média: $media\n")
        } else {
            println("Não foi possível mostrar as notas da matéria $materia\n")
        }
    }
}

/**
 * Retorna a média obtida a partir de uma lista de notas.
 */
fun calcularMedia(notas: MutableList<Double>): Double {
    return if (notas.isNotEmpty()) {
        notas.sum() / notas.size
    } else {
        0.0
    }
}

/**
 * Adiciona várias notas de uma só vez em uma matéria.
 * Retorna true se conseguiu adicionar, false se não conseguiu.
 */
fun adicionarVariasNotas(materia: String, vararg notas: Double): Boolean {
    val listaNotas = materiasENotas[materia]
    return if (listaNotas != null) {
        listaNotas.addAll(notas.toList())
        true
    } else {
        false
    }
}

fun main() {
    // 1. Adicionar disciplina com atribuição posicional
    adicionarDisciplina("Matemática", mutableListOf(8.0, 7.5))

    // 2. Adicionar disciplina com atribuição nomeada
    adicionarDisciplina(materia = "História", notas = mutableListOf(9.0))

    // 3. O parâmetro "notas" agora possui um valor padrão (mutableListOf()).

    // 4. Adicionar disciplina sem atribuir valores a notas
    adicionarDisciplina("Física")

    // 5. Adicionar 3 notas para as disciplinas
    adicionarNota("Matemática", 10.0)
    adicionarNota("História", 8.5)
    adicionarNota("Física", 7.0)

    // 6. Mostrar as notas das disciplinas
    mostrarNotas("Matemática")
    mostrarNotas("História")
    mostrarNotas("Física")

    // 7. Adicionar mais uma disciplina
    adicionarDisciplina("Química")

    // 8. Implementação do método adicionarVariasNotas() feita.

    // 9. Adicionar 3 notas para a disciplina "Química"
    adicionarVariasNotas("Química", 9.5, 8.0, 7.5)

    // 10. Mostrar as notas da disciplina "Química"
    mostrarNotas("Química")

    // 11. Implementação da função calcularMedia() feita.

    // 12. Calcular a média de 2 disciplinas
    val mediaMatematica = calcularMedia(materiasENotas["Matemática"] ?: mutableListOf())
    val mediaHistoria = calcularMedia(materiasENotas["História"] ?: mutableListOf())
    println("Média de Matemática: $mediaMatematica")
    println("Média de História: $mediaHistoria")

    // 13. A função mostrarNotas() já mostra a média das disciplinas.

    // 14. Mostrar as notas de uma disciplina específica
    mostrarNotas("História")
}
