package com.example.nobelpostgres.data

import com.example.nobelpostgres.domain.RemoteLaureateRecord
import com.example.nobelpostgres.domain.RemotePrizeRecord

object SeedData {
    val prizes = listOf(
        RemotePrizeRecord(
            year = 2023,
            category = "physics",
            categoryTitle = "Physics",
            topMotivation = "For experimental methods that generate attosecond pulses of light for the study of electron dynamics in matter.",
            detailLink = "https://www.nobelprize.org/prizes/physics/2023/summary/",
            laureates = listOf(
                RemoteLaureateRecord("1001", "Pierre Agostini", "1/3", "For experimental methods that generate attosecond pulses of light.", "France / USA", null, null),
                RemoteLaureateRecord("1002", "Ferenc Krausz", "1/3", "For attosecond science.", "Hungary / Austria", null, null),
                RemoteLaureateRecord("1003", "Anne L'Huillier", "1/3", "For generating attosecond pulses of light.", "France / Sweden", null, null)
            )
        ),
        RemotePrizeRecord(
            year = 2023,
            category = "chemistry",
            categoryTitle = "Chemistry",
            topMotivation = "For the discovery and synthesis of quantum dots.",
            detailLink = "https://www.nobelprize.org/prizes/chemistry/2023/summary/",
            laureates = listOf(
                RemoteLaureateRecord("1004", "Moungi G. Bawendi", "1/3", "For the discovery and synthesis of quantum dots.", "France / USA", null, null),
                RemoteLaureateRecord("1005", "Louis E. Brus", "1/3", "For the discovery and synthesis of quantum dots.", "USA", null, null),
                RemoteLaureateRecord("1006", "Alexei I. Ekimov", "1/3", "For the discovery and synthesis of quantum dots.", "Russia / USA", null, null)
            )
        ),
        RemotePrizeRecord(
            year = 2023,
            category = "literature",
            categoryTitle = "Literature",
            topMotivation = "For his innovative plays and prose which give voice to the unsayable.",
            detailLink = "https://www.nobelprize.org/prizes/literature/2023/summary/",
            laureates = listOf(
                RemoteLaureateRecord("1007", "Jon Fosse", "1/1", "For his innovative plays and prose which give voice to the unsayable.", "Norway", null, null)
            )
        ),
        RemotePrizeRecord(
            year = 2023,
            category = "peace",
            categoryTitle = "Peace",
            topMotivation = "For her fight against the oppression of women in Iran and her fight to promote human rights and freedom for all.",
            detailLink = "https://www.nobelprize.org/prizes/peace/2023/summary/",
            laureates = listOf(
                RemoteLaureateRecord("1008", "Narges Mohammadi", "1/1", "For her fight against the oppression of women in Iran.", "Iran", null, null)
            )
        ),
        RemotePrizeRecord(
            year = 2023,
            category = "medicine",
            categoryTitle = "Physiology or Medicine",
            topMotivation = "For their discoveries concerning nucleoside base modifications that enabled the development of effective mRNA vaccines against COVID-19.",
            detailLink = "https://www.nobelprize.org/prizes/medicine/2023/summary/",
            laureates = listOf(
                RemoteLaureateRecord("1009", "Katalin Karikó", "1/2", "For discoveries concerning mRNA vaccines.", "Hungary / USA", null, null),
                RemoteLaureateRecord("1010", "Drew Weissman", "1/2", "For discoveries concerning mRNA vaccines.", "USA", null, null)
            )
        ),
        RemotePrizeRecord(
            year = 2023,
            category = "economics",
            categoryTitle = "Economic Sciences",
            topMotivation = "For having advanced our understanding of women’s labour market outcomes.",
            detailLink = "https://www.nobelprize.org/prizes/economic-sciences/2023/summary/",
            laureates = listOf(
                RemoteLaureateRecord("1011", "Claudia Goldin", "1/1", "For having advanced our understanding of women’s labour market outcomes.", "USA", null, null)
            )
        )
    )
}
