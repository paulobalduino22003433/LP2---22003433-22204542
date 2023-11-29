package pt.ulusofona.lp2.deisichess

import pt.ulusofona.lp2.deisichess.StatType

class Statistics {

    fun generateStatFunction(statType: StatType): (GameManager) -> List<String> {
        return when (statType) {
            StatType.TOP_5_CAPTURAS -> { gameManager -> calculateTop5Capturas(gameManager) }
            StatType.TOP_5_PONTOS -> { gameManager -> calculateTop5Pontos(gameManager) }
            StatType.PECAS_MAIS_5_CAPTURAS -> { gameManager -> calculatePecasMais5Capturas(gameManager) }
            StatType.PECAS_MAIS_BARALHADAS -> { gameManager -> calculatePecasMaisBaralhadas(gameManager) }
            StatType.TIPOS_CAPTURADOS -> { gameManager -> calculateTiposCapturados(gameManager) }
        }
    }

    private fun calculateTop5Capturas(gameManager: GameManager): List<String> {
        return listOf("Result for TOP_5_CAPTURAS")
    }

    private fun calculateTop5Pontos(gameManager: GameManager): List<String> {
        return listOf("Result for TOP_5_PONTOS")
    }

    private fun calculatePecasMais5Capturas(gameManager: GameManager): List<String> {
        return listOf("Result for PECAS_MAIS_5_CAPTURAS")
    }

    private fun calculatePecasMaisBaralhadas(gameManager: GameManager): List<String> {
        return listOf("Result for PECAS_MAIS_BARALHADAS")
    }

    private fun calculateTiposCapturados(gameManager: GameManager): List<String> {
        return listOf("Result for TIPOS_CAPTURADOS")
    }
}


