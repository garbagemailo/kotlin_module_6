package com.example.nobelmemory.data

import com.example.nobelmemory.domain.Laureate
import com.example.nobelmemory.domain.NobelPrize

object SeedData {
    val prizes = listOf(
        NobelPrize(
            year = 2023,
            category = "physics",
            categoryTitle = "Physics",
            topMotivation = "For experimental methods that generate attosecond pulses of light for the study of electron dynamics in matter.",
            laureates = listOf(
                Laureate("1", "Pierre Agostini", "1/3", "For experimental methods that generate attosecond pulses of light.", "France / USA"),
                Laureate("2", "Ferenc Krausz", "1/3", "For attosecond science.", "Hungary / Austria"),
                Laureate("3", "Anne L'Huillier", "1/3", "For generating attosecond pulses of light.", "France / Sweden")
            )
        ),
        NobelPrize(
            year = 2023,
            category = "chemistry",
            categoryTitle = "Chemistry",
            topMotivation = "For the discovery and synthesis of quantum dots.",
            laureates = listOf(
                Laureate("4", "Moungi G. Bawendi", "1/3", "For the discovery and synthesis of quantum dots.", "USA"),
                Laureate("5", "Louis E. Brus", "1/3", "For the discovery and synthesis of quantum dots.", "USA"),
                Laureate("6", "Alexei I. Ekimov", "1/3", "For the discovery and synthesis of quantum dots.", "Russia / USA")
            )
        ),
        NobelPrize(
            year = 2023,
            category = "literature",
            categoryTitle = "Literature",
            topMotivation = "For his innovative plays and prose which give voice to the unsayable.",
            laureates = listOf(
                Laureate("7", "Jon Fosse", "1/1", "For his innovative plays and prose which give voice to the unsayable.", "Norway")
            )
        ),
        NobelPrize(
            year = 2023,
            category = "peace",
            categoryTitle = "Peace",
            topMotivation = "For her fight against the oppression of women in Iran and her fight to promote human rights and freedom for all.",
            laureates = listOf(
                Laureate("8", "Narges Mohammadi", "1/1", "For her fight against the oppression of women in Iran.", "Iran")
            )
        ),
        NobelPrize(
            year = 2023,
            category = "medicine",
            categoryTitle = "Physiology or Medicine",
            topMotivation = "For their discoveries concerning nucleoside base modifications that enabled the development of effective mRNA vaccines against COVID-19.",
            laureates = listOf(
                Laureate("9", "Katalin Karikó", "1/2", "For discoveries concerning mRNA vaccines.", "Hungary / USA"),
                Laureate("10", "Drew Weissman", "1/2", "For discoveries concerning mRNA vaccines.", "USA")
            )
        ),
        NobelPrize(
            year = 2023,
            category = "economics",
            categoryTitle = "Economic Sciences",
            topMotivation = "For having advanced our understanding of women’s labour market outcomes.",
            laureates = listOf(
                Laureate("11", "Claudia Goldin", "1/1", "For having advanced our understanding of women’s labour market outcomes.", "USA")
            )
        )
    )
}
