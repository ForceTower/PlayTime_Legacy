package com.forcetower.playtime;

import com.forcetower.playtime.db.model.Title;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by João Paulo on 31/05/2018.
 */
public class MockUtils {
    private static final Title avengers = new Title("Avengers: Infinity War", "https://image.tmdb.org/t/p/w600_and_h900_bestv2/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg", "https://youtu.be/6ZfuNTqbHE8", 4.2f, "13/05/2018");
    private static final Title deadpool = new Title("Deadpool 2", "https://image.tmdb.org/t/p/w600_and_h900_bestv2/to0spRl1CMDvyUbOnbb4fTk3VAd.jpg", "https://youtu.be/D86RtevtfrA", 4.1f, "13/08/2018");
    private static final Title solo = new Title("Han Solo: Uma História Star Wars", "https://image.tmdb.org/t/p/w600_and_h900_bestv2/lJChjsNR7O1waqsOIqlkePoGtZJ.jpg", "https://youtu.be/EqKajgmcs4M", 3.5f, "23/05/2018");
    private static final Title devolucoes = new Title("Não Se Aceitam Devoluções", "https://image.tmdb.org/t/p/w600_and_h900_bestv2/91WqHNXe5tKmLI1pQnptZV9BDkW.jpg", "https://youtu.be/mekjCq7wzEE", 3.0f, "13/08/2018");
    private static final Title gameNight = new Title("A Noite do Jogo", "https://image.tmdb.org/t/p/w600_and_h900_bestv2/fWbGZt8URdS8N4AVpTRupdBXClz.jpg", "https://youtu.be/2wSTtajHAXg", 3.8f, "24/09/2018");
    private static final Title gnomeu = new Title("Gnomeu e Julieta: O Mistério do Jardim", "https://image.tmdb.org/t/p/w600_and_h900_bestv2/b6ZZriobpdpkAy8hfkNENaykot2.jpg", "https://youtu.be/0RbKk3HSXok", 2.8f, "13/05/2016");
    private static final Title cock = new Title("Não Vai Dar", "https://image.tmdb.org/t/p/w600_and_h900_bestv2/cx0LpfM6Drla8uzFfMT09uqKPRu.jpg", "https://youtu.be/g1WVgyh5ah4", 3.8f, "03/05/2018");

    private static final Title readyP1 = new Title("Jogador Nº 1", "https://image.tmdb.org/t/p/w600_and_h900_bestv2/9xDD53hohCMp5VoFtqNPfzTsnLJ.jpg", "https://youtu.be/q_1OJNcTld0", 4.0f, "13/09/2018");
    private static final Title jumanji = new Title("Jumanji: Bem-Vindo à Selva", "https://image.tmdb.org/t/p/w600_and_h900_bestv2/rHPGCpqsbg3WK90uig0KbxBtFrh.jpg", "https://youtu.be/y0oqJGwi42M", 3.9f, "13/05/2018");
    private static final Title blade = new Title("Blade Runner 2049", "https://image.tmdb.org/t/p/w600_and_h900_bestv2/zppVzoeQvds6FPTzcMww6chPIks.jpg", "https://youtu.be/OEW3gbptBZg", 4.0f, "01/01/2020");
    private static final Title kingsman = new Title("Kingsman: Serviço Secreto", "https://image.tmdb.org/t/p/w600_and_h900_bestv2/wDhy1Ga9BoLwkHOI0jJISQem28v.jpg", "https://youtu.be/pm68xWnBeFg", 4.1f, "06/04/2019");
    private static final Title maze = new Title("Maze Runner: A Cura Mortal", "https://image.tmdb.org/t/p/w600_and_h900_bestv2/4H1vAQHD1rojw3cq1VZ3THoRJ6k.jpg", "https://youtu.be/Qi4HEs2H-k8", 3.4f, "15/01/2018");
    private static final Title incredibles = new Title("Os Incríveis", "https://image.tmdb.org/t/p/w600_and_h900_bestv2/tE5Kdj6c2sWMxamK8WCnk2uUzCP.jpg", "https://youtu.be/eZbzbC9285I", 4.3f, "21/07/2018");
    private static final Title bighero = new Title("Operação Big Hero", "https://image.tmdb.org/t/p/w600_and_h900_bestv2/9gLu47Zw5ertuFTZaxXOvNfy78T.jpg", "https://youtu.be/OvgyXKDXdZY", 4.2f, "13/12/2018");
    private static final Title strange = new Title("Doutor Estranho", "https://image.tmdb.org/t/p/w600_and_h900_bestv2/4PiiNGXj1KENTmCBHeN6Mskj2Fq.jpg", "https://youtu.be/9Golt91teTQ", 4.0f, "19/09/2018");
    private static final Title jurassic = new Title("Jurassic World: Reino Ameaçado", "https://image.tmdb.org/t/p/w600_and_h900_bestv2/nR6LhLKk5BsholuIHIAGemMKPKy.jpg", "https://youtu.be/9Golt91teTQ", 4.0f, "18/05/2018");
    private static final Title future = new Title("Future World", "https://image.tmdb.org/t/p/w600_and_h900_bestv2/kMA0IalnEEa0PaHRUzzjpTu5xXQ.jpg", "https://youtu.be/9Golt91teTQ", 4.0f, "21/05/2018");
    private static final Title incredibles2 = new Title("Os Incríveis 2", "https://image.tmdb.org/t/p/w600_and_h900_bestv2/pWIi2JaMO1A7TWDltwk0TifRJco.jpg", "https://youtu.be/9Golt91teTQ", 4.0f, "14/06/2018");
    private static final Title ilha = new Title("Ilha de Cachorros", "https://image.tmdb.org/t/p/w600_and_h900_bestv2/fsS7sWTsmnQmzXCH1tG4DZdRXOQ.jpg", "https://youtu.be/9Golt91teTQ", 4.0f, "03/04/2018");
    private static final Title sexy = new Title("Sexy por Acidente", "https://image.tmdb.org/t/p/w600_and_h900_bestv2/pl8RjlcRuo3qtK6jHemLeqorDgS.jpg", "https://youtu.be/9Golt91teTQ", 4.0f, "13/05/2015");

    private static final Title westworld = new Title("Westworld", "https://image.tmdb.org/t/p/w600_and_h900_bestv2/6aj09UTMQNyfSfk0ZX8rYOEsXL2.jpg", "https://youtu.be/JctIuZfSsa4", 4.0f, "13/05/2017");

    public static List<Title> getRecommendations() {
        List<Title> recommendations = new ArrayList<>();
        avengers.setReleaseDate("20/07/2019");
        deadpool.setReleaseDate("12/10/2018");
        westworld.setReleaseDate("04/05/2018");

        recommendations.add(avengers);
        recommendations.add(deadpool);
        recommendations.add(westworld);

        return recommendations;
    }

    public static List<Title> getAll() {
        return Arrays.asList(readyP1, jumanji, blade, kingsman, maze, incredibles, bighero, strange,
                jurassic, future, incredibles2, ilha, sexy, solo, devolucoes, gameNight, deadpool,
                gameNight, gnomeu);
    }

}
